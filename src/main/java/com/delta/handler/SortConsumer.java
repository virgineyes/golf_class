package com.delta.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;

import lombok.Getter;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
public class SortConsumer implements Consumer<Sort.Order> {

  @Getter
  private Predicate predicate;

  @Getter
  private CriteriaBuilder builder;

  @Getter
  private Root root;

  @Getter
  List<Order> orders = new ArrayList<>();

  public SortConsumer(Predicate predicate, CriteriaBuilder builder, Root root) {
    this.predicate = predicate;
    this.builder = builder;
    this.root = root;
  }

  @Override
  public void accept(Sort.Order order) {
    String[] keys = order.getProperty().split("\\.");
    Path path = root;
    for (String key : keys) {
      path = path.get(key);
    }
    if (order.getDirection().isAscending()) {
      orders.add(builder.asc(path.as(String.class)));
    } else {
      orders.add(builder.desc(path.as(String.class)));
    }
  }
}
