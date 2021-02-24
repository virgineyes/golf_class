package com.delta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.entity.Registration;
import com.delta.repository.BasicJpaRepository;
import com.delta.repository.RegistrationRepository;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-24
 */
//@Slf4j
@Service
public class RegistrationService extends BasicService<Registration> {
  
  @Autowired
  private RegistrationRepository repository;

  @Override
  public BasicJpaRepository<Registration> getRepository() {
    return repository;
  }
}
