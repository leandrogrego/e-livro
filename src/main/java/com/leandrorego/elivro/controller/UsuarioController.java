package com.leandrorego.elivro.controller;
import org.springframework.http.MediaType;

import java.io.IOException;

import javax.validation.Valid;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.models.Endereco;
import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.service.RoleService;
import com.leandrorego.elivro.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UsuarioService userCrud;
	
	@Autowired
	private RoleService roleCrud;
	
    /**
     * Método responsavel por criar uma instancia do usuário onde
     * @param usuario passa para método
     * @param endereco 
     * @param carrinho
     * @param pedido
     * é também uma lista de pedidos e intens para o pedido do cliente
     * @return retornando tudo para a view
     */
	
	@GetMapping("/cadastro")
	public ModelAndView usuario(Usuario usuario, Endereco endereco) {
		
		ModelAndView mv = new ModelAndView("cadastro/usuario");
		
		usuario.setEndereco(endereco);
		mv.addObject("usuario", usuario);
		mv.addObject("roles",roleCrud.findAll());
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper bemvindo = new MimeMessageHelper(mail);
			bemvindo.setTo(usuario.getUsername());
			bemvindo.setSubject("Bem vinco ao E-livros");
			bemvindo.setText("<p>Seja bem vindo ao E-livros</p>", true);
			mailSender.send(mail);
			System.out.println("Email enviado!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("O email não foi enviado");
		}
		return mv;
	}
	
	/**
	 * Método de salvar usuario onde recebe um tipo 
	 * @param usuario - entidade a ser salvar
	 * @param file - tipo de foto do perfil do usuario
	 * @param result - Resultado caso funcione do jeito correto ou errado a validação
	 * @return - retona os objetos para a view dependendo se tudo certo ou não
	 * @throws IOException
	 */
	@PostMapping("/salvar")
	public String create(@Valid Usuario usuario,@RequestParam("uploadfile") MultipartFile file, BindingResult result) throws IOException {
		
		if (result.hasErrors()) {
			return "redirect:/usuario/cadastro";
		}
		
		if (!file.isEmpty()) {
			usuario.setFoto(file.getBytes());
		} 

		usuario.setPassword (new BCryptPasswordEncoder().encode(usuario.getPassword()));
		userCrud.create(usuario);
		return "redirect:/home";
	}
	
	/**
	 * Método de listagem de usuários
	 * @return lista de usuários
	 */
	@GetMapping("/lista")
	public ModelAndView read(){
		
		ModelAndView mv = new ModelAndView("lista/usuarios");
		mv.addObject("usuarios",userCrud.findAll());
		return mv;
	}
	
	/**
	 * Método de deleção de usuários
	 * @param id - Recebe o id do usuário
	 * @return rediceriona para tela de usuários novamente
	 */
	@GetMapping ("/delete/{id}")
	public String delete(@PathVariable("id") long id){
		
		userCrud.delete(userCrud.findById(id));
		return "redirect:/lbook/usuarios";
	}
	
	/**
	 * Metodo de atualização
	 * @param id
	 * @return
	 */
	@GetMapping("/update/{id}")
	public ModelAndView update (@PathVariable("id") long id){
		
		Usuario  usuario = userCrud.findById(id);
		ModelAndView mv = new ModelAndView("cadastro/usuario");
		mv.addObject(usuario);
		return mv;
	}
	
	
	@GetMapping("/perfil")
	public ModelAndView perfil(){
		Usuario usuario = userCrud.findByusername(SecurityContextHolder.getContext().getAuthentication().getName());
		ModelAndView mv = new ModelAndView("cadastro/usuario");
		mv.addObject(usuario);
		return mv;
	}
		
	/**
	 * Método de rederização de imagem
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id) throws IOException {

		Usuario usuario = userCrud.findById(id);
		byte[] imagem = usuario.getFoto();
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}
}