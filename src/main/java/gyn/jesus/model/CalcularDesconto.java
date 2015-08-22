package gyn.jesus.model;

public enum CalcularDesconto {
	
	DESCONTO("DESCONTO"), CALCULOSIMPLES("CALCULO SIMPLES");
	
	private String descricao;
	
	CalcularDesconto(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
