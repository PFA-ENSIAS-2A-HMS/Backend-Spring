package com.example.hmspfa;

import com.example.hmspfa.entities.Admin;
import com.example.hmspfa.entities.Hospital;
import com.example.hmspfa.entities.SuperAdmin;
import com.example.hmspfa.enums.Gender;
import com.example.hmspfa.enums.Role;
import com.example.hmspfa.services.EmailSenderService;
import com.example.hmspfa.services.SuperAdminService;
import com.example.hmspfa.services.implementations.AuthenticationServiceImpl;
import com.example.hmspfa.services.implementations.PasswordGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;

@SpringBootApplication
public class HmsPfaApplication {
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private SuperAdminService superAdminService;
	@Autowired
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private  AuthenticationServiceImpl authenticationService;
	@Autowired
	private  PasswordGeneratorService passwordGeneratorService;

	public static void main(String[] args) {
		SpringApplication.run(HmsPfaApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE")
						.allowedHeaders("*");
			}
		};
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initialize() {
		Admin superAdmin = new Admin();
		superAdmin.setId(1L);
		superAdmin.setFirstName("SUPER");
		superAdmin.setLastName("ADMIN");
		superAdmin.setEmail("abderrazzak.nfissi@um5r.ac.ma");
		superAdmin.setGender(Gender.MALE);
		superAdmin.setRole(Role.SUPER_ADMIN);
		superAdmin.setDate_of_birth(LocalDate.of(2000, 9, 14));
		superAdmin.setImage_url("images/ADMIN.png");
		String password = passwordGeneratorService.generatePassword(10);
		superAdmin.setPassword(password);
		emailSenderService.sendEmail(superAdmin.getEmail(), "Notification: System Administrator Credentials", "Hello,\n\nCongratulations! You have been assigned as the System Administrator. Please find below your login information:\n\nEmail: " + superAdmin.getEmail() + "\nPassword: " + password + "\n\nFeel free to contact us if you have any questions or concerns.\n\nBest regards,\n[The Hospital Management System]");
		superAdmin.setPassword(passwordEncoder.encode(superAdmin.getPassword()));
		//superAdminService.saveSuperAdmin(superAdmin);
		authenticationService.registerAdmin(superAdmin);
	}

}
