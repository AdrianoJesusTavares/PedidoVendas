package gyn.jesus.model;

public enum StatusPedido {
	
	Orcamento("Orcamento"),EMITIDO("EMITIDO"),CANCELADO("CANCELADO");
	
	private String descricao;
	
	StatusPedido(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
