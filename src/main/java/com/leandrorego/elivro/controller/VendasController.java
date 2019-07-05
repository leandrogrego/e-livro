package com.leandrorego.elivro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.api.restclient.ClienteRest;
import com.leandrorego.elivro.dto.CorreioDTO;
import com.leandrorego.elivro.models.Carrinho;
import com.leandrorego.elivro.models.ItemPedido;
import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.service.ItemPedidoService;
import com.leandrorego.elivro.service.LivroService;
import com.leandrorego.elivro.service.UsuarioService;

@Controller
@RequestMapping("/")
public class VendasController {
	
	@Autowired
	LivroService crudLivro;
	
	@Autowired
	UsuarioService crudUsuario;

	@GetMapping("/livros/venda")
	public ModelAndView venda(){
		ModelAndView mv = new ModelAndView("venda/pedido");
		mv.addObject("livros",crudLivro.findAll());
		return mv;
	}
	

	@GetMapping("/carrinho/add/{id}")
	public String addCarinho(@PathVariable("id") long id,HttpSession session){
		Usuario usuarioLogado;
		if(session.getAttribute("usuarioLogado") == null) {
			return "redirect:/entrar";
		}
		usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");  //pega o usuario da seção
	    Usuario usuario = crudUsuario.findById(usuarioLogado.getId()); //pega o usuario da session no banco
	    
	    ItemPedido itemPedido = new ItemPedido();
	    itemPedido.setLivro(crudLivro.findByid(id)); //adiciona o pedido no item pedido
	    itemPedido.setQuantidade(1); 
	    
	    if(usuario.getCarrinho() == null){
	    	Carrinho carrinho = new Carrinho();
	    	usuario.setCarrinho(carrinho);
	    }
	        	
	    //adiciona o itemPedido a lista de itens no carrinho
	    usuario.getCarrinho().add(itemPedido);

	    crudUsuario.create(usuario);
	    
	    session.setAttribute("usuarioLogado",usuario);
		return "redirect:/carrinho";
	}
	
	@GetMapping("/carrinho")
	public ModelAndView carrinho(HttpSession session){
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");  //pega o usuario da seção
		if(usuarioLogado == null) {
			usuarioLogado  = new Usuario();
			usuarioLogado .setCarrinho(new Carrinho());
		} else {
			usuarioLogado  = crudUsuario.findById(usuarioLogado.getId()); //pega o usuario da session no banco
		}
	    ModelAndView mv = new ModelAndView("venda/carrinho");
		mv.addObject("usuario",usuarioLogado);
		mv.addObject("itens",usuarioLogado.getCarrinho().getItens());
		return mv;
	}
	
	@GetMapping("/carrinho/del/{id}")
	public String deleteItemCarrinho(@PathVariable("id") long id, HttpSession session){
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Usuario usuario  = crudUsuario.findById(usuarioLogado.getId());
		usuario.getCarrinho().remove(id);
		crudUsuario.create(usuario);
		session.setAttribute("usuarioLogado",usuario);
		return "redirect:/carrinho";
	}
	
	@GetMapping("/calc/frete")
	public String calcFrete(String origem, String destino, String peso, String valor){
		
		CorreioDTO correio = new CorreioDTO();
		correio.setIndentificador(1);
		correio.setCep_origem(origem);
		correio.setCep_destino(destino);
		correio.setFormato(1);
		correio.setPeso(peso);
		correio.setComprimento(25);
		correio.setAltura(25);
		correio.setLargura(25);
		correio.setMao_propria("N");
		correio.setAviso_recebimento("N");
		correio.setValor_declarado(valor);
		String[] arrayStrings = new String[1];   
		arrayStrings[0] = "04162"; 
		correio.setServicos(arrayStrings);
		
		ClienteRest.requestCorreios(correio);
		return "/teste";
	}
	
	@GetMapping("/carrinho/checkout")
	public String fecharCompra(HttpSession session){
		
		Usuario usuariologado = (Usuario) session.getAttribute("usuarioLogado");
		Usuario usuario  = crudUsuario.findById(usuariologado.getId());
		
		if(usuario.getPedidos() == null){
			List<Carrinho> pedidos = new ArrayList<>();
			usuario.setPedidos(pedidos);
		}
		
		usuario.getPedidos().add(usuario.getCarrinho());
		
		Carrinho pedido =  usuario.getCarrinho();
		
		for(int i = 0; i < pedido.getItens().size();i++){
		    pedido.getItens().remove(i);
		    
		}
		
		usuario.getCarrinho().setQuantItens(0);
		crudUsuario.create(usuario);
		return "redirect:/livros/venda";
	}
}
