package gyn.jesus.service;

import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.ItemPedido;
import gyn.jesus.model.Pedido;
import gyn.jesus.repository.Pedidos;

import java.io.Serializable;

import javax.inject.Inject;

public class EstoqueService  implements Serializable{


	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		
		pedido = this.pedidos.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().baixarExtoque(item.getQuantidade());
		}
		
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()){
			item.getProduto().adicionarExtoque(item.getQuantidade());
		}
		
		
	}

}
