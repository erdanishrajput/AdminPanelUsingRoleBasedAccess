package in.rba.main.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponceJWTDTO {
	
	private String token;
	private String username;

	private String role;
}