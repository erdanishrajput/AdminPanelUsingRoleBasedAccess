package in.rba.main.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigFile {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		// This encoder does not perform any hashing and should only be used in
		// development.
		return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
	}

}