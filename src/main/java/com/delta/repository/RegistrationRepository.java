package com.delta.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.delta.entity.Registration;


/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
@Repository
public interface RegistrationRepository extends BasicJpaRepository<Registration> {
	
	@Modifying
	@Query(value = "delete from Registration r WHERE r.uuid = :uuid")
	void deleteByUuid(@Param("uuid") String uuid);
}
