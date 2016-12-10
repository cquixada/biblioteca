package br.edu.fa7.business;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fa7.model.Estoque;
import br.edu.fa7.model.EstoqueLivro;

@Stateless
public class EstoqueLivroEJB {

	@Inject
	private EntityManager em;
	
	@Inject
	private Estoque estoque;	
	
	@PostConstruct
	public void carregarEstoque() {
		List<EstoqueLivro> estoqueLivros = em.createNamedQuery("listarEstoque", EstoqueLivro.class).getResultList();
		estoque.setEstoqueLivros(estoqueLivros);
	}

	public List<EstoqueLivro> getEstoqueLivros() {
		return estoque.getEstoqueLivros();
	}
}