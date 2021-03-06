package com.android.challenge.yourself.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.android.challenge.yourself.be.repository")
@EntityScan("com.android.challenge.yourself.be.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class ChallengeYourselfApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeYourselfApplication.class, args);
	}
}
