package gyn.jesus.service;


import gyn.jesus.anotations.Transactional;
import gyn.jesus.filtros.ClienteFilter;
import gyn.jesus.model.Cliente;
import gyn.jesus.repository.Clientes;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;


public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Clientes clientes;

	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		return this.clientes.salvar(cliente);
	
	}

	public List<Cliente> pesquisa(ClienteFilter filtro){
		
		return this.clientes.filtrados(filtro);
	
	}
	@Transactional
	public void remover(Cliente cliente){
		this.clientes.remover(cliente);
		
	}
}
