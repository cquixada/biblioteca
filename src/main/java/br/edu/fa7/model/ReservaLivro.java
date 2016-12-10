package br.edu.fa7.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "reserva_livro")
@NamedQuery(name = "listarReserva", query = "SELECT rl FROM ReservaLivro rl")
public class ReservaLivro implements Serializable {

	private static final long serialVersionUID = 5162496192577076647L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;

	private Integer quantidade;

	public ReservaLivro() {
	}
	
	public ReservaLivro(Livro livro, Integer quantidade) {
		super();
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
