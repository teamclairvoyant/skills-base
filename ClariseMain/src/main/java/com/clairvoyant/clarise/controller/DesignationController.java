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

import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.service.DesignationService;

@RestController
@RequestMapping("/v1/designation")
public class DesignationController {

	@Autowired
	private DesignationService service;

	@PostMapping
	public Designation addDesignation(@RequestBody Designation designation) {
		return service.save(designation);
	}

	@GetMapping("/{id}")
	public Designation findById(@PathVariable String id) {
		return service.findById(id);
	}

	@GetMapping
	public List<Designation> findAll() {
		return service.findAll();
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable String id) {
		Designation status = new Designation();
		status.setId(id);
		service.delete(status);
		return "Record Deleted";
	}

}
