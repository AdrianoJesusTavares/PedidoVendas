package gyn.jesus.converter;

import gyn.jesus.model.Pedido;
import gyn.jesus.repository.Pedidos;
import gyn.jesus.util.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {
	
	//@Inject
	Pedidos pedidos;
	
	 public PedidoConverter() {
		 pedidos = CDIServiceLocator.getBean(Pedidos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		
		if(value!= null){
			
			Long id = new Long(value);
			retorno = this.pedidos.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Pedido pedido = ((Pedido)value);
			return pedido.getId()==null ? null : pedido.getId().toString();
		}
		return "";
	}

}
