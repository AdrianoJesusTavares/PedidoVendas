package gyn.jesus.Beans;

import gyn.jesus.model.Categoria;
import gyn.jesus.repository.Categorias;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroSubCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private Categoria subCategoria;
	private List<Categoria> categoriasRaizes;
	private Categoria categoriaPai;

	@Inject
	private Categorias categorias;
	

	CadastroSubCategoriaBean() {
		this.subCategoria = new Categoria();
		
	}

	public void salvarSubcategoria(){
		this.subCategoria.setCategoriaPai(this.categoriaPai);
		this.categorias.salvar(this.subCategoria);
		FacesUtil.addInfoMessage("Sub Categoria " + this.subCategoria.getDescricao() + " Cadastrada com sucesso");
		this.limpar();
		this.categoriasRaizes = this.categorias.listar();
	}

	public void inicializar() {

		if (FacesUtil.isNotPostBack()) {
			this.categoriasRaizes = this.categorias.listar();
		}

	}
	private void limpar(){
		this.categoriasRaizes = new ArrayList<>();
		this.categoriaPai = null ;
		this.subCategoria= new Categoria();
		
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}


	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}



	public Categoria getSubCategoria() {
		return subCategoria;
	}	
	
	
}
