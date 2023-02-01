package com.clairvoyant.services.skillmatrix.controller;

import java.util.List;
import java.util.Optional;

import com.clairvoyant.services.skillmatrix.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clairvoyant.services.skillmatrix.configuration.SwaggerConfig;
import com.clairvoyant.services.skillmatrix.model.Designation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/designation")
@Api(tags = SwaggerConfig.DESIGNATION)
public class DesignationController {

	@Autowired
	private DesignationService service;

	@PostMapping
	@ApiOperation(value = "Create and update the designation", response = Designation.class)
	public Designation addDesignation(@RequestBody Designation designation) {
		return service.save(designation);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Get designation by id", response = Designation.class)
	public Designation findById(@PathVariable String id, @RequestParam(name = "isActive",defaultValue = "true") Optional<Boolean> isActive) {
		return service.findById(id, isActive);
	}

	@GetMapping
	@ApiOperation(value = "Get all designations", response = List.class)
	public List<Designation> findAll(@RequestParam(name = "isActive",defaultValue = "true")Boolean isActive) {
		return service.findAll(Optional.ofNullable(isActive));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes the designation by id", response = String.class)
	public String delete(@PathVariable String id) {
		Designation status = new Designation();
		status.setId(id);
		service.delete(status);
		return "Record Deleted";
	}

}
