package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//A DemoAppConfig-ban hoztuk létre @Beanként
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
		//Ez a rész csak addig volt amíg hardcode-oltuk. Most hogy már adatbázisból vesszük ki, nincs rá szükség
//		UserBuilder users = User.withDefaultPasswordEncoder();
//		
//		auth.inMemoryAuthentication()
//			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//			.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
//			.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
			
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/leaders/**").hasRole("MANAGER") //A ** jelentése, hogy minden ami azalatt van
				.antMatchers("/systems/**").hasRole("ADMIN")
				//.anyRequest().authenticated() //minden mûvelethez be kell lennie jelentkezve, hogy elérje (de akármilyen role lehet)
			.and()
			.formLogin() //a bejelentkezés folyamatát határozzuk meg
				.loginPage("/showMyLoginPage") //milyen linken lesz a login oldalunk (persze a controllerben meg is kell mondani, hogy ezen mit adjon vissza)
				.loginProcessingUrl("/authenticateTheUser") //a bejelentkezõ oldalunk formján erre a linkre küldjük majd az adatokat (ehhez a controllerben nem kell kód, a háttérben lezajlik aminek kell)
				.permitAll() //Mindenkinek engedélyezzük a login formhoz való hozzáférést (hogy be tudjon jelentkezni)
			.and()
			.logout().permitAll() //bárki kijelentkezhet
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied"); //az ehhez tartozó oldal jelenjen meg, ha nem jogosult a tartalom megnézésére
	}

	
}
