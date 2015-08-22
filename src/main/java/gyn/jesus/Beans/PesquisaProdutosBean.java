package gyn.jesus.Beans;

import gyn.jesus.filtros.ProdutoFilter;
import gyn.jesus.model.Produto;
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
public class PesquisaProdutosBean implements Serializable {


	private static final long serialVersionUID = 1L;
	private List<Produto> produtosFiltrados = new ArrayList<Produto>();
	private ProdutoFilter produto;
	private Produto produtoSelecionado;
	
	@Inject
	CadastroProdutoService cadastroProdutoService;
	
	public PesquisaProdutosBean(){
		this.produto = new ProdutoFilter();
		this.produtoSelecionado = new Produto();
		
	}
	
	
	public void pesquisar(){
		this.produtosFiltrados = this.cadastroProdutoService.pesquisa(produto);
	}
	
	public List<Produto> getProdutosFiltrados(){
		return this.produtosFiltrados;
	}
	
	public void excluir(){
		
		this.cadastroProdutoService.remover(produtoSelecionado);
		this.produtosFiltrados.remove(this.produtoSelecionado);
		FacesUtil.addInfoMessage("produto"+this.produtoSelecionado.getSku() + "excluido com sucesso");
	}
	
	public ProdutoFilter getProduto() {
		return produto;
	}


	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}


	public void setProdutoSelecionado(Produto produtoSelecionado) {

		this.produtoSelecionado = produtoSelecionado;
	}
	
}
