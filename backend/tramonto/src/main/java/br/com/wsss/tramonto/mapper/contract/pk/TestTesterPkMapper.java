package br.com.wsss.tramonto.mapper.contract.pk;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.wsss.tramonto.dto.input.UserDto;
import br.com.wsss.tramonto.entity.TestTester;
import br.com.wsss.tramonto.mapper.contract.TestMapper;

@Mapper(componentModel = "spring", uses = { TestMapper.class })
public interface TestTesterPkMapper {

	@Mapping(source = "pk.tester.firstName", target = "firstName")
	@Mapping(source = "pk.tester.lastName", target = "lastName")
	@Mapping(source = "pk.tester.email", target = "email")
	@Mapping(source = "pk.tester.id", target = "id")
	UserDto toUserDto(TestTester entity);
	
	
	@Mapping(target = "pk.tester.firstName", source = "firstName")
	@Mapping(target = "pk.tester.lastName", source = "lastName")
	@Mapping(target = "pk.tester.email", source = "email")
	@Mapping(target = "pk.tester.id", source = "id")
	TestTester toTestTester(UserDto dto);
}
