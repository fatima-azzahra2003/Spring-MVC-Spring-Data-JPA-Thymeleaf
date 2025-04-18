package ma.enset.hopitalTP3;

import ma.enset.hopitalTP3.entities.Patient;
import ma.enset.hopitalTP3.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HopitalTp3Application implements CommandLineRunner {

	private PatientRepository patientRepository;

	public static void main(String[] args) {

		SpringApplication.run(HopitalTp3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		patientRepository.save(new Patient(null,"John", "Doe", new Date(), false, 10));
		patientRepository.save(new Patient(null,"lara", "Doe", new Date(), true, 5));





	}
}
