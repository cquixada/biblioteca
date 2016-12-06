package br.edu.fa7.business;

public class NenhumaSugestaoException extends Exception {
	private static final long serialVersionUID = -7630560255286639409L;

	public NenhumaSugestaoException() {
		super("Nenhuma sugestão para aquisição.");
	}
}
