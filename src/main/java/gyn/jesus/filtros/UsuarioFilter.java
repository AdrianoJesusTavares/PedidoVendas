package gyn.jesus.filtros;

import gyn.jesus.model.Grupo;



import java.io.Serializable;
import java.util.List;

public class UsuarioFilter implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;

	private List<Grupo> grupo;

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Grupo> getGrupo() {
		return grupo;
	}
	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}
	



}
