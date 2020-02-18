package br.com.alura.financas.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.alura.financas.model.TipoTransacao;

@Converter(autoApply = true)
public class TipoTransacaoConverter implements AttributeConverter<TipoTransacao, String> {

	@Override
	public String convertToDatabaseColumn(TipoTransacao tipoTransacao) {
		return tipoTransacao.getLetra();
	}

	@Override
	public TipoTransacao convertToEntityAttribute(String string) {
		return TipoTransacao.getTipoTransacao(string);
	}

}
