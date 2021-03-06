package br.edu.fa7.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.fa7.business.NenhumaSugestaoException;
import br.edu.fa7.business.PedidoException;
import br.edu.fa7.business.SugestaoAquisicaoEJB;
import br.edu.fa7.model.SugestaoAquisicao;

@Named
@RequestScoped
public class FazerPedidoBean {

	@Inject
	private SugestaoAquisicaoEJB sugestaoAquisicaoEJB;

	private SugestaoAquisicao sugestao;

	private SugestaoAquisicao ultimaAquisicao;

	public SugestaoAquisicao getSugestao() {
		return sugestao;
	}

	public void setSugestao(SugestaoAquisicao sugestao) {
		this.sugestao = sugestao;
	}

	public SugestaoAquisicao getUltimaAquisicao() {
		return ultimaAquisicao;
	}

	public void setUltimaAquisicao(SugestaoAquisicao ultimaAquisicao) {
		this.ultimaAquisicao = ultimaAquisicao;
	}

	@PostConstruct
	public void iniciar() {
		try {
			sugestao = sugestaoAquisicaoEJB.obterSugestaoAquisicaoAtiva();

		} catch (NenhumaSugestaoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		ultimaAquisicao = sugestaoAquisicaoEJB.obterUltimaAquisicao();
	}

	public String fazerPedido() {
		try {
			sugestaoAquisicaoEJB.fazerPedido(sugestao);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Pedido enviado à distribuidora.", "Pedido enviado à distribuidora."));

		} catch (PedidoException | NenhumaSugestaoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return null;
	}
}