package com.delta.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
public interface PreCriteriaBuilder {
  Predicate preBuild(Predicate predicate, CriteriaQuery query, CriteriaBuilder builder, Root root);
}

