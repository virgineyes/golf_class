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
		return "welcome";
	}

	@ApiOperation(value = "新增課程")
	@PostMapping("/golf/add/")
	public void create(@RequestBody GolfClassDto dto) {
		try {
			golfClassService.create(dto);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
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
	@GetMapping(value = "/golf/getAll/{weekDate}/")
	public ResponseEntity<?> getAllByWeekDate(@ApiParam(value = "Week Date") @PathVariable String weekDate) {
		try {
			String weekDateCh = "日";
			if (weekDate.equals("Sat")) {
				weekDateCh = "六";	
			} else if (weekDate.equals("Sun")) {
				weekDateCh = "日";	
			}
			List<GolfClass> golfClassList = golfClassService.findByWeekDate(weekDateCh);
			return ResponseEntity.ok(new Resources<>(assembler.toResources(golfClassList)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
		}
	}

	@ApiOperation(value = "新增報名者")
	@PutMapping("/golf/update/{uuid}/{name}")
	public ResponseEntity<?> update(@ApiParam(value = "Update item's uuid") @PathVariable List<String> uuids,
			@ApiParam(value = "Add registration name") @PathVariable String name) {
		try {
			StringBuilder result = new StringBuilder();
			uuids.forEach(uuid -> {
				log.info("Update uuid: " + uuid);
				if (golfClassService.update(uuid, name)) {
					GolfClass golfClass = golfClassService.findByUuid(uuid);
					result.append(golfClass.getClassDate());
					result.append(" - ");
					result.append(golfClass.getCoach());
					result.append("(" + golfClass.getRemindAccount() + ")");
					result.append("\n");
				}
			});
			return ResponseEntity.badRequest().body(new Resource<>(result.toString()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new Resource<>(e.toString()));
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
