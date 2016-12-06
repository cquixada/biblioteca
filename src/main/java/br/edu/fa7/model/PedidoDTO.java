package br.edu.fa7.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PedidoDTO implements Serializable {
	private static final long serialVersionUID = 3437822806751365177L;

	private Long idCliente;

	private Map<Long, Integer> itens;

	public PedidoDTO() {
	}

	public PedidoDTO(Long idCliente) {
		super();
		this.idCliente = idCliente;
		itens = new HashMap<>();
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Map<Long, Integer> getItens() {
		return itens;
	}

	public void setItens(Map<Long, Integer> itens) {
		this.itens = itens;
	}
}
