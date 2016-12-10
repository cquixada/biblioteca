package br.edu.fa7.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 5162496192577076647L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Column(name = "data_hora", nullable = true)
	private Date dataHora;
	
	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ReservaLivro> reservaLivro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	
	public List<ReservaLivro> getReservaLivro() {
		return reservaLivro;
	}

	public void setReservaLivro(List<ReservaLivro> reservaLivro) {
		this.reservaLivro = reservaLivro;
	}
}
