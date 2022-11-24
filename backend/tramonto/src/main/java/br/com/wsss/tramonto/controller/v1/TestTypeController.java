package br.com.wsss.tramonto.controller.v1;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsss.tramonto.dto.input.TestTypeDto;
import br.com.wsss.tramonto.service.contract.TestTypeService;

@RestController
@RequestMapping(value = "/api/v1/test-type")
public class TestTypeController {

	@Autowired
	private TestTypeService service;
	
	@PutMapping
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TestTypeDto> save(@RequestBody TestTypeDto dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	@GetMapping("{testTypeId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TestTypeDto> find(@PathVariable UUID testTypeId) {
		return ResponseEntity.ok(service.findById(testTypeId));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Set<TestTypeDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
}
