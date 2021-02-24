package com.delta.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delta.assembler.GolfClassResourceAssembler;
import com.delta.dto.GolfClassDto;
import com.delta.entity.GolfClass;
import com.delta.service.GolfClassService;
import com.delta.service.RegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-23
 */
@Slf4j
@Api(value = "Region", tags = "課程管理")
@RestController
public class GolfClassController {

  @Autowired
  private GolfClassService golfClassService;

  @Autowired
  private RegistrationService registrationService;

  @Autowired
  private GolfClassResourceAssembler assembler;

  @GetMapping("")
  public String welcome() {
    return "hello heroku";
  }
  
  @ApiOperation(value = "新增課程")
  @PostMapping("/golf/add/")
  public void create(
      @RequestBody GolfClassDto dto) {
    try {
      golfClassService.create(dto);
    } catch (Exception e) {
      log.error(e.toString(), e);
    }
  }

  @ApiOperation(value = "刪除簡章")
  @DeleteMapping("/golf/delete/{uuid}")
  public void delete(@ApiParam(value = "Delete Item's uuid") @PathVariable String uuid) {
    try {
      golfClassService.delete(uuid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @ApiOperation(value = "取得所有課程清單")
  @GetMapping(value = "/golf/getAll/")
  public ResponseEntity<?> getAll() {
    try {
      List<GolfClass> regions = golfClassService.findAll();
      return ResponseEntity.ok(new Resources<>(assembler.toResources(regions)));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
    }
  }

  @ApiOperation(value = "新增報名者")
  @PutMapping("/golf/update/{uuid}/{name}")
  public void update(@ApiParam(value = "Update item's uuid") @PathVariable String uuid,
      @ApiParam(value = "Add registration name") @PathVariable String name) {
    try {
      log.info("Update uuid: " + uuid);
      golfClassService.update(uuid, name);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @ApiOperation(value = "刪除報名者")
  @DeleteMapping("/golf/delete/registration/{uuid}")
  public void deleteRegistration(@ApiParam(value = "Delete registration uuid") @PathVariable String uuid) {
    try {
      registrationService.delete(uuid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
