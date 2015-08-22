package gyn.jesus.converter;

import gyn.jesus.model.Produto;
import gyn.jesus.repository.Produtos;
import gyn.jesus.util.CDIServiceLocator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {
	
	//@Inject
	Produtos produto;
	
	 public ProdutoConverter() {
		 produto = CDIServiceLocator.getBean(Produtos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;
		
		if(value!= null){
			
			Long id = new Long(value);
			retorno = this.produto.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			Produto produto = ((Produto)value);
			return produto.getId()==null ? null : produto.getId().toString();
		}
		return "";
	}

}
