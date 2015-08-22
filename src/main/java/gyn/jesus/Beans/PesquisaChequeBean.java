package gyn.jesus.Beans;

import gyn.jesus.filtros.ChequeFilter;
import gyn.jesus.model.CalcularDesconto;
import gyn.jesus.model.Cheque;
import gyn.jesus.repository.Cheques;



import gyn.jesus.service.ChequeService;
import gyn.jesus.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class PesquisaChequeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cheques cheques;
	
	@Inject
	private ChequeService chequeService;
	private boolean descontoCheque = false;
	private double total;
	private double juros;
	private double vliquido;
	private Cheque cheque;
	private Cheque chequeSelecionado;
	
	private ChequeFilter filtro;
	private List<Cheque> chequesFiltrados;
	
	
	public PesquisaChequeBean() {
		filtro = new ChequeFilter();
		this.chequesFiltrados = new ArrayList<>();
		this.chequeSelecionado = new Cheque();
	}

	public void pesquisar() {
		
		this.setTotal(0);
		this.setJuros(0);
		this.setVliquido(0);
		
		this.chequesFiltrados = this.cheques.filtrados(filtro);
		
		for(Cheque cheque : this.chequesFiltrados){
			this.total +=  cheque.getValor();
			this.juros += cheque.getJuros();
			this.vliquido += cheque.getValorLiquido();
			
		}
	}
	
	public void excluir(){
		
		this.chequeService.remover(this.chequeSelecionado);
		this.chequesFiltrados.remove(this.chequeSelecionado);
		FacesUtil.addInfoMessage("cheque " +this.chequeSelecionado.getCliente().getNome() + " excluido com sucesso");
	}
	
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity.");
         
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
	
	public List<Cheque> getchequesFiltrados() {
		return this.chequesFiltrados;
	}

	public ChequeFilter getFiltro() {
		return filtro;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public double getVliquido() {
		return vliquido;
	}

	public void setVliquido(double vliquido) {
		this.vliquido = vliquido;
	}

	public Cheque getChequeSelecionado() {
		return chequeSelecionado;
	}

	public void setChequeSelecionado(Cheque chequeSelecionado) {
		this.chequeSelecionado = chequeSelecionado;
	}
	
	public void rendered() {

		if (CalcularDesconto.CALCULOSIMPLES.equals(this.cheque.getTipoDesconto())) {
			this.descontoCheque = false;
		} else {
			this.descontoCheque = true;
		}

	}

	public CalcularDesconto[] getTipoDesconto() {
		return CalcularDesconto.values();
	}

	public boolean isDescontoCheque() {
		return descontoCheque;
	}

	public void setDescontoCheque(boolean descontoCheque) {
		this.descontoCheque = descontoCheque;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	
}