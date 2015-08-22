package gyn.jesus.converter;

import gyn.jesus.model.Categoria;
import gyn.jesus.repository.Categorias;
import gyn.jesus.util.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {
	
	//@Inject
	Categorias categorias;
	
	 public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if(value!= null){
			Long id = new Long(value);
			retorno = this.categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Categoria)value).getId().toString();
		}
		return "";
	}

}
