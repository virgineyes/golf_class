package com.delta.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delta.assembler.GolfClassResourceAssembler;
import com.delta.dto.GolfClassDto;
import com.delta.entity.GolfClass;
import com.delta.service.GolfClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-23
 */
@Slf4j
@Api(value = "Region", tags = "區域國家管理")
@RestController
public class GolfClassController {

  @Autowired
  private GolfClassService service;
  
  @Autowired
  private GolfClassResourceAssembler assembler;

  @ApiOperation(value = "新增課程")
  @PostMapping("/golf/class/add/")
  public void create(
      @RequestBody GolfClassDto request
  ) {
    try {
      service.create(request);
    } catch ( Exception e ) {
      log.error(e.toString(), e);
    }
  }
  
  @ApiOperation(value = "取得所有課程清單")
  @GetMapping(value = "/golf/all")
  public ResponseEntity<?> getAll() {
    try {
      List<GolfClass> regions = service.findAll();
      return ResponseEntity.ok(new Resources<>(assembler.toResources(regions)));
    } catch ( Exception e ) {
      return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
    }
  }
}
