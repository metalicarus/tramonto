package br.com.wsss.tramonto.mapper.contract;

import org.mapstruct.Mapper;

import br.com.wsss.tramonto.dto.input.VectorCategoryDto;
import br.com.wsss.tramonto.entity.VectorCategory;

@Mapper(componentModel= "spring")
public interface VectorCategoryMapper extends BaseMapper<VectorCategoryDto, VectorCategory>{

	VectorCategoryDto vectorCategoryToDto(VectorCategory entity);
	VectorCategory vectorCategoryDtoToEntity(VectorCategoryDto dto);
}
