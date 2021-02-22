package com.delta.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import lombok.Getter;
import lombok.Setter;
/**
 * @author: ACE.CHIU
 * @create: 2020-03-18
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity implements Serializable, Identifiable<Long> {

  private static final long serialVersionUID = 4825301256750350895L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  protected Long id;

  @Getter
  @Setter
  protected String uuid = UUID.randomUUID().toString();

  @Getter
  @Setter
  @Version
  protected Long updateCount;  

  @CreatedDate
  @Getter
  @Setter
  protected LocalDateTime created;

  @LastModifiedDate
  @Getter
  @Setter
  protected LocalDateTime lastModified;

  @CreatedBy
  @Getter
  @Setter
  protected String createdBy;   

  @LastModifiedBy
  @Getter
  @Setter
  protected String lastModifiedBy; 
}
