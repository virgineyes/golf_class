package com.delta.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-23
 */
@Getter
@Setter
public class GolfClassDto {

  private String classDate;
  
  private String weekDate;

  private String coach;
  
  private Integer remindAccount;
  
  private Boolean additional;
}
