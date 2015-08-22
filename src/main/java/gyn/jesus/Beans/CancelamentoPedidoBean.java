package gyn.jesus.Beans;

import gyn.jesus.PedidoAlteradoEvent;
import gyn.jesus.anotations.PedidoEdicao;
import gyn.jesus.model.Pedido;
import gyn.jesus.service.CancelamentoPedidoService;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;


import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class CancelamentoPedidoBean implements Serializable{


	private static final long serialVersionUID = 1L;

	@Inject
	private CancelamentoPedidoService cancelamentoPedidoService;
	
	@Inject
	
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void cancelarPedido(){
		this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
		this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
		
		FacesUtil.addInfoMessage("Pedido Cancelado com sucesso");
		
	}
}
