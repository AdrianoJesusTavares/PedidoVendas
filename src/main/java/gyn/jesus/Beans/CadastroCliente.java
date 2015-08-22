package gyn.jesus.Beans;

import gyn.jesus.model.Cliente;
import gyn.jesus.model.Endereco;
import gyn.jesus.model.Estados;
import gyn.jesus.model.TipoPessoa;
import gyn.jesus.service.CadastroClienteService;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class CadastroCliente implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private  boolean status;
	private Cliente cliente;
	private Endereco endereco;
	private List<Endereco> listaEndereco;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	public CadastroCliente (){
		this.cliente = new Cliente();
		this.listaEndereco = new ArrayList<>();
		this.endereco = new Endereco();
	}

	public void salvar(){
		
		
	
		this.endereco.setCliente(this.cliente);
		this.listaEndereco.add(this.endereco);
		this.cliente.setEnderecos(this.listaEndereco);
		
		this.cadastroClienteService.salvar(this.cliente);
		this.limpar();
		FacesUtil.addInfoMessage("Cliente salvo com sucesso!");
	}
	
	private void limpar(){
		this.cliente = new Cliente();
		this.endereco = new Endereco();
		this.listaEndereco = new ArrayList<Endereco>();
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		if(this.cliente!= null){
			this.Rendered();
			this.listaEndereco = cliente.getEnderecos();
			for(Endereco end : this.listaEndereco){
				this.endereco = end;
			}
		}
		
	}

	public TipoPessoa [] getTipoPessoa() {
		return TipoPessoa.values();
	}
	
	public Estados[] getEstados(){
		return Estados.values();
	}
	
	public void Rendered(){
		
		if(TipoPessoa.FISICA.equals(this.cliente.getTipo())){
			this.status = true;
		}else{
			this.status = false;
		}
	
	}
	
	public boolean getStatus(){
		return this.status;
	}
	
	


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

/*	private String formataDocReceitaFederal(Cliente cliente){
		String docFormatado = null;
		
		if(cliente.getDocumentoReceitaFederal().length()==14){
			docFormatado = this.cliente.getDocumentoReceitaFederal().replaceAll("\\.","").replace("-",""); 
		}else{
			docFormatado = this.cliente.getDocumentoReceitaFederal().replaceAll("\\.","").replaceAll("\\/","").replace("-","");
		}
		return docFormatado;
	}*/
	
	public boolean getEditando(){
		return this.cliente.getId()!=null;
	}
	
}
