package com.delta.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.delta.entity.Title;

/**
 * @author: ACE.CHIU
 * @create: 2022-09-01
 */
@Repository
public interface TitleRepository extends BasicJpaRepository<Title> {
  Optional<Title> findByPageName(String pageName);
}
