package com.leandrorego.elivros.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivros.model.Endereco;
import com.leandrorego.elivros.model.ItemPedido;
import com.leandrorego.elivros.model.Usuario;
import com.leandrorego.elivros.service.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/add")
	public ModelAndView add(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("/usuario/form");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return add(usuario);
		}
                //usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
                
		String cep = usuario.getCep();
		RestTemplate template = new RestTemplate();
		Endereco endereco = new Endereco(); // = template.getForObject("https://viacep.com.br/ws/"+cep+"/json",Endereco.class);
                
		if(endereco != null){			
			usuario.setEstado(endereco.getUf());
			usuario.setCidade(endereco.getLocalidade());
		}
		service.save(usuario);
		return findAll();
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("usuario/listar");
		mv.addObject("usuarios", service.findAll());
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		service.delete(id);
		return findAll();
	}
        
        @GetMapping("/login")
	public ModelAndView login(String email, String senha) {
            ModelAndView mv = new ModelAndView("usuario/login");
            if(email!=null && senha!=null){
                mv.addObject("usuario", service.findByEmail(email));
            } else {
                mv.addObject("usuario", new Usuario());
            }
            return mv;
	}
        
        @GetMapping("/cesta")
	public ModelAndView cesta(ItemPedido ip) {
            ModelAndView mv = new ModelAndView("IremPeidod/");
             new Usuario().getCesta().addItem(ip);
            return mv;
	}
        
        
        
        
}
