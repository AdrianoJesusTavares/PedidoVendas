package gyn.jesus.service;

import gyn.jesus.NegocioException;
import gyn.jesus.anotations.Transactional;
import gyn.jesus.filtros.ProdutoFilter;
import gyn.jesus.model.Produto;
import gyn.jesus.repository.Produtos;





import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;


public class CadastroProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Produtos produtos;

	@Transactional
	public Produto salvar(Produto produto) {
		
	Produto produtoExistente = produtos.porSku(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado.");
		}
		
		return produtos.guardar(produto);
	}

	public List<Produto> pesquisa(ProdutoFilter filtro){
		
		return this.produtos.filtrados(filtro);
	
	}
	@Transactional
	public void remover(Produto produto){
		this.produtos.remover(produto);
		
	}
}
