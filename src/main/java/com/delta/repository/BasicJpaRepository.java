package com.delta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
@NoRepositoryBean
public interface BasicJpaRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
  Optional<T> findByUuid(String uuid);
}
