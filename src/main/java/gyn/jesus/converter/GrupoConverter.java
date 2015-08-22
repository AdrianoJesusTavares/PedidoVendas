package gyn.jesus.converter;

import gyn.jesus.model.Grupo;
import gyn.jesus.repository.Grupos;
import gyn.jesus.util.CDIServiceLocator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Grupo.class)
public class GrupoConverter implements Converter {

	//@Inject
	private Grupos grupos;
	
	public GrupoConverter() {
		this.grupos = (Grupos) CDIServiceLocator.getBean(Grupos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;

		if (value != null) {
			retorno = this.grupos.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Grupo) value).getId().toString();
		}
		return "";
	}

}