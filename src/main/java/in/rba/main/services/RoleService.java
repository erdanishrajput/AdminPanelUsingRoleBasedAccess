package in.rba.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rba.main.dao.RoleDao;
import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
//  <--------------------------------------------------------------------------------------------------->
	
	public RoleEntity getRole(RoleEnum roleEnum) {
		
		RoleEntity role = roleDao.roleByRoleName(roleEnum)
                .orElseThrow(() -> new RuntimeException("Role not found: " + RoleEnum.USER));
		
		return role;
		
	}

//  <--------------------------------------------------------------------------------------------------->
	
}