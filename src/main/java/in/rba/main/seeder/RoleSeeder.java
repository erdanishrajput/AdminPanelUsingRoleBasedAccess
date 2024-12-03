package in.rba.main.seeder;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;
import in.rba.main.repositories.RoleRepo;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private final RoleRepo roleRepo;

    public RoleSeeder(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
            RoleEnum.USER, "User role",
            RoleEnum.ADMIN, "Administrator role",
            RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );
        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<RoleEntity> optionalRole = roleRepo.findByRoleName(roleName);

            //if role is present than print if not than set role
            
            optionalRole.ifPresentOrElse(
                System.out::println, 
                () -> {
                	RoleEntity roleToCreate = new RoleEntity();

                    roleToCreate.setRoleName(roleName);
                    roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                    roleRepo.save(roleToCreate); 
                }
            );
        });
    }
}