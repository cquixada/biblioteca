package br.edu.fa7.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fa7.model.EstoqueLivro;
import br.edu.fa7.model.Livro;
import br.edu.fa7.model.LivroSugerido;
import br.edu.fa7.model.SugestaoAquisicao;

@Singleton
@Startup
public class DatabasePopulator {
	@Inject
	private EntityManager em;

	@PostConstruct
	public void init() {

		if (em.createQuery("from Livro").getResultList().size() == 0) {
			Livro livro1 = new Livro("Java como programar", 1000L);
			Livro livro2 = new Livro("C# como programar", 1001L);

			em.persist(livro1);
			em.persist(livro2);

			SugestaoAquisicao sugestao = new SugestaoAquisicao(new Date());

			em.persist(sugestao);
			
			EstoqueLivro estoqueLivro1 = new EstoqueLivro(livro1, 5);
			EstoqueLivro estoqueLivro2 = new EstoqueLivro(livro2, 4);
			
			em.persist(estoqueLivro1);
			em.persist(estoqueLivro2);

			LivroSugerido livroSugerido1 = new LivroSugerido(sugestao, livro1, 2);
			LivroSugerido livroSugerido2 = new LivroSugerido(sugestao, livro2, 1);					

			sugestao.getLivrosSugeridos().add(livroSugerido1);
			sugestao.getLivrosSugeridos().add(livroSugerido2);

			em.persist(sugestao);
		}
	}
}
