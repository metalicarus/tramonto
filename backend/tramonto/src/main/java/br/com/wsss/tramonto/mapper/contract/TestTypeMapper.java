package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.TestTypeDto;
import br.com.wsss.tramonto.entity.TestType;

 

@Mapper(componentModel= "spring")
public interface TestTypeMapper  extends BaseMapper<TestTypeDto, TestType>{
	TestTypeDto testTypeToTestTypeDto(TestType entity);
	TestType testTypeDtoToTestType(TestTypeDto dto);
}
