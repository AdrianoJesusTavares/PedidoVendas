package gyn.jesus.converter;


import gyn.jesus.model.Cliente;
import gyn.jesus.repository.Clientes;
import gyn.jesus.util.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;




@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {
	
	private Clientes clientes;
	
	public ClienteConverter() {
		this.clientes = (Clientes) CDIServiceLocator.getBean(Clientes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		
		if(value!=null){
			retorno = clientes.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
	
	if(value != null){
		Cliente cliente = ((Cliente)value);
		return cliente.getId()==null ? null : cliente.getId().toString();
	}
	return "";

}
}
