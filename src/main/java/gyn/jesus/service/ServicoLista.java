package gyn.jesus.service;



import gyn.jesus.anotations.Transactional;
import gyn.jesus.model.Cheque;
import gyn.jesus.model.Cliente;
import gyn.jesus.repository.Cheques;
import gyn.jesus.repository.Clientes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class ServicoLista implements Serializable {
 
	private static final long serialVersionUID = 1L;

	double total;
	
	@Inject
	private Clientes clientes;

	@Inject
	private Cheques cheques;
    
    List<Cliente> lista = new ArrayList<Cliente>();
	
    public  List<Cliente> listarPessoas(){
		lista = clientes.clientes();
		
		return lista;
	}
	
    public void salvarCliente(Cliente cliente){
    	this.clientes.guardar(cliente);
    	
    
    }
	public  Cliente getPessoa(String nome){
		
		for(Cliente cliente: lista){
			if(nome.equalsIgnoreCase(cliente.getNome())){
				return cliente;
			}
		}
		
		return null;
	}
	
	public int calcularData(Date dataEntrada, Date dataVencimento){
		
		int diasCheque = (int)Math.round(( (dataVencimento.getTime() - dataEntrada.getTime()) / (1000 * 60 * 60 * 24) ));
		return diasCheque;
		
	}
	
	public double calculaJuros(double taxaJuro , double valorBruto, int dias){
		
		double juro=(((valorBruto*taxaJuro)/100)/30)*(dias+2);
		return juro;
	}
	public double calulaValorLiquido(double valorBruto, double juro){
		double valorLiquido=valorBruto-juro;
		return valorLiquido;
	}
	
	@Transactional
	public void salvarCheques(List<Cheque> cheques){
		
		for(Cheque cheque : cheques){
			this.cheques.salvar(cheque);
		}
		
	}
	public List<Cheque> listaCheques(){
		return this.cheques.listaCheques();
	}
	
	public double calulaValorLiquido(List<Cheque> lista){
		double valorLiquido=0;
		for(Cheque cheque : lista){
			valorLiquido +=cheque.getValorLiquido();
		}
		return valorLiquido;
	}

	public double calulaValorJuros(List<Cheque> lista){
		double juros=0;
		for(Cheque cheque : lista){
			juros +=cheque.getJuros();
		}
		return juros;
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
