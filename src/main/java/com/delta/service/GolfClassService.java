package com.delta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delta.dto.GolfClassDto;
import com.delta.entity.GolfClass;
import com.delta.entity.Registration;
import com.delta.repository.BasicJpaRepository;
import com.delta.repository.GolfClassRepository;
import com.delta.repository.RegistrationRepository;

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

	@Autowired
	private RegistrationRepository registrationRepository;

	@Transactional
	public void create(GolfClassDto dto) {
		try {
			GolfClass golfClass = new GolfClass();
			golfClass.setClassDate(dto.getClassDate());
			golfClass.setWeekDate(dto.getWeekDate());
			golfClass.setCoach(dto.getCoach());
			golfClass.setRemindAccount(dto.getRemindAccount());
			repository.save(golfClass);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
	}

	@Transactional
	public boolean update(String uuid, String name) {
		try {
			GolfClass golfClass = repository.findByUuid(uuid).orElse(new GolfClass());
			if (golfClass.getRemindAccount() == 0) {
				return false;
			} else {
				golfClass.setRemindAccount(golfClass.getRemindAccount() - 1);
				Registration registration = new Registration();
				registration.setName(name);
				registration.setGolfClass(golfClass);
				golfClass.getRegistrations().add(registration);
				repository.save(golfClass);
				return true;
			}
		} catch (Exception e) {
			log.error(e.toString(), e);
			return false;
		}
	}

	@Transactional
	public void addRemindAccound(String uuid, String classUuid) {
		try {
			registrationRepository.deleteByUuid(uuid);
			GolfClass golfClass = repository.findByUuid(classUuid).orElse(new GolfClass());
			golfClass.setRemindAccount(golfClass.getRemindAccount() + 1);
			repository.save(golfClass);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
	}

	public GolfClass findByUuid(String uuid) {
		return repository.findByUuid(uuid).orElse(new GolfClass());
	}

	public List<GolfClass> findAll() {
		return repository.findAll();
	}

	public List<GolfClass> findByWeekDate(String weekDate) {
		return repository.findByWeekDate(weekDate);
	}

	@Override
	public BasicJpaRepository<GolfClass> getRepository() {
		return repository;
	}
}
