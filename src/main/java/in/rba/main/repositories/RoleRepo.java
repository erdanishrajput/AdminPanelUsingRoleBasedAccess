package in.rba.main.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;



@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long>{
	Optional<RoleEntity> findByRoleName(RoleEnum name);
}