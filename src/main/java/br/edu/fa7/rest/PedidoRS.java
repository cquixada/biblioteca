package br.edu.fa7.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.edu.fa7.business.NenhumaSugestaoException;
import br.edu.fa7.business.SugestaoAquisicaoEJB;
import br.edu.fa7.model.PedidoDTO;

@Path("pedidos")
public class PedidoRS {
	@Inject
	private SugestaoAquisicaoEJB sugestaoAquisicaoEJB;

	@PUT
	@Path("notificar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarSugestao(@QueryParam("sugestao") Long idSugestao, PedidoDTO dto) {
		try {
			sugestaoAquisicaoEJB.atualizar(idSugestao, dto);

			return Response.status(Status.OK).build();

		} catch (NenhumaSugestaoException e) {
			throw new NotFoundException();
		}
	}
}