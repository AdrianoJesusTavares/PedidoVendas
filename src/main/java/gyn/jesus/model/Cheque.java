package gyn.jesus.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cheque implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private Cliente cliente;
	private String nomeCheque;
	private Date dataEntradaCheque;
	private Date dataVencimentoChque;
	private double valor;
	private String bancoEmissor;
	private int diasCheque;
	private double juros;
	private double valorLiquido;
	private Integer numCheque;
	private CalcularDesconto tipoDesconto;
	
	@Column(columnDefinition="text")
	private String observacao;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public double getJuros() {
		return juros;
	}
	public void setJuros(double juros) {
		this.juros = juros;
	}
	@Column(name="vl_liquido")
	public double getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	@Column(name="dias_cheque")
	public int getDiasCheque() {
		return diasCheque;
	}
	public void setDiasCheque(int diasCheque) {
		this.diasCheque = diasCheque;
	}
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=false)
	public Cliente getCliente() {
		return cliente;
	}
	

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Column(name="nome_cheque")
	public String getNomeCheque() {
		return nomeCheque;
	}
	public void setNomeCheque(String nomeCheque) {
		this.nomeCheque = nomeCheque;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="entrada_cheque")
	public Date getDataEntradaCheque() {
		return dataEntradaCheque;
	}
	public void setDataEntradaCheque(Date dataEntradaCheque) {
		this.dataEntradaCheque = dataEntradaCheque;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="vencimento_cheque")
	public Date getDataVencimentoChque() {
		return dataVencimentoChque;
	}
	public void setDataVencimentoChque(Date dataVencimentoChque) {
		this.dataVencimentoChque = dataVencimentoChque;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Column(name="banco")
	public String getBancoEmissor() {
		return bancoEmissor;
	}
	public void setBancoEmissor(String bancoEmissor) {
		this.bancoEmissor = bancoEmissor;
	}
	
	@Column(name="numero_cheque")
	public Integer getNumCheque() {
		return numCheque;
	}
	public void setNumCheque(Integer numCheque) {
		this.numCheque = numCheque;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	public CalcularDesconto getTipoDesconto() {
		return tipoDesconto;
	}
	public void setTipoDesconto(CalcularDesconto tipoDesconto) {
		this.tipoDesconto = tipoDesconto;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bancoEmissor == null) ? 0 : bancoEmissor.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime
				* result
				+ ((dataEntradaCheque == null) ? 0 : dataEntradaCheque
						.hashCode());
		result = prime
				* result
				+ ((dataVencimentoChque == null) ? 0 : dataVencimentoChque
						.hashCode());
		result = prime * result + diasCheque;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(juros);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((nomeCheque == null) ? 0 : nomeCheque.hashCode());
		result = prime * result
				+ ((numCheque == null) ? 0 : numCheque.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((tipoDesconto == null) ? 0 : tipoDesconto.hashCode());
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorLiquido);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cheque other = (Cheque) obj;
		if (bancoEmissor == null) {
			if (other.bancoEmissor != null)
				return false;
		} else if (!bancoEmissor.equals(other.bancoEmissor))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataEntradaCheque == null) {
			if (other.dataEntradaCheque != null)
				return false;
		} else if (!dataEntradaCheque.equals(other.dataEntradaCheque))
			return false;
		if (dataVencimentoChque == null) {
			if (other.dataVencimentoChque != null)
				return false;
		} else if (!dataVencimentoChque.equals(other.dataVencimentoChque))
			return false;
		if (diasCheque != other.diasCheque)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(juros) != Double
				.doubleToLongBits(other.juros))
			return false;
		if (nomeCheque == null) {
			if (other.nomeCheque != null)
				return false;
		} else if (!nomeCheque.equals(other.nomeCheque))
			return false;
		if (numCheque == null) {
			if (other.numCheque != null)
				return false;
		} else if (!numCheque.equals(other.numCheque))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (tipoDesconto != other.tipoDesconto)
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		if (Double.doubleToLongBits(valorLiquido) != Double
				.doubleToLongBits(other.valorLiquido))
			return false;
		return true;
	}
}
