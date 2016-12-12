package br.edu.fa7.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.fa7.business.EstoqueLivroEJB;
import br.edu.fa7.business.ReservaLivroEJB;
import br.edu.fa7.model.EstoqueLivro;
import br.edu.fa7.model.Livro;
import br.edu.fa7.model.ReservaLivro;

@Named
@ApplicationScoped
public class GerenciadorLivroBean {

	@Inject
	private EstoqueLivroEJB estoqueLivroEJB;

	@Inject
	private ReservaLivroEJB reservaLivroEJB;

	public List<EstoqueLivro> getEstoqueLivros() {
		return estoqueLivroEJB.getEstoqueLivros();
	}

	public List<ReservaLivro> getReservasPendentes() {
		return reservaLivroEJB.getReservasPendentes();
	}

	public List<ReservaLivro> getReservasFinalizadas() {
		return reservaLivroEJB.getReservasFinalizadas();
	}

	public void adicionarParaReserva(Livro livro) {
		reservaLivroEJB.adicionarParaReserva(livro);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Livro adicionado à lista de reserva.", "Livro adicionado à lista de reserva."));
	}

	public void finalizarReserva() {
		HashMap<String, Integer> livrosQuantidade = new HashMap<>();
		getEstoqueLivros().forEach(estoqueLivro -> {
			livrosQuantidade.put(estoqueLivro.getLivro().getTitulo(), estoqueLivro.getQuantidade());
		});
		getReservasFinalizadas().forEach(reservaLivro -> {
			Integer qtdeDisponivel = livrosQuantidade.get(reservaLivro.getLivro().getTitulo())
					- reservaLivro.getQuantidade();
			livrosQuantidade.put(reservaLivro.getLivro().getTitulo(), qtdeDisponivel);
		});
		reservaLivroEJB.finalizarReserva(livrosQuantidade);

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Lista de reserva finalizada", "Lista de reserva finalizada"));
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}