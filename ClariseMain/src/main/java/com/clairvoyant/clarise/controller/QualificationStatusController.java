package com.clairvoyant.clarise.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.clarise.model.QualificationStatus;
import com.clairvoyant.clarise.service.QualificationStatusService;

@RestController
@RequestMapping("/v1/qualificationstatus")
public class QualificationStatusController {

	@Autowired
	private QualificationStatusService service;

	@PostMapping
	public QualificationStatus addqualificationStatus(@RequestBody QualificationStatus qualificationStatus) {
		return service.save(qualificationStatus);
	}

	@GetMapping("/{id}")
	public QualificationStatus findById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping
	public List<QualificationStatus> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		QualificationStatus status = new QualificationStatus();
		status.setId(id);
		service.delete(status);
		return "Record Deleted";
	}

}
