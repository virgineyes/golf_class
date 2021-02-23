package com.delta.resource;

import com.delta.entity.GolfClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;

public class GolfClassResource extends BaseEntityResource<GolfClass> {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonUnwrapped
  @Setter
  @Getter
  private GolfClass entity;

  @Override
  public String getResourceType() {
    return GolfClass.class.getSimpleName();
  }

}
