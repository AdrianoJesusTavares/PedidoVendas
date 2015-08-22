package gyn.jesus.converter;

import gyn.jesus.model.Cheque;
import gyn.jesus.repository.Cheques;
import gyn.jesus.util.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Cheque.class)
public class ChequeConverter implements Converter {
	
	//@Inject
	Cheques cheque;
	
	 public ChequeConverter() {
		 cheque = CDIServiceLocator.getBean(Cheques.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cheque retorno = null;
		
		if(value!= null){
			
			Long id = new Long(value);
			retorno = this.cheque.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Cheque cheque = ((Cheque)value);
			return cheque.getId()==null ? null : cheque.getId().toString();
		}
		return "";
	}

}
