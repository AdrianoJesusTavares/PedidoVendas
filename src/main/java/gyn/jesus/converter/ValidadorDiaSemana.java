package gyn.jesus.converter;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validaDia")
public class ValidadorDiaSemana implements Validator {

	public void validate(FacesContext contex, UIComponent compnent, Object valor)
			throws ValidatorException {
		Calendar data = Calendar.getInstance();
		data.setTime((Date)valor);
		
		int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
		
		if(diaDaSemana == Calendar.SATURDAY || diaDaSemana == Calendar.SUNDAY){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data Invalida","Informe um dia Util");
			throw new ValidatorException(msg);
		}
		
		
	}

}
