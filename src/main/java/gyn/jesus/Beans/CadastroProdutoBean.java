package gyn.jesus.Beans;

import gyn.jesus.model.Categoria;
import gyn.jesus.model.Produto;
import gyn.jesus.repository.Categorias;
import gyn.jesus.service.CadastroProdutoService;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Categorias categorias;
	
	@Inject
	CadastroProdutoService cadastroProdutoService;
	
	private Produto produto;
	private Categoria CategoriaPai;
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;
	private String percentLucro;

	public CadastroProdutoBean() {
		//this.produto = new Produto();
		this.limpar();
	}
	
	private void limpar(){
		this.produto = new Produto();
		this.CategoriaPai = null;
		this.subcategorias = new ArrayList<>();
	}

	public void salvar() {
		this.produto = this.cadastroProdutoService.salvar(this.produto);
		this.limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");

	}

	public void inicializar() {
		
		if(FacesUtil.isNotPostBack()){
			this.categoriasRaizes = this.categorias.listar();	
		}
		if(this.CategoriaPai!=null){
			this.carregarSubcategorias();
		}
		
	}

	public void carregarSubcategorias(){
		this.subcategorias = this.categorias.subCategoriaDe(this.CategoriaPai);
		
	}
	public Produto getProduto() {
		return this.produto;
	}
	
	

	public void setProduto(Produto produto) {
		this.produto = produto;
		
		if(this.produto != null){
			this.CategoriaPai = this.produto.getCategoria().getCategoriaPai();
			
		}
	}

	public void calcularValorUnitario() {
		System.out.println("percent lucro " + percentLucro);
			this.produto.calcularValorVenda();
		
	}
	
	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public Categoria getCategoriaPai() {
		return CategoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		CategoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
	

	public boolean getEditando(){
		return this.produto.getId()!=null;
	}

	public String getPercentLucro() {
		return percentLucro;
	}

	public void setPercentLucro(String percentLucro) {
		this.percentLucro = percentLucro;
	}

	
	
}
