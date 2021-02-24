package com.delta.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-24
 */
@Entity
@ApiModel(value = "Registration", description = "報名者")
public class Registration extends BaseEntity {
  private static final long serialVersionUID = -66606257433452604L;

  @ManyToOne
  @JsonBackReference
  @Getter
  @Setter
  private GolfClass golfClass;
  
  @Getter
  @Setter
  private String name;
}
