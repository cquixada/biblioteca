package br.edu.fa7.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "livros")
public class Livro implements Serializable {
	private static final long serialVersionUID = 902735805337004788L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = false)
	private String titulo;

	@Column(name = "id_produto", nullable = false)
	private Long idProduto;

	public Livro() {
	}

	public Livro(String title, Long idProduto) {
		this.titulo = title;
		this.idProduto = idProduto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Book");
		sb.append("{id=").append(id);
		sb.append(", title='").append(titulo);
		sb.append("'}");

		return sb.toString();
	}
}