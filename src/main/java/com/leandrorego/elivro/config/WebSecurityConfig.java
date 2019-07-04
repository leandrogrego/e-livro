package com.leandrorego.elivro.config;

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
/**
 * Classe responsavel por da todas as permições do usuario e chamada da pagina de login
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired //repository do user config
	private ImplementsUserDetailsService userDetailsService;
	
    /**
     * Método de autorização de paginas e requisições
     */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/*").permitAll()
		//.anyRequest().authenticated()
		.and().formLogin().loginPage("/entrar").permitAll()
		.successForwardUrl("/home")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	/**
	 * Metodo de autenticação do usuario recebendo pelo repositorio ImplementsUserDetailsService 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}
	
	/**
	 * Método que habilita as pastas estaticas para o spring security não interropelas
	 */
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**","/h2/**", "/img/**","/js/**","/vendor/**");
	 }
}
