package br.edu.fa7.model;

import java.io.Serializable;
import java.util.List;

public class Estoque implements Serializable {
	
	private static final long serialVersionUID = 1326184949464067231L;

	private List<EstoqueLivro> estoqueLivros;

	public List<EstoqueLivro> getEstoqueLivros() {
		return estoqueLivros;
	}

	public void setEstoqueLivros(List<EstoqueLivro> estoqueLivro) {
		this.estoqueLivros = estoqueLivro;
	}

}
