package com.delta.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.delta.criteria.SearchCriteria;

import lombok.Getter;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
public class SearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

  @Getter
  private Predicate predicate;

  @Getter
  private CriteriaBuilder builder;

  @Getter
  private Root root;

  public SearchQueryCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root root) {
    this.predicate = predicate;
    this.builder = builder;
    this.root = root;
  }

  @Override
  public void accept(SearchCriteria param) {
    try {
      if ("id".equals(param.getKey())) {
        return;
      }
      String[] keys = param.getKey().split("\\.");
      Path path = root;
      for (String key : keys) {
        path = path.get(key);
      }
      if (param.getOperation().equalsIgnoreCase(SearchCriteria.GREATER_THAN)) {
        if (param.getValue() instanceof LocalDate) {
          predicate = builder.and(predicate, builder.greaterThan(path, (LocalDate) param.getValue()));
        } else if (param.getValue() instanceof LocalDateTime) {
          predicate = builder.and(predicate, builder.greaterThan(path, (LocalDateTime) param.getValue()));
        } else {
          predicate = builder.and(predicate, builder.greaterThan(path, param.getValue().toString()));
        }
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.GREATER_THAN_EQUAL)) {
        if (param.getValue() instanceof LocalDate) {
          predicate = builder.and(predicate, builder.greaterThanOrEqualTo(path, (LocalDate) param.getValue()));
        } else if (param.getValue() instanceof LocalDateTime) {
          predicate = builder.and(predicate, builder.greaterThanOrEqualTo(path, (LocalDateTime) param.getValue()));
        } else {
          predicate = builder.and(predicate, builder.greaterThanOrEqualTo(path, param.getValue().toString()));
        }
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.LESS_THAN)) {
        if (param.getValue() instanceof LocalDate) {
          predicate = builder.and(predicate, builder.lessThan(path, (LocalDate) param.getValue()));
        } else if (param.getValue() instanceof LocalDateTime) {
          predicate = builder.and(predicate, builder.lessThan(path, (LocalDateTime) param.getValue()));
        } else {
          predicate = builder.and(predicate, builder.lessThan(path, param.getValue().toString()));
        }
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.LESS_THAN_EQUAL)) {
        if (param.getValue() instanceof LocalDate) {
          predicate = builder.and(predicate, builder.lessThanOrEqualTo(path, (LocalDate) param.getValue()));
        } else if (param.getValue() instanceof LocalDateTime) {
          predicate = builder.and(predicate, builder.lessThanOrEqualTo(path, (LocalDateTime) param.getValue()));
        } else {
          predicate = builder.and(predicate, builder.lessThanOrEqualTo(path, param.getValue().toString()));
        }
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.EQUALS)) {
        if (param.getValue() instanceof LocalDate) {
          predicate = builder.and(predicate, builder.equal(path, param.getValue()));
        } else if (param.getValue() instanceof LocalDateTime) {
          predicate = builder.and(predicate, builder.equal(path, param.getValue()));
        } else {
          predicate = builder.and(predicate, builder.equal(path, param.getValue().toString()));
        }
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.LIKE)) {
        if (path.getJavaType() == String.class) {
          predicate = builder.and(predicate, builder.like(path, "%" + param.getValue() + "%"));
        } else {
          predicate = builder.and(predicate, builder.equal(path, param.getValue()));
        }
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.NOT)) {
        predicate = builder.and(predicate, builder.notEqual(path,  param.getValue()));
      } else if (param.getOperation().equalsIgnoreCase(SearchCriteria.NOT_LIKE)) {
        predicate = builder.and(predicate, builder.notLike(path,  param.getValue() + ""));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

