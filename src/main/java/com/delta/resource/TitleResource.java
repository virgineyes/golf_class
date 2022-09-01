package com.delta.resource;

import com.delta.entity.Title;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Getter;
import lombok.Setter;


/**
 * @author: ACE.CHIU
 * @create: 2022-09-01
 */
public class TitleResource extends BaseEntityResource<Title> {

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonUnwrapped
  @Setter
  @Getter
  private Title entity;
  
  @Override
  public String getResourceType() {
    return Title.class.getSimpleName();
  }

}
