package com.delta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delta.dto.GolfClassDto;
import com.delta.entity.GolfClass;
import com.delta.repository.BasicJpaRepository;
import com.delta.repository.GolfClassRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
@Slf4j
@Service
public class GolfClassService extends BasicService<GolfClass> {

  @Autowired
  private GolfClassRepository repository;

  @Transactional
  public void create(GolfClassDto request) {
    try {
      GolfClass golfClass = new GolfClass();
      golfClass.setClasDate(request.getClasDate());
      golfClass.setCoach(request.getCoach());
      golfClass.setLimitAccount(request.getLimitAccount());
      repository.save(golfClass);
    } catch (Exception e) {
      log.error(e.toString(), e);
    }
  }

  public List<GolfClass> findAll() {
    return repository.findAll();
  }

  @Override
  public BasicJpaRepository<GolfClass> getRepository() {
    return repository;
  }
}
