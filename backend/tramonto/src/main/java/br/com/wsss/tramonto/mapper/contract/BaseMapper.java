package br.com.wsss.tramonto.mapper.contract;

public interface BaseMapper<DTO, ENTITY> {
	DTO toDto(ENTITY entity);	
	ENTITY toEntity(DTO dto);
}
