package gyn.jesus.Beans;

import gyn.jesus.filtros.UsuarioFilter;
import gyn.jesus.model.Grupo;
import gyn.jesus.model.Usuario;
import gyn.jesus.repository.Grupos;
import gyn.jesus.service.CadastroUsuarioService;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Usuario> usuariosFiltrados = new ArrayList<Usuario>();
	private UsuarioFilter usuario;
	private Usuario usuarioSelecionado;
	private List<String> listagrupo;
	private List<Grupo> grupo;

	@Inject
	CadastroUsuarioService cadastroUsuarioService;
	
	@Inject
	Grupos grupos;

	public PesquisaUsuarioBean() {
		this.usuario = new UsuarioFilter();
		this.usuarioSelecionado = new Usuario();
		this.listagrupo = new ArrayList<>();
		this.grupo = new ArrayList<>();

	}

	public void pesquisar() {
		
		for(int i=0 ; i< listagrupo.size(); i++){
			if(listagrupo.get(i).equalsIgnoreCase(this.lista().get(i).getNome())){
				this.grupo.add(this.lista().get(i));
				
			}
		}   
		this.usuario.setGrupo(grupo);
		this.usuariosFiltrados = this.cadastroUsuarioService.pesquisa(this.usuario);
		
		
		/*int cont =0;
		for (int i = 0; i < this.usuariosFiltrados.size(); i++) {
			this.grupo = this.usuariosFiltrados.get(i).getGrupos();

			for (int j = 0; j < this.grupo.size(); j++) {
				grupos = grupos.concat(grupo.get(j).getDescricao() + "  ");
			//	cont ++;

			}
			this.descricao.setDescricao(grupos);
		//	this.usuariosFiltrados.set(cont, this.usuariosFiltrados.get(cont).setGrupos(this.descricao));
			this.gruposCompostos.add(grupos);
			this.grupos = "";
		}

		for (String teste : this.gruposCompostos) {
			System.out.println(teste);
		}*/

	}
	
	public List<Grupo> lista(){
		return this.grupos.grupos();
	}

	public List<Usuario> getUsuariosFiltrados() {
		return this.usuariosFiltrados;
	}

	public void excluir() {
		for(Grupo grupo : this.usuarioSelecionado.getGrupos()){
			System.out.println(grupo.getId());
		}
		
		this.cadastroUsuarioService.remover(this.usuarioSelecionado);
		this.usuariosFiltrados.remove(this.usuarioSelecionado);
		FacesUtil.addInfoMessage("Usuario" + this.usuarioSelecionado.getNome()
				+ "excluido com sucesso");
	}

	public UsuarioFilter getUsuario() {
		return this.usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return this.usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {

		this.usuarioSelecionado = usuarioSelecionado;
	}

	/*public List<Grupo> getGrupo() {
		return grupo;
	}

		public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public List<String> getGruposCompostos() {
		return gruposCompostos;
	}*/

	public List<String> getListagrupo() {
		return listagrupo;
	}

	public void setListagrupo(List<String> listagrupo) {
		this.listagrupo = listagrupo;
		

	}
}
