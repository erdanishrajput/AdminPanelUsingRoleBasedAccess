package in.rba.main.translater;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.rba.main.dto.UserRequestDTO;
import in.rba.main.dto.UserResponceDTO;
import in.rba.main.entities.UserEntity;

@Component
public class UserTranslater {
	
	@Autowired
	private ModelMapper modelMapper;
	
//	this fun map entity to dto in user
	public UserResponceDTO userEntityToDto(UserEntity userEntity) {
		return modelMapper.map(userEntity, UserResponceDTO.class);
	}
	
	// this fun map optional<UserEntity> to UserResponseDto
	public UserResponceDTO userEntityToResponceDTO(Optional<UserEntity> userEntity) {
		return modelMapper.map(userEntity, UserResponceDTO.class);
				
	}

//	this fun map dto to entity in user
	public UserEntity userDtoToEntity(UserRequestDTO userRequestDTO) {
		return modelMapper.map(userRequestDTO, UserEntity.class);
	}
	
//	for map all list user
	public List<UserResponceDTO> userListEntityToDto(List<UserEntity> userEntities) {
		return userEntities.stream().map(this::userEntityToDto).collect(Collectors.toList());
	}

}