package in.rba.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.rba.main.dao.UserDao;
import in.rba.main.dto.UserRequestDTO;
import in.rba.main.dto.UserResponceDTO;
import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;
import in.rba.main.entities.UserEntity;
import in.rba.main.exceptionHandlers.BadCredentialsException;
import in.rba.main.exceptionHandlers.UserNameAllreadyExistException;
import in.rba.main.exceptionHandlers.UsernameNotFoundException;
import in.rba.main.translater.UserTranslater;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserTranslater userTranslater;

//  <--------------------------------------------------------------------------------------------------->

	public Boolean checkUserExist(String username) {
		return userDao.userNameAlreadyExist(username);
	}

//  <--------------------------------------------------------------------------------------------------->

	public UserResponceDTO getUser(String username) {
		Optional<UserEntity> userOptional = userDao.userByUsername(username);
		UserEntity userEntity = userOptional.orElseThrow(() -> new UsernameNotFoundException("user not found..."));

		return userTranslater.userEntityToDto(userEntity);
	}
	
//  <--------------------------------------------------------------------------------------------------->
	
	public UserResponceDTO getUserById(Long id)  {
		Optional<UserEntity> user = userDao.getUserById(id);
		UserEntity userEntity = user.orElseThrow(() -> new UsernameNotFoundException("userid not found..."));
		return userTranslater.userEntityToResponceDTO(user);
	}

//  <--------------------------------------------------------------------------------------------------->

	public UserResponceDTO saveUser(UserRequestDTO userRequestDTO, RoleEntity roleEntity)
			throws UserNameAllreadyExistException {

		UserEntity user = UserEntity.builder().firstName(userRequestDTO.getFirstName())
				.lastName(userRequestDTO.getLastName()).username(userRequestDTO.getUsername())
				.phoneNumber(userRequestDTO.getPhoneNumber()).email(userRequestDTO.getEmail())
				.password(userRequestDTO.getPassword()).role(roleEntity).build();

		if (checkUserExist(user.getUsername())) {
			throw new UserNameAllreadyExistException("user with same username allready exist...");
		}

		UserEntity savedUser = userDao.saveUserEntity(user);
		UserResponceDTO userResponceDTO = userTranslater.userEntityToDto(savedUser);

		return userResponceDTO;

	}

//  <--------------------------------------------------------------------------------------------------->

	public UserResponceDTO loginUser(String username, String password) throws UsernameNotFoundException {
		Optional<UserEntity> userOptional = userDao.userByUsername(username);

		UserEntity userEntity = userOptional.orElseThrow(() -> new UsernameNotFoundException("user not found..."));

		if (!userEntity.getPassword().equals(password)) {
			throw new BadCredentialsException("Incorrect password.");
		}
		UserResponceDTO createrResDTO = userTranslater.userEntityToDto(userEntity);

		return createrResDTO;

	}

//  <--------------------------------------------------------------------------------------------------->

//	public List<UserResponceDTO> getAllUsers() {
//
//		List<UserEntity> userEntities = userDao.getAllUsers();
//		return userTranslater.userListEntityToDto(userEntities);
//	}

	
	// <------------------------------------------------------------------------------>//
	
	
	 public List<UserResponceDTO> getUsersByRole(RoleEnum roleEnum) {
	        List<UserEntity> userEntities = userDao.getUsersByRole(roleEnum);
	        return userTranslater.userListEntityToDto(userEntities);
	    }
	
//	 public List<UserResponceDTO> getAllUsers(){
//		 return this.userTranslater.userListEntityToDto(userDao.getUsersByRole(RoleEnum.USER));
//	 }
//	 
//	 public List<UserResponceDTO> getAllAdmin(){
//		 return this.userTranslater.userListEntityToDto(userDao.getUsersByRole(RoleEnum.ADMIN));
//	 }
	 
//  <--------------------------------------------------------------------------------------------------->

	public Integer removeUser(String username) throws UsernameNotFoundException {
		Integer deletedUser = userDao.deleteUser(username);

		if (deletedUser == 0) {
		    throw new UsernameNotFoundException("User with username " + username + " not found.");
		}

		return deletedUser;
	}

//  <--------------------------------------------------------------------------------------------------->

	public UserResponceDTO updateUser(String username, UserRequestDTO userRequestDTO) throws Exception {

		UserEntity user = userDao.userByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("username not found..."));

		user.setFirstName(userRequestDTO.getFirstName());
		user.setLastName(userRequestDTO.getLastName());
		user.setPhoneNumber(userRequestDTO.getPhoneNumber());
		user.setEmail(userRequestDTO.getEmail());

		UserEntity updatedUser = userDao.updateUser(user);

		return userTranslater.userEntityToDto(updatedUser);

	}

//  <--------------------------------------------------------------------------------------------------->

}