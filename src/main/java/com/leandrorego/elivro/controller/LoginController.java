package com.leandrorego.elivro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.service.RoleService;
import com.leandrorego.elivro.service.UsuarioService;

@Controller
public class LoginController {
	@Autowired
	private UsuarioService crud;
	private RoleService roles;

	@GetMapping("/entrar")
	public String entrar() {
		
		Usuario usuario = crud.findByusername("contato@leandrorego.com");
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setNome("Leandro Rego");
			usuario.setUsername("contato@leandrorego.com");
			usuario.setPassword(new BCryptPasswordEncoder().encode("123"));
			crud.create(usuario);
		}
		//usuario.setRoles(roles.findAll());
	
		return "login/login";
	}

	/**
	 * @param session
	 *            Método responsavel por passa para a session o usuário que está
	 *            logado.
	 */
	@PostMapping("/home")
	public String session(HttpSession session) {
		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		session.setAttribute("usuarioLogado",
				crud.findByusername(usuario.getUsername()));
		return "redirect:/home";
	}
}
