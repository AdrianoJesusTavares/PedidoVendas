package gyn.jesus.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import gyn.jesus.model.Grupo;
import gyn.jesus.model.Usuario;
import gyn.jesus.repository.Grupos;
import gyn.jesus.service.CadastroUsuarioService;
import gyn.jesus.util.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean  implements Serializable{


	private static final long serialVersionUID = 1L;

	
	private Usuario usuario;
	private List<String> listagrupo;
	private List<Grupo> grupo;
	private Grupo grupoUsuario;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	Grupos grupos;
	
	public CadastroUsuarioBean(){
		this.usuario = new Usuario();
		this.listagrupo = new ArrayList<>();
		this.grupo = new ArrayList<>();
		this.grupoUsuario = new Grupo();
	}
	
	public void salvar(){
		//this.grupo = new ArrayList<>();
		System.out.println(listagrupo.size());
		for(int i=0 ; i< listagrupo.size(); i++){
			System.out.println("listagrupo 1 " + listagrupo.get(i).toString());
			this.grupoUsuario = this.grupos.porNome(listagrupo.get(i).toString());
			
			
			this.grupo.add(this.grupoUsuario);
			this.grupoUsuario = new Grupo();
		
			//System.out.println("lista 2 " + this.lista().get(i).getNome());
		//	if(listagrupo.get(i).equalsIgnoreCase(this.lista().get(i).getNome())){
			//	System.out.println(listagrupo.get(i).toString());
				
				
			//}
		}

	    
		this.usuario.setGrupos(grupo);			
		for(Grupo grupo : this.grupo){
			System.out.println("grupo 2 " + grupo.getDescricao());
			//System.out.println(grupo.getId());
		}
    	this.cadastroUsuarioService.salvar(this.usuario);
		
		this.listagrupo = new ArrayList<>();
		this.grupo = new ArrayList<>();
    	FacesUtil.addInfoMessage("usuario salvo com sucesso!");
	}
	
	public List<Grupo> lista(){
		return this.grupos.grupos();
		
	}
	

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		
		if(this.usuario!= null){
			for(Grupo grup : usuario.getGrupos()){
				this.listagrupo.add(grup.getDescricao());
				System.out.println("Veio por parametro do pesquisar" + grup.getDescricao());
				
			}
		}
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public List<String> getListagrupo() {
		return listagrupo;
	}

	public void setListagrupo(List<String> listagrupo) {
		this.listagrupo = listagrupo;
		

	}

	
	public boolean getEditando(){
		return this.usuario.getId()!=null;
	}
	
}
