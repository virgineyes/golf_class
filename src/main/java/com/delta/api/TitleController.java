package com.delta.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delta.assembler.TitleResourceAssembler;
import com.delta.dto.TitleDto;
import com.delta.entity.Title;
import com.delta.service.TitleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author: ACE.CHIU
 * @create: 2022-09-01
 */
//@Slf4j
@Api(value = "Title", tags = "標題資訊")
@RestController
public class TitleController {
  
  @Autowired
  private TitleService titleService;
  
  @Autowired
  private TitleResourceAssembler assembler;

  @ApiOperation(value = "取得標題資訊", tags = "title")
  @GetMapping(value = "/title/{pageName}")
  public ResponseEntity<?> find(@ApiParam(value = "pageName") @PathVariable String pageName) {
    try {
      Title title = titleService.findByPageName(pageName);
      return ResponseEntity.ok(assembler.toResource(title));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
    }
  }
  
  @ApiOperation(value = "新增標題資訊", tags = "title")
  @PostMapping(value = "title/add")
  public ResponseEntity<?> upsert(@RequestBody TitleDto dto) throws IOException {
    titleService.upsert(dto);
    return ResponseEntity.ok("Ok");
  }
}
