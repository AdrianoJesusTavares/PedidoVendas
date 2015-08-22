package gyn.jesus.service;

import java.io.Serializable;

import javax.inject.Inject;

import gyn.jesus.NegocioException;
import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.Pedido;
import gyn.jesus.model.StatusPedido;
import gyn.jesus.repository.Pedidos;

public class CancelamentoPedidoService implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject
	Pedidos pedidos;
	
	@Inject
	EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		if(pedido.isNaoCancelavel()){
			
			throw new NegocioException("Pedido nao pode ser cancelado no Status" + pedido.getStatusPedido());
		}
		if(pedido.isEmitido()){
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatusPedido(StatusPedido.CANCELADO);
		pedido = this.pedidos.salvar(pedido);
		
		return pedido;
	}

	
}
