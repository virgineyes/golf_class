package com.delta.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.delta.criteria.PreCriteriaBuilder;
import com.delta.criteria.SearchCriteria;
import com.delta.handler.SearchQueryCriteriaConsumer;
import com.delta.handler.SortConsumer;
import com.delta.repository.BasicJpaRepository;

/**
 * @author: ACE.CHIU
 * @create: 2020-02-17
 */
public abstract class BasicService<T> {

  public abstract BasicJpaRepository<T> getRepository();

  @Transactional
  public T save(T entity) {
    return getRepository().save(entity);
  }

  public T findOne(String id) {
    Optional<T> opt = getRepository().findByUuid(id);

    return opt.orElse(null);
  }

  public List<T> findAll() {
    return getRepository().findAll();
  }

  public List<T> findAll(Sort sort) {
    return getRepository().findAll(sort);
  }

  public Page<T> findAll(Pageable pageable) {
    return getRepository().findAll(pageable);
  }

  public Page<T> findAll(List<SearchCriteria> params, PreCriteriaBuilder preCriteriaBuilder, Pageable pageable) {
    return getRepository().findAll((root, query, builder) -> {

      Predicate predicate = preCriteriaBuilder.preBuild(builder.conjunction(), query, builder, root);

      if (!params.isEmpty()) {
        SearchQueryCriteriaConsumer searchConsumer = new SearchQueryCriteriaConsumer(predicate, builder, root);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
      }

      if (pageable.getSort() != null) {
        SortConsumer sortConsumer = new SortConsumer(predicate, builder, root);
        pageable.getSort().forEach(sortConsumer);
        query.orderBy(sortConsumer.getOrders());
      }
      return predicate;
    }, pageable);
  }

  public long count() {
    return getRepository().count();
  }

  @Transactional
  public boolean delete(String id) {
    Optional<T> entity = getRepository().findByUuid(id);
    if (entity.isPresent()) {
      getRepository().delete(entity.get());
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public List<T> saveAll(Iterable<T> list) {
    return getRepository().saveAll(list);
  }
}

