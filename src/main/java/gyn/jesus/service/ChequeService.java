package gyn.jesus.service;



import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.Cheque;
import gyn.jesus.repository.Cheques;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class ChequeService implements Serializable {
 
	private static final long serialVersionUID = 1L;

	double total;

	@Inject
	private Cheques cheques;
    
	
	public int calcularData(Date dataEntrada, Date dataVencimento){
		
		return (int)Math.round(( (dataVencimento.getTime() - dataEntrada.getTime()) / (1000 * 60 * 60 * 24) ));
		
	}
	
	public double calculaJuros(double taxaJuro , double valorBruto, int dias){
		
		return (((valorBruto*taxaJuro)/100)/30)*(dias+2);
		
	}
	public double calculaValorLiquido(double valorBruto, double juro){
		return valorBruto-juro;
	}
	
	@Transactional
	public void salvarCheques(List<Cheque> cheques){
		
		for(Cheque cheque : cheques){
			this.cheques.salvar(cheque);
		}
		
	}

	public double calulaValorBruto(List<Cheque> lista){
		double vlBruto=0;
		for(Cheque cheque : lista){
			vlBruto +=cheque.getValor();
		}
		return vlBruto;
	}

	@Transactional
	public void remover(Cheque chequeSelecionado) {
		this.cheques.remover(chequeSelecionado);
		
	}
	
}
