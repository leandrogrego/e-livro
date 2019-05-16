package com.leandrorego.elivros.security;

import com.leandrorego.elivros.service.UsuarioService;
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
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Override
	protected void configure(HttpSecurity http) throws Exception{
            System.out.println(new BCryptPasswordEncoder().encode("123"));
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/livro/").hasRole("ADMIN")
		//.antMatchers(HttpMethod.POST, "/autor").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST, "/editora").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST, "/categoria").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/fonts/**", 
                        "/img/**", "/js/**", "/scss/**", 
                        "/vnedor/**", "/vendbox/**");
	}
}
