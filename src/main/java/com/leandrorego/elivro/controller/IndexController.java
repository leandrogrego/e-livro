package com.leandrorego.elivro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@GetMapping("/teste")
	public String entrar() {
		return "categoria/form2";
	}
	
	@GetMapping("/base")
	public String base() {
		return "pagina_base";
	}
	
	@GetMapping("/imprimir/{valor}")
	public String imprimir(@PathVariable("valor") String valor ) {
		
		return "index2";
	}
	
	@GetMapping("/imprimir")
	public String imprimir(@RequestParam(name="marca") String marca, @RequestParam(name="valor") String valor) {
		
		return "index2";
	}
	
	
	
	
}
