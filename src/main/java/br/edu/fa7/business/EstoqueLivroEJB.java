package br.edu.fa7.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fa7.model.EstoqueLivro;

@Stateless
public class EstoqueLivroEJB {

	@Inject
	private EntityManager em;

	public List<EstoqueLivro> listarTodos() {
		return em.createNamedQuery("listarEstoque", EstoqueLivro.class).getResultList();
	}
}