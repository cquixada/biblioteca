package br.edu.fa7.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estoque")
public class Estoque implements Serializable {
	
	private static final long serialVersionUID = 1326184949464067231L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<EstoqueLivro> estoqueLivros;

	public List<EstoqueLivro> getEstoqueLivros() {
		return estoqueLivros;
	}

	public void setEstoqueLivros(List<EstoqueLivro> estoqueLivro) {
		this.estoqueLivros = estoqueLivro;
	}

}
