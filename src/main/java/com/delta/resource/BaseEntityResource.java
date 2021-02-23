package com.delta.resource;

import org.springframework.hateoas.ResourceSupport;

import com.delta.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-23
 */
public abstract class BaseEntityResource<T extends BaseEntity> extends ResourceSupport {

  public abstract String getResourceType();

  @JsonIgnore
  public abstract T getEntity();

  @JsonProperty("uuid")
  public String getObjectId() {
    return getEntity().getUuid();
  }
}