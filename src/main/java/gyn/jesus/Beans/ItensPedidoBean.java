package gyn.jesus.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named("itensBean")
@ViewScoped
public class ItensPedidoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private List<Integer> itensFiltrados;
	
	public ItensPedidoBean(){
		itensFiltrados = new ArrayList<>();
		
			this.itensFiltrados.add(1);
	
	}
	public List<Integer> getItensFiltrados(){
		return this.itensFiltrados;
	}
	
}
