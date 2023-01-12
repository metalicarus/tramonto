package br.com.wsss.tramonto.controller.v1;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsss.tramonto.domain.type.Status;
import br.com.wsss.tramonto.dto.input.ToolDto;

@RestController
@RequestMapping(value= "/api/v1/tool")
public class ToolController {

	@GetMapping("/findAll")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Set<ToolDto>> findAll() {
		Set<ToolDto> dtos = new HashSet<>();
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Acunetix",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Aircrack-ng Suite",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Aquatone",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Burp Suite",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"DNSRecon",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"HP WebInspect",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"IBM AppScan",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Maltego",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Metasploit",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Nessus",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Nexpose",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Nikto",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"NMap",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"OpenVAS",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"OWASP ZAP",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"SET",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"SQLMap",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"The Harvester",
				"",
				""));
		dtos.add(new ToolDto(UUID.randomUUID(),
				Status.ACTIVE,
				Date.from(Instant.now()),
				Date.from(Instant.now()),
				"Wireshark",
				"",
				""));
		return ResponseEntity.ok(dtos);
	}
}
