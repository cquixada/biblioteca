package br.edu.fa7.business;

public class PedidoException extends Exception {
	private static final long serialVersionUID = -5204096556440391743L;

	public PedidoException() {
		super("Ocorreu um erro ao fazer o pedido.");
	}
}
