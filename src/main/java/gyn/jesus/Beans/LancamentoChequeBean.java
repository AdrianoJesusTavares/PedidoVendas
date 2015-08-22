package gyn.jesus.Beans;



import gyn.jesus.model.CalcularDesconto;
import gyn.jesus.model.Cliente;
import gyn.jesus.model.Cheque;
import gyn.jesus.repository.Clientes;
import gyn.jesus.service.ChequeService;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;




import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class LancamentoChequeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//private Pessoa pessoa;
	private double valorBruto;
	private double valorJuros;
	private double valorLiquido;
	private List<Cheque> listacheques = new ArrayList<Cheque>();
	private Cheque cheque;
	private boolean descontoCheque = false;
	private double taxaJuros;
	
	
	double total;
	private List<Cliente> cliente;
	
	@Inject
	private ChequeService servico;
	
	@Inject
	private Clientes clientes;
	
	public LancamentoChequeBean(){
		this.cliente = new ArrayList<>();
		this.cheque = new Cheque();
		
	
	}
	
	public List<Cliente> getCliente(){
		this.cliente = this.clientes.clientes();
		
		return this.cliente; 
	}

	public void excluirCheque(){

		this.valorBruto = this.valorBruto - this.cheque.getValor();
		this.valorJuros = this.valorJuros - this.cheque.getJuros();
		this.valorLiquido = this.valorLiquido - this.cheque.getValorLiquido();
		this.listacheques.remove(cheque);
	}
	public void cadastrar(){
		
	
		this.cheque.setDiasCheque(this.servico.calcularData(this.cheque.getDataEntradaCheque(), this.cheque.getDataVencimentoChque()));
		this.cheque.setJuros(this.servico.calculaJuros(this.getTaxaJuros(), this.cheque.getValor(), this.cheque.getDiasCheque()));
		this.cheque.setValorLiquido(this.servico.calculaValorLiquido(this.cheque.getValor(), this.cheque.getJuros()));
		this.setValorBruto(this.cheque.getValor());
		this.setValorJuros(this.cheque.getJuros());
		this.setValorLiquido(this.cheque.getValorLiquido());

		this.listacheques.add(this.cheque);
		
		this.cheque = new Cheque();
		
	
	}

	
	public List<Cheque> listaCheques(){
		return this.listacheques;
	}
	
	public void salvar(){
		this.servico.salvarCheques(listacheques);
		this.limpar();
	
		FacesUtil.addInfoMessage("Cheques Cadastrados Com Sucesso...");
	}
	private void limpar(){
		this.listacheques = new ArrayList<>();
		this.cheque = new Cheque();
		this.valorBruto=0;
		this.valorJuros=0;
		this.valorLiquido=0;
	}

	
	public Cheque getCheque(){
		return this.cheque;
	}
	

	
	public void setCheque(Cheque cheque) {
		this.rendered();
		this.cheque = cheque;
	}

	public double getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(double valorBruto) {
		this.valorBruto += valorBruto;
	}
	public double getValorJuros() {
		return valorJuros;
	}
	public void setValorJuros(double valorJuros) {
		this.valorJuros += valorJuros;
	}
	public double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido += valorLiquido;
	}


	public boolean getDescontoCheque() {
		return descontoCheque;
	}

	public void setDescontoCheque(boolean descontoCheque) {
		this.descontoCheque = descontoCheque;
	}


	public void rendered(){
	
		if(CalcularDesconto.CALCULOSIMPLES.equals(this.cheque.getTipoDesconto())){
			this.descontoCheque = false;
		}else{
			this.descontoCheque = true;
		}
	
	}
	
	public CalcularDesconto [] getTipoDesconto() {
		return CalcularDesconto.values();
	}

	public double getTaxaJuros() {
		return taxaJuros;
	}

	public void setTaxaJuros(double taxaJuros) {
		this.taxaJuros = taxaJuros;
	}



}
