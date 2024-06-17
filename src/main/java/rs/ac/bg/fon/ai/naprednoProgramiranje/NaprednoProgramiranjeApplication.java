package rs.ac.bg.fon.ai.naprednoProgramiranje;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.AppUser;
import rs.ac.bg.fon.ai.naprednoProgramiranje.user.UserRepository;
/**
 * Our Spring app :D
 * @author milos jolovic
 */
@SpringBootApplication
public class NaprednoProgramiranjeApplication {
	/**
	 * Entry point of the app :D
	 * @param args args
	 */
	public static void main(String[] args) {
		SpringApplication.run(NaprednoProgramiranjeApplication.class, args);
	}

	/**
	 * bean for creation of some users in H2.
	 * @param userRepository reference to the user repo
	 * @param encoder reference to the specific encoder we are using
	 * @return executed command
	 */
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
		return args ->{
			userRepository.save(new AppUser("Jack Doe","Jack123", encoder.encode("Jack123"),"ROLE_ADMIN"));
			userRepository.save(new AppUser("Jane Doe","Jane123",encoder.encode("Jane123"),"ROLE_USER"));
			userRepository.save(new AppUser("John Smith","John123",encoder.encode("John123"),"ROLE_USER"));

		};
	}
}
