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
		
		return usuarios.salvar(usuario);
	}

	public List<Usuario> pesquisa(UsuarioFilter filtro){
		
		return this.usuarios.filtrados(filtro);
	
	}
	@Transactional
	public void remover(Usuario usuario){
		this.usuarios.remover(usuario);
		
	}
}
