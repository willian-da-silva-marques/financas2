package br.com.alura.financas.exception;

public class RegistroNaoLocalizadoException extends RuntimeException {

	private static final long serialVersionUID = 409238263033886777L;

	public RegistroNaoLocalizadoException() {

	}

	public RegistroNaoLocalizadoException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
