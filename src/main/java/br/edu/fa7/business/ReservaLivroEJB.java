package br.edu.fa7.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fa7.model.Livro;
import br.edu.fa7.model.ReservaLivro;

@Stateful
public class ReservaLivroEJB {

	@Inject
	private EntityManager em;

	private List<ReservaLivro> reservasPendentes;

	@PostConstruct
	public void iniciar() {
		reservasPendentes = new ArrayList<>();
	}

	public List<ReservaLivro> listarTodos() {
		return em.createNamedQuery("listarReserva", ReservaLivro.class).getResultList();
	}

	public List<ReservaLivro> getReservasPendentes() {
		return reservasPendentes;
	}

	public void adicionarParaReserva(Livro livro) {
		if (!reservasPendentes.isEmpty()) {
			for (ReservaLivro reserva : reservasPendentes) {
				int qtdeReserva = reserva.getQuantidade();
				if (reserva.getLivro().getId() == livro.getId()) {
					reserva.setQuantidade(qtdeReserva + 1);
					return;
				}
			}
		}
		ReservaLivro reservaLivro = new ReservaLivro(livro, 1);
		reservasPendentes.add(reservaLivro);
	}

	public void finalizarReserva(HashMap<String, Integer> livrosQuantidade) {
		reservasPendentes.forEach(reserva -> {
			Integer qtdeDisponivel = livrosQuantidade.get(reserva.getLivro().getTitulo());
			if(reserva.getQuantidade() > qtdeDisponivel) {
				reserva.setQuantidade(qtdeDisponivel);
			}			
			if(qtdeDisponivel != 0) {
				em.persist(reserva);
			}			
		});
		reservasPendentes.removeAll(reservasPendentes);
	}

	@Remove
	public void terminar() {
		reservasPendentes = null;
	}
}