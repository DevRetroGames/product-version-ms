package com.productversion.productversionms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	UserDetailsService users() {
	    UserDetails user = User.builder()
	        .username("user")
	        .password(passwordEncoder().encode("user"))
	        .roles("USER")
	        .build();

	    UserDetails admin = User.builder()
	        .username("admin")
	        .password(passwordEncoder().encode("admin"))
	        .roles("ADMIN")
	        .build();

	    return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		
		http.authorizeHttpRequests(authorize -> authorize
		    .requestMatchers("/swagger-ui/**").permitAll()
		    .requestMatchers("/v3/api-docs/**").permitAll()
		    .requestMatchers("/api/productos/**").hasAnyRole("USER", "ADMIN")
		    .requestMatchers("/api/versiones/**").hasAnyRole("USER", "ADMIN")
		    .anyRequest().authenticated());
		
		http.httpBasic(Customizer.withDefaults());

		return http.build();
	}

}
