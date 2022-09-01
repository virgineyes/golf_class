package com.delta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delta.dto.TitleDto;
import com.delta.entity.Title;
import com.delta.repository.BasicJpaRepository;
import com.delta.repository.TitleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: ACE.CHIU
 * @create: 2022-09-01
 */
@Slf4j
@Service
public class TitleService extends BasicService<Title> {
  @Autowired
  private TitleRepository repository;

  @Override
  public BasicJpaRepository<Title> getRepository() {
      return repository;
  }
  
  @Transactional
  public boolean upsert(TitleDto dto) {
      try {
          Title title = new Title();
          Optional<Title> titleOpt = repository.findByPageName(dto.getPageName());
          if (titleOpt.isPresent()) {
            title = titleOpt.get();
          } 
          title.setTitle(dto.getTitle());
          title.setSubTitle(dto.getSubTitle());
          repository.save(title);
          return true;
      } catch (Exception e) {
          log.error(e.toString(), e);
          return false;
      }
  }
  
  public Title findByPageName(String pageName) {
    return repository.findByPageName(pageName).orElse(new Title());
}
}
