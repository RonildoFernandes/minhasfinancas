package com.nivelamento.minhasfinancas.model.Excepition;

public class ErroAuteticacao extends RuntimeException {

	public ErroAuteticacao(String mensagem) {
		super(mensagem);
	}
}
