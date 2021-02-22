package com.delta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.entity.GolfClass;
import com.delta.repository.BasicJpaRepository;
import com.delta.repository.GolfClassRepository;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
@Service
public class GolfClassService extends BasicService<GolfClass> {

  @Autowired
  private GolfClassRepository repository;

  @Override
  public BasicJpaRepository<GolfClass> getRepository() {
    return repository;
  }
}
