package gyn.jesus.service;

import gyn.jesus.NegocioException;
import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.Pedido;
import gyn.jesus.model.StatusPedido;
import gyn.jesus.repository.Pedidos;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;


public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatusPedido(StatusPedido.Orcamento);
		}
		
		pedido.recalcularValorTotal();
		if(pedido.isNaoAlteravel()){
			throw new NegocioException("Pedido nao pode ser Alterado no status" + pedido.getStatusPedido()+".");
		}
		
		if (pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if (pedido.isValorTotalNegativo()) {
			throw new NegocioException("Valor total do pedido não pode ser negativo.");
		}
		
		pedido = this.pedidos.guardar(pedido);
		return pedido;
	}
	
}
