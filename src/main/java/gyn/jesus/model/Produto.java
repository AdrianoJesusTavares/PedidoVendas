package gyn.jesus.model;

import gyn.jesus.NegocioException;
import gyn.jesus.anotations.SKU;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@Size(max = 80)
	@Column(nullable = false, length = 80)
	private String nome;

	@NotBlank
	@SKU
	@Column(nullable = false, length = 20, unique = true)
	private String sku;

	@NotNull
	@Min(0)
	@Max(9999)
	@Column(name = "percentual_lucro", nullable = false, length = 5)
	private BigDecimal percentualLucro = BigDecimal.ZERO;

	@NotNull
	@Column(name = "valor_unitario", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorUnitario = BigDecimal.ZERO;

	@NotNull
	@Column(name = "valor_custo", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorCusto = BigDecimal.ZERO;

	@NotNull
	@Min(0)
	@Max(9999)
	@Column(name = "quantidade_estoque", nullable = false, length = 5)
	private Integer quantidadeEstoque;

	// @NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		this.sku = sku == null ? null : sku.toUpperCase();
	}

	public BigDecimal getPercentualLucro() {
		return percentualLucro;
	}

	public void setPercentualLucro(BigDecimal percentualLucro) {
		this.percentualLucro = percentualLucro;
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void baixarExtoque(Integer quantidade) {

		int novaQuantidade = this.getQuantidadeEstoque() - quantidade;

		if (novaQuantidade < 0) {
			throw new NegocioException("Nao a disponibilidade no Estoque de "
					+ quantidade + "itens do produto" + this.getSku());
		}

		this.setQuantidadeEstoque(novaQuantidade);

	}

	public void adicionarExtoque(Integer quantidade) {
		this.setQuantidadeEstoque(this.getQuantidadeEstoque() + quantidade);

	}

	@Transient
	public void calcularValorVenda() {

		BigDecimal valorVenda = this.getValorCusto();
		System.out.println("valor custo" + this.getValorCusto());
		System.out
				.println("valor percentual lucro" + this.getPercentualLucro());
		// BigDecimal percent = new BigDecimal(this.getPercentualLucro());

		valorVenda = valorVenda.add(this.getValorCusto()
				.multiply(this.getPercentualLucro())
				.divide(new BigDecimal(100)));
		this.setValorUnitario(valorVenda);
		System.out.println(valorVenda);
	}

}