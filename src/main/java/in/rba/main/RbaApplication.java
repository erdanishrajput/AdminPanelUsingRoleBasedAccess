package in.rba.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RbaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbaApplication.class, args);
		System.out.println("started");
	}

}
