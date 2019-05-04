package com.leandrorego.elivro.client;


import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.leandrorego.elivro.dto.Municipio;
import com.leandrorego.elivro.dto.Regiao;

@Component
public class MunicipioClient {

	private static final String GET_MUNICIPIO_URL = "http://servicodados.ibge.gov.br/api/v1/localidades/regioes";
	private static final String GET_MUNICIPIO_URL_UF = "http://servicodados.ibge.gov.br/api/v1/localidades/estados/";
/*	public void buscarMunicipios() {
		RestTemplate restTemplate = new RestTemplate();
		Object[] forNow = restTemplate.getForObject(GET_MUNICIPIO_URL, Object[].class);
	    List<Object>searchList= Arrays.asList(forNow);
		System.out.println(searchList.size());
	}*/
	
	public List<Regiao> buscarRegioes() {
		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<List<Regiao>> responseType = new ParameterizedTypeReference<List<Regiao>>() {
		};
		
		ResponseEntity<List<Regiao>> responseEntity = restTemplate.exchange(GET_MUNICIPIO_URL,
				HttpMethod.GET, null,
				responseType);
		List<Regiao> regioes = responseEntity.getBody();
		return regioes;
	}

	public List<Municipio> buscarMunicipioByUF(int idEstado) {
		RestTemplate restTemplate = new RestTemplate();
		
		ParameterizedTypeReference<List<Municipio>> responseType = new ParameterizedTypeReference<List<Municipio>>() {
		};
		
		ResponseEntity<List<Municipio>> responseEntity = restTemplate.exchange(GET_MUNICIPIO_URL_UF + idEstado+"/municipios", HttpMethod.GET, null,
						responseType);
		List<Municipio> lista = responseEntity.getBody();
		return lista;
	}


}
