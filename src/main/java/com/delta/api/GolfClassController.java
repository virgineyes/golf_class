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
  private GolfClassResourceAssembler assembler;

//  @GetMapping("*")
//  public String welcomeAll() {
//    return "welcome all";
//  }
  
  @GetMapping("")
  public String welcome() {
    return "welcome ";
  }

  @ApiOperation(value = "新增課程")
  @PostMapping("/golf/add/")
  public GolfClass create(@RequestBody GolfClassDto dto) {
    try {
      return golfClassService.create(dto);
    } catch (Exception e) {
      log.error(e.toString(), e);
    }
	return null;
  }

  @ApiOperation(value = "刪除課程")
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
      List<GolfClass> golfClassList = golfClassService.findAll();
      return ResponseEntity.ok(new Resources<>(assembler.toResources(golfClassList)));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
    }
  }

  @ApiOperation(value = "依據日期取得所有課程清單")
  @GetMapping(value = "/golf/getAll/{weekDate}/{additional}")
  public ResponseEntity<?> getAllByWeekDate(@ApiParam(value = "Week Date") @PathVariable String weekDate,
      @ApiParam(value = "additional") @PathVariable boolean additional) {
    try {
      List<GolfClass> golfClassList = golfClassService.findByWeekDateAndAdditional(weekDate, additional);
      return ResponseEntity.ok(new Resources<>(assembler.toResources(golfClassList)));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
    }
  }

  @ApiOperation(value = "新增報名者")
  @PutMapping("/golf/update/{name}")
  public ResponseEntity<?> update(@RequestBody List<String> uuids,
      @ApiParam(value = "Add registration name") @PathVariable String name) {
    try {
      StringBuilder result = new StringBuilder();
      uuids.forEach(uuid -> {
        log.info("Update uuid: " + uuid);
        String weekDate = "";
        GolfClass golfClass = golfClassService.findByUuid(uuid);
        if (golfClass.getWeekDate().equals("6")) {
        	weekDate = "六";
        } else if (golfClass.getWeekDate().equals("0")) {
        	weekDate = "日";
        }
        
        if (golfClassService.update(uuid, name)) {
          result.append(golfClass.getClassDate());
          result.append(" (");
          result.append(weekDate);
          result.append(")");
          result.append(" - ");
          result.append(golfClass.getCoach());
          result.append("(剩餘:" + golfClass.getRemindAccount() + ")");
          result.append(" <b>報名成功</b>");
          result.append("<br/>");
        } else {
          result.append(golfClass.getClassDate());
          result.append(" (");
          result.append(weekDate);
          result.append(") ");
          result.append(" - ");
          result.append(golfClass.getCoach());
          result.append("(剩餘:" + golfClass.getRemindAccount() + ")");
          result.append(" <b style=\"color:red\">報名失敗</b>");
          result.append("<br/>");
        }
      });
      return ResponseEntity.accepted().body(new Resource<>(result.toString()));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
    }
  }

  @ApiOperation(value = "刪除報名者")
  @DeleteMapping("/golf/delete/registration/{classUuid}/{uuid}")
  public void deleteRegistration(@ApiParam(value = "Delete registration uuid") @PathVariable String uuid,
      @ApiParam(value = "golf class uuid") @PathVariable String classUuid) {
    try {
      golfClassService.addRemindAccound(uuid, classUuid);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
