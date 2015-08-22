package gyn.jesus.Beans;

import gyn.jesus.model.Categoria;
import gyn.jesus.repository.Categorias;


import gyn.jesus.util.FacesUtil;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroCategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Categoria categoria;

	@Inject
	private Categorias categorias;

	CadastroCategoriaBean() {
		this.categoria = new Categoria();

	}

	public void salvar() {

		this.categorias.salvar(this.categoria);
		FacesUtil.addInfoMessage("Categoria " + this.categoria.getDescricao() + " Cadastrada com sucesso");
		this.limpar();

	}

	private void limpar() {

		this.categoria = new Categoria();

	}

	public Categoria getCategoria() {
		return categoria;
	}

}
