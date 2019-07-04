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
import com.leandrorego.elivro.models.Pedido;
import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.service.LivroService;
import com.leandrorego.elivro.service.UsuarioService;

@Controller
@RequestMapping("/")
public class VendasController {
	
	@Autowired
	LivroService crudLivro;
	
	@Autowired
	UsuarioService crudUsuario;
	/**
	 * Método de rederização das pagina de venda
	 * @return
	 */
	@GetMapping("/livros/venda")
	public ModelAndView venda(){
		ModelAndView mv = new ModelAndView("venda/venda");
		mv.addObject("livros",crudLivro.findAll());
		return mv;
	}
	
	/**
	 * Metodo que adiciona um produto ao carrinho do cliente onde
	 * @param id responsavel por identificar o livro a ser colocado no item carrinho
	 * criando se for nulo o carrinho, com os pedidos e com itensPedidos e adicionando 
	 * o livro no itempedido adicionando na lista de intens pedidos dos pedidos do carrinho
	 * @return
	 */
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
	    
	    if(usuario.getCarrinho() == null){
	    	Carrinho carrinho = new Carrinho();
	    	Pedido pedido = new Pedido();	
	    	List<ItemPedido> itemPedidos = new ArrayList<>();
	    	pedido.setItemPedidos(itemPedidos);
	    	carrinho.setPedido(pedido);
	    	usuario.setCarrinho(carrinho);
	    }
	    
	    
	    usuario.getCarrinho().getPedido().getItemPedidos().add(itemPedido); //adiciona o itemPedido la lista de item pedido
	    //do pedido do carrinho
	    usuario.getCarrinho().setQuantItens(usuario.getCarrinho().getQuantItens()+1);
	    
	    crudUsuario.create(usuario);
	    
	    session.setAttribute("usuarioLogado",usuario);
		return "redirect:/livros/venda";
	}
	
	/**
	 * Metodo responsavel por mostra todos os itens dos pedidos do carrinho do usuário logado
	 * @param session
	 * @return
	 */
	@GetMapping("/carrinho")
	public ModelAndView carrinho(HttpSession session){
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Usuario usuario  = crudUsuario.findById(usuarioLogado.getId());
		ModelAndView mv = new ModelAndView("venda/cart");
		mv.addObject("itemPedidos",usuario.getCarrinho().getPedido().getItemPedidos());
		return mv;
	}
	
	/**
	 * Método responsavel por deletar o item pedido do carrinho do usuario onde
	 * @param id passado é o do item pedido
	 * @param session pega o usuário logado
	 * Verifica se o id do item está no carrinho do cliente
	 * se estiver remove salvar usuário e diminui a quantidade de itens
	 * @return
	 */
	@GetMapping("/delete/item/carrinho/{id}")
	public String deleteItemCarrinho(@PathVariable("id") long id, HttpSession session){
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Usuario usuario  = crudUsuario.findById(usuarioLogado.getId());
		
		
		for(int i = 0; i < usuario.getCarrinho().getPedido().getItemPedidos().size(); i++){
			
			if(id == usuario.getCarrinho().getPedido().getItemPedidos().get(i).getId() ){
				
				usuario.getCarrinho().getPedido().getItemPedidos().remove(i);
				usuario.getCarrinho().setQuantItens(usuario.getCarrinho().getQuantItens() - 1);
				crudUsuario.create(usuario);
				session.setAttribute("usuarioLogado",usuario);
			}
		}
		
		return "redirect:/carrinho";
	}
	
	@GetMapping("/calc/frete")
	public String calcFrete(){
		
		CorreioDTO correio = new CorreioDTO();
		correio.setIndentificador(1);
		correio.setCep_origem("80230-110");
		correio.setCep_destino("83606-594");
		correio.setFormato(1);
		correio.setPeso("3,75");
		correio.setComprimento(25);
		correio.setAltura(40);
		correio.setLargura(11);
		correio.setMao_propria("S");
		correio.setAviso_recebimento("S");
		correio.setValor_declarado("101,00");
		String[] arrayStrings = new String[1];   
		arrayStrings[0] = "04162"; 
		correio.setServicos(arrayStrings);
		
		ClienteRest.requestCorreios(correio);
		return "/teste";
	}
	
	@GetMapping("/fecha/carrinho")
	public String fecharCompra(HttpSession session){
		
		Usuario usuariologado = (Usuario) session.getAttribute("usuarioLogado");
		Usuario usuario  = crudUsuario.findById(usuariologado.getId());
		
		if(usuario.getPedidos() == null){
			List<Pedido> pedidos = new ArrayList<>();
			usuario.setPedidos(pedidos);
		}
		
		usuario.getPedidos().add(usuario.getCarrinho().getPedido());
		
		Pedido pedido =  usuario.getCarrinho().getPedido();
		
		for(int i = 0; i < pedido.getItemPedidos().size();i++){
		    pedido.getItemPedidos().remove(i);
		    
		}
		
		usuario.getCarrinho().setQuantItens(0);
		crudUsuario.create(usuario);
		return "redirect:/livros/venda";
	}
}
