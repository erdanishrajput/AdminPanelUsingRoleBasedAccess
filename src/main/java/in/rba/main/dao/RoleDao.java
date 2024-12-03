package in.rba.main.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;
import in.rba.main.repositories.RoleRepo;

@Repository
public class RoleDao {

	@Autowired
	private RoleRepo roleRepo;
	
//  <--------------------------------------------------------------------------------------------------->
	
	public Optional<RoleEntity> roleByRoleName(RoleEnum roleEnum) {
		return roleRepo.findByRoleName(roleEnum);
		
	}
	
//  <--------------------------------------------------------------------------------------------------->
	
}