package br.com.wsss.tramonto.controller.v1.handle;

import java.io.Serializable;
import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TramontoStandardError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
