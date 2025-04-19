package ma.enset.hopitalTP3;

import ma.enset.hopitalTP3.entities.Patient;
import ma.enset.hopitalTP3.repository.PatientRepository;
import ma.enset.hopitalTP3.security.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HopitalTp3Application implements CommandLineRunner {

	private final AccountService accountService;
	private PatientRepository patientRepository;

	public HopitalTp3Application(AccountService accountService) {
		this.accountService = accountService;
	}

	public static void main(String[] args) {

		SpringApplication.run(HopitalTp3Application.class, args);
	}
	@Bean
	CommandLineRunner start(PatientRepository patientRepository) {
		return args -> {
			this.patientRepository = patientRepository;
			patientRepository.save(new Patient(null,"John", "Doe", new Date(), false, 10));
			patientRepository.save(new Patient(null,"lara", "Doe", new Date(), true, 5));
		};
	}
	@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager userDetailsManager) {
		PasswordEncoder passwordEncoder = PasswordEncoder();
		return args -> {

			userDetailsManager.createUser(
					org.springframework.security.core.userdetails.User.withUsername("user")
							.password(passwordEncoder().encode("1234"))
							.roles("USER")
							.build()
			);
			userDetailsManager.createUser(
					org.springframework.security.core.userdetails.User.withUsername("admin")
							.password(passwordEncoder().encode("1234"))
							.roles("USER", "ADMIN")
							.build()
			);
		};

	}
	CommandLineRunner commandLineRunnerUserDetailsManager(JdbcUserDetailsManager userDetailsManager) {
		PasswordEncoder passwordEncoder = PasswordEncoder();
		return args -> {
			accountService .addNewUser("user", "1234", "1234");
			accountService .addNewUser("admin", "1234", "1234");
			accountService .addNewRole("USER");
			accountService .addNewRole("ADMIN");
			accountService .addRoleToUser("user", "USER");
			accountService .addRoleToUser("admin", "USER");
			accountService .addRoleToUser("admin", "ADMIN");

		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
}


}
