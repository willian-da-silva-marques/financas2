package br.com.alura.financas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -7268507287792690301L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 80, nullable = false)
	private String nome;

	@Column(name = "profissao", length = 80, nullable = false)
	private String profissao;

	@OneToOne
	@JoinColumn(nullable = false, unique = true)
	private Endereco endereco;
	
	@OneToOne(mappedBy = "titular")
	@JoinColumn(nullable = false, unique = true)
	private Conta conta;

	public Cliente() {
	}

	public Cliente(String nome, String profissao, Endereco endereco, Conta conta) {
		this.nome = this.setNome(nome);
		this.profissao = this.setProfissao(profissao);
		this.endereco = this.setEndereco(endereco);
		this.conta = this.setConta(conta);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String setNome(String nome) {
		this.nome = nome;
		return this.nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public String setProfissao(String profissao) {
		this.profissao = profissao;
		return this.profissao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Endereco setEndereco(Endereco endereco) {
		this.endereco = endereco;
		return this.endereco;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public Conta setConta(Conta conta) {
		this.conta = conta;
		return this.conta;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
//		sb.append("\nId: ").append(id)
		sb.append("\nNome: ").append(nome);
//		.append("\nProfissao: ").append(profissao);
//		.append("\n[Endereco]: ").append(endereco);
		return sb.toString();
	}
}