package br.com.alura.financas.model;

public enum TipoTransacao {

	ENTRADA("E"), SAIDA("S");

	private String letra;

	private TipoTransacao(String letra) {
		this.letra = letra;
	}

	public String getLetra() {
		return letra;
	}

	public static TipoTransacao getTipoTransacao(String letra) {
		switch (letra) {
		case "E":
			return TipoTransacao.ENTRADA;
		case "S":
			return TipoTransacao.SAIDA;
		default:
			throw new IllegalArgumentException("A letra: ".concat(letra).concat(" é inválida"));
		}
	}

}
