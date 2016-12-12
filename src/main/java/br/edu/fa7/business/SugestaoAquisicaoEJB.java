package br.edu.fa7.business;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.edu.fa7.model.EstoqueLivro;
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

	public SugestaoAquisicao obterUltimaAquisicao() {
		try {
			SugestaoAquisicao sugestao = em.createNamedQuery("obterUltimaAquisicao", SugestaoAquisicao.class)
					.getSingleResult();

			return sugestao;

		} catch (NoResultException e) {
			return null;
		}
	}

	public SugestaoAquisicao obterSugestaoAquisicao(Long id) {
		try {
			SugestaoAquisicao sugestao = em.createNamedQuery("obterSugestaoAquisicaoPorId", SugestaoAquisicao.class)
					.setParameter("id", id).getSingleResult();

			return sugestao;

		} catch (NoResultException e) {
			return null;
		}
	}

	public void atualizar(Long idSugestao, PedidoDTO dto) throws NenhumaSugestaoException {
		SugestaoAquisicao sugestao = obterSugestaoAquisicao(idSugestao);

		if (sugestao == null) {
			throw new NenhumaSugestaoException();
		}

		Query query = em.createQuery("SELECT el FROM EstoqueLivro el WHERE el.livro.id = :idLivro");
		EstoqueLivro estoqueLivro;

		for (LivroSugerido livroSugerido : sugestao.getLivrosSugeridos()) {
			livroSugerido.setQtdeAdquirida(dto.getItens().get(livroSugerido.getLivro().getIdProduto()));

			// Atualiza a quantidade em estoque do livro.
			query.setParameter("idLivro", livroSugerido.getLivro().getId());
			estoqueLivro = (EstoqueLivro) query.getSingleResult();
			estoqueLivro.setQuantidade(estoqueLivro.getQuantidade() + livroSugerido.getQtdeAdquirida());
			em.merge(estoqueLivro);
		}

		// Atualiza a sugestão com as quantidades adquiridas.
		sugestao.setDataRetornoPedido(new Date());
		em.merge(sugestao);
	}

	public void fazerPedido(SugestaoAquisicao sugestao) throws PedidoException, NenhumaSugestaoException {
		if (sugestao.getId() == null || sugestao.getDataEnvioPedido() != null) {
			throw new PedidoException();
		}

		boolean nenhumaSugestao = true;

		for (LivroSugerido livroSugerido : sugestao.getLivrosSugeridos()) {
			if (livroSugerido.getQtdeSolicitada() > 0) {
				nenhumaSugestao = false;
			}
		}

		if (nenhumaSugestao) {
			throw new NenhumaSugestaoException();
		}

		// URL chamada pela Distribuidora após o processamento do Pedido.
		String callback = "http://localhost:8080/biblioteca/api/pedidos/notificar?sugestao=" + sugestao.getId();

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