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

import br.com.wsss.tramonto.dto.input.StrategyDto;
import br.com.wsss.tramonto.dto.output.PageResponse;
import br.com.wsss.tramonto.service.contract.StrategyService;

@RestController
@RequestMapping(value= "/api/v1/strategy")
public class StrategyController {

	@Autowired
	private StrategyService service;
	
	@PutMapping
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<StrategyDto> save(@RequestBody StrategyDto dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	@GetMapping("{strategyId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<StrategyDto> findById(@PathVariable UUID strategyId) {
		return ResponseEntity.ok(service.findById(strategyId));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PageResponse<StrategyDto>> paginate(@RequestParam("filter") String filter, @RequestParam("page") Integer page,
			@RequestParam("perPage") Integer perPage,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("direction") String direction) {
		return ResponseEntity.ok(service.paginate(filter, page, perPage, sortBy, Direction.fromString(direction)));
	}
}
