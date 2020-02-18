package br.com.alura.financas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 7601082209052898242L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cep", length = 8, nullable = false)
	private String cep;

	@Enumerated(EnumType.STRING)
	private Pais pais;

	@Enumerated(EnumType.STRING)
	private Uf uf;

	@Column(name = "cidade", length = 80, nullable = false)
	private String cidade;

	@Column(name = "bairro", length = 80, nullable = false)
	private String bairro;

	@Column(name = "logradouro", length = 255, nullable = false)
	private String logradouro;

	@Column(name = "complemento", length = 60, nullable = true)
	private String complemento;

	public Endereco() {
	}

	public Endereco(String cep, Pais pais, Uf uf, String cidade, String bairro, String logradouro, String complemento) {
		this.cep = this.setCep(cep);
		this.pais = this.setPais(pais);
		this.uf = this.setUf(uf);
		this.cidade = this.setCidade(cidade);
		this.bairro = this.setBairro(bairro);
		this.logradouro = this.setLogradouro(logradouro);
		this.complemento = this.setComplemento(complemento);
	}

	public Long getId() {
		return id;
	}

	public String getCep() {
		return cep;
	}

	public String setCep(String cep) {
		this.cep = cep;
		return this.cep;
	}

	public Pais getPais() {
		return pais;
	}

	public Pais setPais(Pais pais) {
		this.pais = pais;
		return this.pais;
	}

	public Uf getUf() {
		return uf;
	}

	public Uf setUf(Uf uf) {
		this.uf = uf;
		return this.uf;
	}

	public String getCidade() {
		return cidade;
	}

	public String setCidade(String cidade) {
		this.cidade = cidade;
		return this.cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public String setBairro(String bairro) {
		this.bairro = bairro;
		return this.bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String setLogradouro(String logradouro) {
		this.logradouro = logradouro;
		return this.logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String setComplemento(String complemento) {
		this.complemento = complemento;
		return this.complemento;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nId: ").append(id)
		.append("\nCep: ").append(cep)
		.append("\nPais: ").append(pais)
		.append("\nUf: ").append(uf)
		.append("\nCidade: ").append(cidade)
		.append("\nBairro: ").append(bairro)
		.append("\nLogradouro: ").append(logradouro)
		.append("\nComplemento: ").append(complemento);
		return sb.toString();
	}
}
