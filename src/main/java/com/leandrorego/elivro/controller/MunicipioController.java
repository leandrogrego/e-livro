package com.leandrorego.elivro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.leandrorego.elivro.client.MunicipioClient;
import com.leandrorego.elivro.dto.Municipio;
import com.leandrorego.elivro.dto.Regiao;

@Controller
public class MunicipioController {
	
	@Autowired
	private MunicipioClient municipio;
	
	@GetMapping("/regiao")
	public String entrar() {
	List<Regiao> regioes = municipio.buscarRegioes();
		
		for(Regiao r : regioes ) {
			System.out.println(r.getNome());
		}
		
		return "index";
	}
	
	
	@GetMapping("/municipio")
	public String listaMunicipio() {
	List<Municipio> regioes = municipio.buscarMunicipioByUF(33);
		
		for(Municipio r : regioes ) {
			System.out.println(r.getNome());
		}
		
		return "index";
	}
	
}
