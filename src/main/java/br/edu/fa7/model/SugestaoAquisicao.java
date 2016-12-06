package br.edu.fa7.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sugestoes_aquisicao")
@NamedQuery(name = "obterSugestaoAquisicaoAtiva", query = "SELECT sa FROM SugestaoAquisicao sa WHERE sa.ativa = true")
public class SugestaoAquisicao implements Serializable {
	private static final long serialVersionUID = 4546622568582391153L;

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date data;

	@OneToMany(mappedBy = "sugestao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<LivroSugerido> livrosSugeridos;

	@Column(nullable = false)
	private Boolean ativa;

	public SugestaoAquisicao() {
	}

	public SugestaoAquisicao(Boolean ativa) {
		this.ativa = ativa;
		livrosSugeridos = new HashSet<>();
	}

	public SugestaoAquisicao(Date data, Boolean ativa) {
		this.data = data;
		this.ativa = ativa;
		livrosSugeridos = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Set<LivroSugerido> getLivrosSugeridos() {
		return livrosSugeridos;
	}

	public void setLivrosSugeridos(Set<LivroSugerido> livrosSugeridos) {
		this.livrosSugeridos = livrosSugeridos;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
}
