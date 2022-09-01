package com.delta.entity;

import javax.persistence.Entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: ACE.CHIU
 * @create: 2022-09-01
 */
@Entity
@Data
@ApiModel(value = "Title", description = "標題資訊")
public class Title extends BaseEntity {
  private static final long serialVersionUID = -7703383982608633491L;
  private String pageName;
  private String title;
  private String subTitle;
}
