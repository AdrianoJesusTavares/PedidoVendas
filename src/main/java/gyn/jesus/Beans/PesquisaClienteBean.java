package gyn.jesus.Beans;

import gyn.jesus.filtros.ClienteFilter;
import gyn.jesus.model.Cliente;
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
public class PesquisaClienteBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Cliente> clientesFiltrados = new ArrayList<Cliente>();
	private ClienteFilter cliente;
	private Cliente clienteSelecionado;
	private boolean opcao;
	
	@Inject
	CadastroClienteService cadastroClienteService;
	
	public PesquisaClienteBean(){
		this.cliente = new ClienteFilter();
		this.clienteSelecionado = new Cliente();
		
	}
	
	
	public void pesquisar(){
		System.out.println(this.cliente.getDocumentoReceitaFederal());
		this.clientesFiltrados = this.cadastroClienteService.pesquisa(cliente);
	}
	
	public List<Cliente> getClientesFiltrados(){
		return this.clientesFiltrados;
	}
	
	public void excluir(){
		
		this.cadastroClienteService.remover(this.clienteSelecionado);
		this.clientesFiltrados.remove(this.clienteSelecionado);
		FacesUtil.addInfoMessage("cliente " + this.clienteSelecionado.getNome() + " foi excluido com sucesso");
	}
	
	public ClienteFilter getCliente() {
		return cliente;
	}


	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}


	public void setClienteSelecionado(Cliente produtoSelecionado) {

		this.clienteSelecionado = produtoSelecionado;
	}


	public boolean isOpcao() {
		return opcao;
	}


	public void setOpcao(boolean opcao) {
		this.opcao = opcao;
	}
	
	
}
