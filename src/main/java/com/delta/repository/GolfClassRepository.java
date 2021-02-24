package com.delta.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delta.entity.GolfClass;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-24
 */
@Repository
public interface GolfClassRepository extends BasicJpaRepository<GolfClass> {
	 List<GolfClass> findByWeekDate(String weekDate);
}
