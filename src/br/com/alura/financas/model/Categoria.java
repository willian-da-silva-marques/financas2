package br.com.alura.financas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
@NamedQuery(name = "findCategoriaById", query = "SELECT c FROM Categoria c WHERE c.id = :pId")
public class Categoria implements Serializable {

	private static final long serialVersionUID = -1840926221687983090L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_categoria", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCategoria tipoCategoria;

	public Categoria() {
	}

	public Categoria(TipoCategoria tipoCategoria) {
		this.tipoCategoria = this.setTipoCategoria(tipoCategoria);
	}

	public Long getId() {
		return id;
	}

	public TipoCategoria getTipoCategoria() {
		return tipoCategoria;
	}

	public TipoCategoria setTipoCategoria(TipoCategoria tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
		return this.tipoCategoria;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Código: ").append(this.id)
		.append(" Tipo: ").append(this.tipoCategoria.name());
		return sb.toString();
	}
}