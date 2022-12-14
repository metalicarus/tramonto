package br.com.wsss.tramonto.controller.v1;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.service.contract.TestService;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

	@Autowired
	private TestService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TestDto> save(@RequestBody TestDto dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TestDto> update(@RequestBody TestDto dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PageResponse<TestDto>> paginate(@RequestParam("filter") String filter, @RequestParam("page") Integer page,
			@RequestParam("perPage") Integer perPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction) {
		return ResponseEntity.ok(service.paginate(filter, page, perPage, sortBy, Direction.fromString(direction)));
	}
	
	@GetMapping("{testId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TestDto> find(@PathVariable UUID testId) {
		return ResponseEntity.ok(service.findById(testId));
	}
}
