package com.example.doprequestform;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.cors(cors -> {})
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
			.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
			.requestMatchers(HttpMethod.POST, "/api/requests").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/authorization").permitAll()
			.requestMatchers(HttpMethod.POST, "/api/authorization/create").hasRole("ADMIN")
			.requestMatchers(HttpMethod.PUT, "/api/authorization/admin/password").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE, "/api/authorization/employee/{id}").hasRole("ADMIN")
			.anyRequest().authenticated()
			);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:5173"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
