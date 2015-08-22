package gyn.jesus.Beans;



import gyn.jesus.model.CalcularDesconto;
import gyn.jesus.model.Cliente;
import gyn.jesus.model.Cheque;
import gyn.jesus.repository.Clientes;
import gyn.jesus.service.ServicoLista;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroChequeBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//private Pessoa pessoa;
	private double valorBruto;
	private double valor;
	private double valorJuros;
	private double valorLiquido;
	private List<Cheque> listacheques = new ArrayList<Cheque>();
	private Cheque cheque;
	private Cheque chequeVazio ;
	private boolean descontoCheque = false;
	private double taxaJuros;
	
	private Cliente decontador;
	private String nomeCheque;
	private String banco;
	private int numcheque;
	private Date dataEntrada;
	private Date dataVencimento;
	
	double total;
	private List<Cliente> cliente;
	
	@Inject
	private ServicoLista servico;
	
	@Inject
	private Clientes clientes;
	

	
	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.cliente = new ArrayList<>();
			this.cheque = new Cheque();
			this.adicionarItemVazio();
			
		}
	}
	
	private void adicionarItemVazio() {
		this.chequeVazio = new Cheque();
		this.listacheques.add(0, this.chequeVazio);
		
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
		

		
		this.cheque.setCliente(this.decontador);
		this.cheque.setNomeCheque(this.nomeCheque);
		this.cheque.setBancoEmissor(this.banco);
		this.cheque.setDataEntradaCheque(this.dataEntrada);
		this.cheque.setDataVencimentoChque(this.dataVencimento);
		this.cheque.setNumCheque(this.numcheque);
		this.cheque.setValor(valor);
		
		this.cheque.setDiasCheque(this.servico.calcularData(this.cheque.getDataEntradaCheque(), this.cheque.getDataVencimentoChque()));
		this.cheque.setJuros(this.servico.calculaJuros(this.taxaJuros, this.cheque.getValor(), this.cheque.getDiasCheque()));
		this.cheque.setValorLiquido(this.servico.calulaValorLiquido(this.cheque.getValor(), this.cheque.getJuros()));
		this.setValorBruto(this.cheque.getValor());
		this.setValorJuros(this.cheque.getJuros());
		this.setValorLiquido(this.cheque.getValorLiquido());

		this.listacheques.add(this.cheque);
	
		this.cheque = new Cheque();
		this.valor = 0;
	
	}
	
	public boolean renderedTabela(){
		return  this.listacheques.get(0).getValor()==0;
	}
	public boolean rend(){
		return this.valor!=0;
	}
	
	public void imprimirData(){
		System.out.println(this.dataEntrada);
		System.out.println(this.dataVencimento);
	}
	
	public List<Cheque> listaCheques(){
		return this.listacheques;
	}
	
	public void salvar(){
		
		this.listacheques.remove(0);
		this.servico.salvarCheques(listacheques);
		this.limpar();
		this.adicionarItemVazio();
	
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

	public String getNomeCheque() {
		return nomeCheque;
	}

	public void setNomeCheque(String nomeCheque) {
		this.nomeCheque = nomeCheque;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public int getNumcheque() {
		return numcheque;
	}

	public void setNumcheque(int numcheque) {
		this.numcheque = numcheque;
	}

	public Cliente getDecontador() {
		return decontador;
	}

	public void setDecontador(Cliente decontador) {
		this.decontador = decontador;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	

}
