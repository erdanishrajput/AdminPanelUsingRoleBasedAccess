package in.rba.main.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.rba.main.entities.RoleEnum;
import in.rba.main.entities.UserEntity;



@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{
	
//	method to check if username already exist in db or not.
	public boolean existsByUsername(String username);
	
//	method to find user by username.
	public Optional<UserEntity> getByUsername(String username);
	
//	public Optional<UserEntity> getByuserId(Long id);
	
//	method to delete user by username in user
	public Integer deleteByUsername(String username);
 
	List<UserEntity> findByRoleRoleName(RoleEnum roleName);
	
}