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
@Table(name = "estoque_livro")
@NamedQuery(name = "listarEstoque", query = "SELECT el FROM EstoqueLivro el ORDER BY el.livro.titulo ASC")
public class EstoqueLivro implements Serializable {
	
	private static final long serialVersionUID = 1326184949464067231L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;

	private Integer quantidade;

	public EstoqueLivro() {
	}

	public EstoqueLivro(Livro livro, Integer quantidade) {
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
