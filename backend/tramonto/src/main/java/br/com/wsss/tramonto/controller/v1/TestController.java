package br.com.wsss.tramonto.controller.v1;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.wsss.tramonto.domain.type.Approval;
import br.com.wsss.tramonto.dto.input.TestDto;
import br.com.wsss.tramonto.dto.input.TestVectorDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.dto.output.View;
import br.com.wsss.tramonto.service.contract.TestService;

@RestController
@RequestMapping(value = "/api/v1/test")
public class TestController {

	@Autowired
	private TestService service;

	@PreAuthorize("hasAnyAuthority('TESTER_INTERMEDIARY', 'TESTER_ADVANCED')")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TestDto> save(@RequestBody TestDto dto) {
		return ResponseEntity.ok(service.save(dto));
	}

	@PreAuthorize("hasAnyAuthority('TESTER_ADVANCED', 'TESTER_INTERMEDIARY')")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TestDto> update(@RequestBody TestDto dto) {
		return ResponseEntity.ok(service.update(dto));
	}

	@PreAuthorize("hasAnyAuthority('TESTER_BASIC', 'TESTER_INTERMEDIARY', 'TESTER_ADVANCED')")
	@JsonView(View.Pagination.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PageResponse<TestDto>> paginate(@RequestParam("filter") String filter,
			@RequestParam("page") Integer page, @RequestParam("perPage") Integer perPage,
			@RequestParam("sortBy") String sortBy, @RequestParam("direction") String direction) {
		PageResponse<TestDto> resp = service.paginate(filter, page, perPage, sortBy, Direction.fromString(direction));
		return ResponseEntity.ok(resp);
	}

	@PreAuthorize("hasAnyAuthority('TESTER_BASIC', 'TESTER_INTERMEDIARY', 'TESTER_ADVANCED')")
	@GetMapping("{testId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TestDto> find(@PathVariable UUID testId) {
		return ResponseEntity.ok(service.findById(testId));
	}
	
	@PreAuthorize("hasAnyAuthority('TESTER_BASIC', 'TESTER_INTERMEDIARY', 'TESTER_ADVANCED')")	
	@PostMapping("/addVector")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TestVectorDto> addVector(@RequestParam("testId") UUID testId, @JsonView(View.Crud.class) @RequestBody TestVectorDto dto
			) {
		return ResponseEntity.ok(service.addTestVector(testId, dto));
	}
	
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAnyAuthority('TESTER_INTERMEDIARY', 'TESTER_ADVANCED')")
	@PatchMapping("/changeVectorStatus")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity changeVectorStatus(@RequestParam("vectorId") UUID vectorId, @RequestParam("approval") Approval approval) {
		service.changeVectorStatus(vectorId, approval);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
