package gyn.jesus.filtros;

import java.io.Serializable;

public class ProdutoFilter implements Serializable {
	String nome;
	String sku;
	
	private static final long serialVersionUID = 1L;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}



}
