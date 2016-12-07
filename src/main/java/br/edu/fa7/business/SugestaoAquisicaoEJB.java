package br.edu.fa7.business;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.edu.fa7.model.LivroSugerido;
import br.edu.fa7.model.PedidoDTO;
import br.edu.fa7.model.SugestaoAquisicao;

@Stateless
public class SugestaoAquisicaoEJB {

	@Inject
	private EntityManager em;

	public SugestaoAquisicao obterSugestaoAquisicaoAtiva() throws NenhumaSugestaoException {
		try {
			SugestaoAquisicao sugestao = em.createNamedQuery("obterSugestaoAquisicaoAtiva", SugestaoAquisicao.class)
					.getSingleResult();

			return sugestao;

		} catch (NoResultException e) {
			throw new NenhumaSugestaoException();
		}
	}

	public void fazerPedido(SugestaoAquisicao sugestao) throws PedidoException {
		if (sugestao.getId() == null || sugestao.getDataEnvioPedido() != null) {
			throw new PedidoException();
		}

		// URL chamada pela Distribuidora após o processamento do Pedido.
		String callback = "http://localhost:8080/biblioteca/notificarPedido?controle=" + sugestao.getId();

		PedidoDTO dto = new PedidoDTO(1000L, callback);

		for (LivroSugerido livroSugerido : sugestao.getLivrosSugeridos()) {
			dto.getItens().put(livroSugerido.getLivro().getIdProduto(), livroSugerido.getQtdeSolicitada());
		}

		Gson gson = new Gson();

		try {
			Response resp = ClientBuilder.newClient().target("http://localhost:8090/distribuidora/solicitarPedido")
					.request(MediaType.APPLICATION_JSON).post(Entity.json(gson.toJson(dto)), Response.class);

			if (resp.getStatusInfo() == Response.Status.OK) {
				sugestao.setDataEnvioPedido(new Date());
				em.merge(sugestao);
			}

		} catch (Exception e) {
			throw new PedidoException();
		}
	}
}