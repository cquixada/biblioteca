package br.edu.fa7.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livros_sugeridos")
public class LivroSugerido implements Serializable {
	private static final long serialVersionUID = -7477059861333220944L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_sugestao")
	private SugestaoAquisicao sugestao;

	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;

	private Integer quantidade;

	public LivroSugerido() {
	}

	public LivroSugerido(SugestaoAquisicao sugestao, Livro livro, Integer quantidade) {
		super();
		this.sugestao = sugestao;
		this.livro = livro;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SugestaoAquisicao getSugestao() {
		return sugestao;
	}

	public void setSugestao(SugestaoAquisicao sugestao) {
		this.sugestao = sugestao;
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
