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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sugestoes_aquisicao")
@NamedQueries({
		@NamedQuery(name = "obterSugestaoAquisicaoAtiva", query = "SELECT sa FROM SugestaoAquisicao sa WHERE sa.dataEnvioPedido IS NULL"),
		@NamedQuery(name = "obterUltimaAquisicao", query = "SELECT sa FROM SugestaoAquisicao sa WHERE sa.dataRetornoPedido = (SELECT MAX(s.dataRetornoPedido) FROM SugestaoAquisicao s)"),
		@NamedQuery(name = "obterSugestaoAquisicaoPorId", query = "SELECT sa FROM SugestaoAquisicao sa WHERE sa.id = :id") })
public class SugestaoAquisicao implements Serializable {
	private static final long serialVersionUID = 4546622568582391153L;

	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_envio_pedido", nullable = true)
	private Date dataEnvioPedido;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_retorno_pedido", nullable = true)
	private Date dataRetornoPedido;

	@OneToMany(mappedBy = "sugestao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<LivroSugerido> livrosSugeridos;

	public SugestaoAquisicao() {
	}

	public SugestaoAquisicao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
		livrosSugeridos = new HashSet<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Date getDataEnvioPedido() {
		return dataEnvioPedido;
	}

	public void setDataEnvioPedido(Date dataEnvioPedido) {
		this.dataEnvioPedido = dataEnvioPedido;
	}

	public Date getDataRetornoPedido() {
		return dataRetornoPedido;
	}

	public void setDataRetornoPedido(Date dataRetornoPedido) {
		this.dataRetornoPedido = dataRetornoPedido;
	}

	public Set<LivroSugerido> getLivrosSugeridos() {
		return livrosSugeridos;
	}

	public void setLivrosSugeridos(Set<LivroSugerido> livrosSugeridos) {
		this.livrosSugeridos = livrosSugeridos;
	}
}
