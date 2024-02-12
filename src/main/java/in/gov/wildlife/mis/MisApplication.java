package in.gov.wildlife.mis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class MisApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MisApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder br = new BCryptPasswordEncoder();
		br.encode("Password@10");
		System.out.println(br.encode("123"));
	}
}
