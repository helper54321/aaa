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
	
	//A DemoAppConfig-ban hoztuk l�tre @Beank�nt
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
		
		
		//Ez a r�sz csak addig volt am�g hardcode-oltuk. Most hogy m�r adatb�zisb�l vessz�k ki, nincs r� sz�ks�g
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
				.antMatchers("/leaders/**").hasRole("MANAGER") //A ** jelent�se, hogy minden ami azalatt van
				.antMatchers("/systems/**").hasRole("ADMIN")
				//.anyRequest().authenticated() //minden m�velethez be kell lennie jelentkezve, hogy el�rje (de ak�rmilyen role lehet)
			.and()
			.formLogin() //a bejelentkez�s folyamat�t hat�rozzuk meg
				.loginPage("/showMyLoginPage") //milyen linken lesz a login oldalunk (persze a controllerben meg is kell mondani, hogy ezen mit adjon vissza)
				.loginProcessingUrl("/authenticateTheUser") //a bejelentkez� oldalunk formj�n erre a linkre k�ldj�k majd az adatokat (ehhez a controllerben nem kell k�d, a h�tt�rben lezajlik aminek kell)
				.permitAll() //Mindenkinek enged�lyezz�k a login formhoz val� hozz�f�r�st (hogy be tudjon jelentkezni)
			.and()
			.logout().permitAll() //b�rki kijelentkezhet
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied"); //az ehhez tartoz� oldal jelenjen meg, ha nem jogosult a tartalom megn�z�s�re
	}

	
}
