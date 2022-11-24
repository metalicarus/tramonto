package br.com.wsss.tramonto.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.service.contract.TestService;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

	@Autowired
	private TestService service;
	
	@PutMapping
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TestDto> save(@RequestBody TestDto dto) {
		return ResponseEntity.ok(service.save(dto));
	}
}
