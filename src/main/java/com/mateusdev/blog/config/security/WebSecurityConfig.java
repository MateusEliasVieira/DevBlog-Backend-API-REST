package com.mateusdev.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private FilterToken filterToken;

	// Aqui neste método trabalhamos com as rotas.
	// Definimos o que deve estar autenticado ou não para acessar cada rota
	// (endpoint)
	// e quem pode acessar as rotas
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		System.out.println("\nEntrou no filterChain\n");

		// Desabilitamos a segurança contra ataques csrf
        http.csrf((csrf) -> csrf.disable() ); 
        
        // Configuramos o gerenciamento de sessão, nesse caso o spring não usará sessões
        // usaremos token para atender as requisições
        http.sessionManagement(management -> 
        	management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        // Configuramos a autorização de acesso a rotas
        http.authorizeHttpRequests((authz) -> 
                authz
                .requestMatchers(HttpMethod.POST,"/login/logar").permitAll() // Permitir acesso público a essa URL
                .requestMatchers(HttpMethod.POST,"/new").hasAuthority("USER") // Requerer autenticação com a função USER
                .requestMatchers(HttpMethod.GET,"/list").hasAuthority("USER") // Requerer autenticação com a função USER
                .anyRequest().authenticated()); // Todas as outras URLs exigem autenticação
             
        // após passar pelo filtro e criar uma autenticação válida, essa classe UsernamePasswordAuthenticationFilter
        // verificará essa autenticação criada e então deixará o usuário acessae aquilo que ele pode acessar segundo suas roles.
        http.addFilterBefore(this.filterToken, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
        
        // .hasAuthority("USER") seria o mesmo que .hasRole(ROLE_USER)
    }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
