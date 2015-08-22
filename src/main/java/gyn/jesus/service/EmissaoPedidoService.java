package gyn.jesus.service;

import java.io.Serializable;

import javax.inject.Inject;

import gyn.jesus.NegocioException;
import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.Pedido;
import gyn.jesus.model.StatusPedido;
import gyn.jesus.repository.Pedidos;

public class EmissaoPedidoService  implements Serializable{


	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	

	@Transactional
	public Pedido emitir(Pedido pedido) {
		
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if(pedido.isNaoEmissivel()){
			throw new NegocioException("Pedido nao pode ser emitido com status" + pedido.getStatusPedido()+".");
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatusPedido(StatusPedido.EMITIDO);
		
		pedido = this.pedidos.salvar(pedido);
		
		return pedido;
		
	}

}
