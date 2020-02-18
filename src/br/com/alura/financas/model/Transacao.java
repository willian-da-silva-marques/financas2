package br.com.alura.financas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "transacao")
@NamedQuery(name = "findAllTransacoes", query = "SELECT t FROM Transacao t WHERE t.id IS NOT NULL")
@NamedQuery(name = "findTransacaoById", query = "SELECT t FROM Transacao t WHERE t.id = :pId")
@NamedQuery(name = "findTransacaoByContaId", query = "SELECT t FROM Transacao t WHERE t.conta.id = :pContaId")
@NamedQuery(name = "findTransacaoByTipoTransacao", query = "SELECT t FROM Transacao t WHERE t.tipoTransacao = :pTipoTransacao")
@NamedQuery(name = "findTransacaoByCategoria", query = "SELECT t FROM Transacao t INNER JOIN t.categoria c WHERE c = :pCategoria")
@NamedQuery(name = "findTransacaoByContaAndTipoTransacaoOrderByValor", query = "SELECT t FROM Transacao t INNER JOIN t.conta c WHERE c = :pConta and t.tipoTransacao = :pTipoTransacao ORDER BY t.valor DESC")
@NamedQuery(name = "somaValorDeTodasAsTransacoesByContaAndTipoTransacao", query = "SELECT sum(t.valor) FROM Transacao t INNER JOIN t.conta c WHERE c = :pConta and t.tipoTransacao = :pTipoTransacao")
@NamedQuery(name = "menorValorDeTodasAsTransacoesByContaAndTipoTransacao", query = "SELECT min(t.valor) FROM Transacao t INNER JOIN t.conta c WHERE c = :pConta and t.tipoTransacao = :pTipoTransacao")
@NamedQuery(name = "maiorValorDeTodasAsTransacoesByContaAndTipoTransacao", query = "SELECT max(t.valor) FROM Transacao t INNER JOIN t.conta c WHERE c = :pConta and t.tipoTransacao = :pTipoTransacao")
@NamedQuery(name = "mediaDosValorDeTodasAsTransacoesByContaAndTipoTransacao", query = "SELECT avg(t.valor) FROM Transacao t INNER JOIN t.conta c WHERE c = :pConta and t.tipoTransacao = :pTipoTransacao")
@NamedQuery(name = "contaQuantidadeDeTransacoesByConta", query = "SELECT count(t) FROM Transacao t INNER JOIN t.conta c WHERE c = :pConta")
public class Transacao implements Serializable {

	private static final long serialVersionUID = -5183311263427967988L;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	private static final NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data_hora", nullable = false)
	private LocalDateTime dataHora;

	@Column(name = "valor", nullable = false)
	private BigDecimal valor;

	@Column(name = "descricao", length = 200, nullable = false)
	private String descricao;

	@Column(name = "tipo_transacao", length = 1, nullable = false)
	private TipoTransacao tipoTransacao;

	@ManyToOne
	private Conta conta;

	@ManyToMany
	@JoinTable(name = "transacao_categoria", joinColumns = @JoinColumn(name = "transacao_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categoria;
	
	public Transacao() {}

	public Transacao(LocalDateTime dataHora, BigDecimal valor, String descricao, TipoTransacao tipoTransacao, Conta conta, List<Categoria> categoria) {
		this.dataHora = this.setDataHora(dataHora);
		this.valor = this.setValor(valor);
		this.descricao = this.setDescricao(descricao);
		this.tipoTransacao = this.setTipoTransacao(tipoTransacao);
		this.conta = this.setConta(conta);
		this.categoria = this.setCategoria(categoria);
	}

	public Long getId() {
		return id;
	}	

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public LocalDateTime setDataHora(LocalDateTime dataHora) {
		return this.dataHora = dataHora;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public BigDecimal setValor(BigDecimal valor) {
		return this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public String setDescricao(String descricao) {
		return this.descricao = descricao;
	}

	public TipoTransacao getTipoTransacao() {
		return tipoTransacao;
	}

	public TipoTransacao setTipoTransacao(TipoTransacao tipoTransacao) {
		return this.tipoTransacao = tipoTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public Conta setConta(Conta conta) {
		return this.conta = conta;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public List<Categoria> setCategoria(List<Categoria> categoria) {
		if (categoria == null)
			return this.categoria = categoria = new ArrayList<>();
		return this.categoria = categoria;
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
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String dataHoraStr = FORMATTER.format(this.dataHora);
		StringBuilder sb = new StringBuilder();
		
		sb.append("------------------------------------")
		.append("\nId: ").append(id)
		.append("\nData e Hora: ").append(dataHoraStr)
		.append("\nValor: ").append(FORMAT.format(valor))
		.append("\n------------------------------------\n");
//		.append("\nDescrição: ").append(descricao)
//		.append("\nTipo de Transação: ").append(tipoTransacao)
//		.append("\n[Conta]: ").append(conta)
//		.append("\n[Categoria]: ").append(categoria);
		return sb.toString();
	}
}