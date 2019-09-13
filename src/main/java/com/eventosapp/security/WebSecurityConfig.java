package com.eventosapp.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception{
		http
		.csrf()
		.disable()
		.authorizeRequests()
		
		.antMatchers("/deletarConvidado").permitAll()
		.antMatchers("/deletarEvento").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.POST, "/cadastrarEvento").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.POST, "/{codigo}").hasRole("ADMIN")		
		
		.antMatchers(HttpMethod.POST, "/editarEvento/{codigo}").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/editarEvento/{codigo}").hasRole("ADMIN")
		
		.antMatchers(HttpMethod.GET, "/login").permitAll()
		
		.antMatchers(HttpMethod.GET, "/cadastrarUsuario").permitAll()
		
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.usernameParameter("login")
		.passwordParameter("senha")
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
	}	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		}
	
	@Override 
	public void configure(WebSecurity web) throws Exception{
		web
		.ignoring()
		.antMatchers("/materialize/**","/style/**");
	}

}
