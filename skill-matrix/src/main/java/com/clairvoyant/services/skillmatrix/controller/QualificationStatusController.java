package com.clairvoyant.services.skillmatrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.services.skillmatrix.configuration.SwaggerConfig;
import com.clairvoyant.services.skillmatrix.model.QualificationStatus;
import com.clairvoyant.services.skillmatrix.service.QualificationStatusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/qualificationstatus")
@Api(tags = SwaggerConfig.QUALIFICATION_STATUS)
public class QualificationStatusController {

	@Autowired
	private QualificationStatusService service;

	@PostMapping
	@ApiOperation(value = "Create and update qualification status", response = QualificationStatus.class)
	public QualificationStatus addqualificationStatus(@RequestBody QualificationStatus qualificationStatus) {
		return service.save(qualificationStatus);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Gets qualification status by id", response = QualificationStatus.class)
	public QualificationStatus findById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping
	@ApiOperation(value = "Get all qualification status", response = List.class)
	public List<QualificationStatus> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes the qualification status by id")
	public String delete(@PathVariable String id) {
		QualificationStatus status = new QualificationStatus();
		status.setId(id);
		service.delete(status);
		return "Record Deleted";
	}

}
