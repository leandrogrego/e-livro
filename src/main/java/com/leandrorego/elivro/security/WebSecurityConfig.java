package com.leandrorego.elivro.security;

import com.leandrorego.elivro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

        @Autowired
        private UsuarioService usuarioService;
        
        @Autowired
	private UserDetailsService userDetailsService;
	
        @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()			
			.antMatchers("/autor/**").hasAnyRole("ADMIN")
			.antMatchers("/livro/**").hasAnyRole("ADMIN")
			.antMatchers("/categoria/**").hasAnyRole("ADMIN")
			.antMatchers("/editora/**").hasAnyRole("ADMIN")
			.antMatchers("/usuario/**").hasAnyRole("ADMIN")
			.antMatchers("/https://**").hasAnyRole("ADMIN")
			.antMatchers("/frete/**").hasAnyRole("USER")
			.antMatchers("/pedido/**").hasAnyRole("USER")
			.antMatchers("/itemPedido/**").hasAnyRole("USER")
			.antMatchers("http:/**").hasAnyRole("USER")
			.antMatchers("https:/**").hasAnyRole("USER")
			.anyRequest().authenticated()
                        .and().formLogin().permitAll()
                            .loginPage("/login").permitAll()
                            .successForwardUrl("/").and().logout().permitAll()
                        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Autowired
	public void configureGloblal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder());  
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/css/**", "/fonts/**", 
                        "/img/**", "/js/**", "/scss/**", 
                        "/vnedor/**", "/vendbox/**", "/loja"    );
		web.ignoring().antMatchers("/h2/**");
	}


}
