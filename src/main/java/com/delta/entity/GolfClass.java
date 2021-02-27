package com.delta.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
@Entity
@ApiModel(value = "Golf Class", description = "高爾夫課程")
public class GolfClass extends BaseEntity {

  private static final long serialVersionUID = 3138912709489043965L;

  @Getter
  @Setter
  @ApiModelProperty(value = "課程日期")
  private String classDate;
  
  @Getter 
  @Setter
  @ApiModelProperty(value = "教練")
  private String weekDate;
  
  @Getter 
  @Setter
  @ApiModelProperty(value = "教練")
  private String coach;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "剩餘數量")
  private Integer remindAccount;
  
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "golfClass", orphanRemoval = true)
  @Getter
  @Setter
  private Set<Registration> registrations = new HashSet<>();
}
