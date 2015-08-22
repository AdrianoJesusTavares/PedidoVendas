package gyn.jesus.filtros;



import java.io.Serializable;
import java.util.Date;

public class ChequeFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;

	private String nomeCliente;



	public Date getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(Date dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public Date getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(Date dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	

}