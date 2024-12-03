package in.rba.main.seeder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import in.rba.main.dto.UserRequestDTO;
import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;
import in.rba.main.entities.UserEntity;
import in.rba.main.repositories.RoleRepo;
import in.rba.main.repositories.UserRepo;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	public AdminSeeder(UserRepo userRepo, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.createSuperAdmin();

	}

	private void createSuperAdmin() {
		UserRequestDTO userDto = new UserRequestDTO();
		userDto.setUsername("danish");
		userDto.setPassword("123");

		Optional<RoleEntity> optionalRole = roleRepo.findByRoleName(RoleEnum.SUPER_ADMIN);
		Optional<UserEntity> optionalUser = userRepo.getByUsername(userDto.getUsername());
		
		if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }
		
		UserEntity user = UserEntity.builder()
				.username(userDto.getUsername())
				.password(userDto.getPassword())
				.role(optionalRole.get())
				.build();
		
		userRepo.save(user);

	}

}