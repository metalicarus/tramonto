package br.com.wsss.tramonto.controller.v1;

import java.util.Set;
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

import br.com.wsss.tramonto.dto.input.VectorCategoryDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.service.contract.BaseService;

@RestController
@RequestMapping(value= "/api/v1/vectorCategory")
public class VectorCategoryController {
	
	@Autowired
	private BaseService<VectorCategoryDto, UUID> service;
	
	@PutMapping
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<VectorCategoryDto> save(@RequestBody VectorCategoryDto dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	@GetMapping("{vectorCategoryId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<VectorCategoryDto> findById(@PathVariable UUID vectorCategoryId) {
		return ResponseEntity.ok(service.findById(vectorCategoryId));
	}
	
	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Set<VectorCategoryDto>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PageResponse<VectorCategoryDto>> paginate(@RequestParam("filter") String filter, @RequestParam("page") Integer page,
			@RequestParam("perPage") Integer perPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction) {
		return ResponseEntity.ok(service.paginate(filter, page, perPage, sortBy, Direction.fromString(direction)));
	}
}
