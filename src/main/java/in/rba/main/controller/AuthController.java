package in.rba.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.rba.main.dto.UserRequestDTO;
import in.rba.main.dto.UserResponceDTO;
import in.rba.main.dto.UserResponceJWTDTO;
import in.rba.main.entities.RoleEntity;
import in.rba.main.entities.RoleEnum;
import in.rba.main.security.AuthService;
import in.rba.main.security.JWTUtil;
import in.rba.main.services.JWTUserService;
import in.rba.main.services.RoleService;
import in.rba.main.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JWTUserService jwtUserService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private RoleService roleService;

//  <--------------------------------------------------------------------------------------------------->

//	http://localhost:1000/api/auth/user/signin
	@PostMapping("/user/signin")
	public ResponseEntity<UserResponceDTO> signInUser(@RequestBody UserRequestDTO userRequestDTO) {
		RoleEntity role = roleService.getRole(RoleEnum.USER);
		return new ResponseEntity<UserResponceDTO>(userService.saveUser(userRequestDTO, role), HttpStatus.OK);
	}
//  <--------------------------------------------------------------------------------------------------->
	
	
//	http://localhost:1000/api/auth/login
	@PostMapping("/login")
	public ResponseEntity<UserResponceJWTDTO> login(@RequestParam String username, @RequestParam String password) {

		this.authService.doAuthenticate(username, password);
		UserDetails userDetails = this.jwtUserService.loadUserByUsername(username);
		String token = this.jwtUtil.generateToken(username);
		
		String role = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority().replace("ROLE_", "")) 
                .findFirst()
                .orElse(null);

		UserResponceJWTDTO userResponceJWTDTO = UserResponceJWTDTO
				.builder()
				.token(token)
				.username(userDetails.getUsername())
				.role(role)
				.build();
		return new ResponseEntity<UserResponceJWTDTO>(userResponceJWTDTO, HttpStatus.OK);

	}
	
//  <--------------------------------------------------------------------------------------------------->
	
}