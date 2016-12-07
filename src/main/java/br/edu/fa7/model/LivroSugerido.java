package br.edu.fa7.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@JoinColumn(name = "id_sugestao", nullable = false)
	private SugestaoAquisicao sugestao;

	@ManyToOne
	@JoinColumn(name = "id_livro", nullable = false)
	private Livro livro;

	@Column(name = "qtde_solicitada", nullable = false)
	private Integer qtdeSolicitada;

	@Column(name = "qtde_adquirida", nullable = true)
	private Integer qtdeAdquirida;

	public LivroSugerido() {
	}

	public LivroSugerido(SugestaoAquisicao sugestao, Livro livro, Integer qtdeSolicitada) {
		super();
		this.sugestao = sugestao;
		this.livro = livro;
		this.qtdeSolicitada = qtdeSolicitada;
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

	public Integer getQtdeSolicitada() {
		return qtdeSolicitada;
	}

	public void setQtdeSolicitada(Integer qtdeSolicitada) {
		this.qtdeSolicitada = qtdeSolicitada;
	}

	public Integer getQtdeAdquirida() {
		return qtdeAdquirida;
	}

	public void setQtdeAdquirida(Integer qtdeAdquirida) {
		this.qtdeAdquirida = qtdeAdquirida;
	}
}
