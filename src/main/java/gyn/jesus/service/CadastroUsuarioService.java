package gyn.jesus.service;


import gyn.jesus.anotations.Transactional;
import gyn.jesus.filtros.UsuarioFilter;
import gyn.jesus.model.Usuario;
import gyn.jesus.repository.Usuarios;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;


public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Usuarios usuarios;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		
//	Produto produtoExistente = produtos.porSku(produto.getSku());
//		if (produtoExistente != null && !produtoExistente.equals(produto)) {
	//		throw new NegocioException("Já existe um produto com o SKU informado.");
	//	}
		
		return usuarios.guardar(usuario);
	}

	public List<Usuario> pesquisa(UsuarioFilter filtro){
		
		return this.usuarios.filtrados(filtro);
	
	}
	@Transactional
	public void remover(Usuario usuario){
		this.usuarios.remover(usuario);
		
	}
}
