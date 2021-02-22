package com.delta.criteria;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2020-02-22
 */
public class SearchCriteria {
  public static final String GREATER_THAN = ">";

  public static final String GREATER_THAN_EQUAL = ">=";

  public static final String LESS_THAN = "<";

  public static final String LESS_THAN_EQUAL = "<=";

  public static final String EQUALS = "=";

  public static final String LIKE = ":";

  public static final String NOT = "~";

  public static final String NOT_LIKE = "~:";

  @Getter
  @Setter
  private String key;

  @Getter
  @Setter
  private String operation;

  @Getter
  @Setter
  private Object value;

  public SearchCriteria(String key, String operation, Object value) {
    this.key = key;
    this.operation = operation;
    this.value = value;
  }
}
