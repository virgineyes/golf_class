package com.delta.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-23
 */
@Getter
@Setter
public class GolfClassDto {

  private LocalDate clasDate;

  private String coach;
  
  private Integer limitAccount;
}
