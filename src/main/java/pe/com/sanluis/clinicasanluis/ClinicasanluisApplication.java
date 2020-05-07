package pe.com.sanluis.clinicasanluis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"pe.com.sanluis.clinicasanluis"})
public class ClinicasanluisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicasanluisApplication.class, args);
	}

}
