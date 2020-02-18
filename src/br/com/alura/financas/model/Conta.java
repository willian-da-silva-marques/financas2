package br.com.alura.financas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "conta", uniqueConstraints = @UniqueConstraint(columnNames = { "numero_conta" }, name = "numero_uk"))
@NamedQuery(name = "findAllContas", query = "SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.transacoes WHERE c.id IS NOT NULL")
@NamedQuery(name = "findContaById", query = "SELECT c FROM Conta c WHERE c.id = :pId")
@NamedQuery(name = "findContaByTransacoes", query = "SELECT c FROM Conta c INNER JOIN c.transacoes t WHERE t = :pTransacoes")
public class Conta implements Serializable {

	private static final long serialVersionUID = 2850376970636640959L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Cliente titular;

	@Column(name = "numero_conta", length = 6, nullable = false)
	private String numero;

	@Column(name = "nome_banco", length = 45, nullable = false)
	private String banco;

	@Column(name = "numero_agencia", length = 4, nullable = false)
	private String agencia;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "conta", fetch = FetchType.LAZY)
	private List<Transacao> transacoes;

	public Conta() {
	}

	public Conta(Cliente titular, String numero, String banco, String agencia, List<Transacao> transacoes) {
		this.titular = this.setTitular(titular);
		this.numero = this.setNumero(numero);
		this.banco = this.setBanco(banco);
		this.agencia = this.setAgencia(agencia);
		this.transacoes = this.setTransacoes(transacoes);
	}

	public Long getId() {
		return id;
	}

	public Cliente getTitular() {
		return titular;
	}

	public Cliente setTitular(Cliente titular) {
		this.titular = titular;
		return this.titular;
	}

	public String getNumero() {
		return numero;
	}

	public String setNumero(String numero) {
		numero = numero.replaceAll("\\D", "");
		this.numero = numero;
		return this.numero;
	}

	public String getBanco() {
		return banco;
	}

	public String setBanco(String banco) {
		this.banco = banco;
		return this.banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public String setAgencia(String agencia) {
		this.agencia = agencia;
		return this.agencia;
	}

	public List<Transacao> getTransacoes() {
		if (this.transacoes == null)
			this.transacoes = new ArrayList<>();
		return transacoes;
	}

	public List<Transacao> setTransacoes(List<Transacao> transacoes) {
		if (transacoes == null) {
			return this.transacoes = new ArrayList<>();
		} else {
			this.transacoes = transacoes;
		}
		return this.transacoes;
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
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nId: ").append(id)
		.append("\n[Titular]: ").append(titular)
		.append("\nNúmero: ").append(numero)
		.append("\nBanco: ").append(banco)
		.append("\nAgência: ").append(agencia);
		return sb.toString();
	}
}