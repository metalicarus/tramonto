package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.TestVectorDto;
import br.com.wsss.tramonto.entity.TestVector;

@Mapper(componentModel = "spring")
public interface TestVectorMapper {

	TestVector dtoToEntity(TestVectorDto dto);
	TestVectorDto entityToDto(TestVector entity);
}
