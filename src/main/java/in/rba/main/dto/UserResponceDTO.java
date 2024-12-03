package in.rba.main.dto;


import in.rba.main.entities.RoleEntity;
import lombok.Data;

@Data
public class UserResponceDTO {

	private String username;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String email;

	private RoleEntity role;
}