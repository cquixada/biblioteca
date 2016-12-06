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
public class SugestaoAquisicaoBean {

	@Inject
	private SugestaoAquisicaoEJB sugestaoAquisicaoEJB;

	private SugestaoAquisicao sugestao;

	public SugestaoAquisicao getSugestao() {
		return sugestao;
	}

	public void setSugestao(SugestaoAquisicao sugestao) {
		this.sugestao = sugestao;
	}

	@PostConstruct
	public void iniciar() {
		try {
			sugestao = sugestaoAquisicaoEJB.obterSugestaoAquisicaoAtiva();

		} catch (NenhumaSugestaoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}

	public String fazerPedido() {
		try {
			sugestao.setAtiva(!sugestaoAquisicaoEJB.fazerPedido(sugestao));

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Pedido enviado à distribuidora.", "Pedido enviado à distribuidora."));

		} catch (PedidoException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}

		return null;
	}
}