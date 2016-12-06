package br.edu.fa7.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Classe utilitaria para simplificar a injecao do EntityManager em outros pontos da aplicacao.
public class DatabaseProducer {

	@Produces
	@PersistenceContext(unitName = "bibliotecaPU")
	private EntityManager em;
}