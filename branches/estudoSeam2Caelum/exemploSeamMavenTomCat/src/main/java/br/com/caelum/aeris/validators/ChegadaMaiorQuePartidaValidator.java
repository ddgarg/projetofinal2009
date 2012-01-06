package br.com.caelum.aeris.validators;

import java.util.Date;

import org.hibernate.validator.Validator;

import br.com.caelum.aeris.entity.Voo;

public class ChegadaMaiorQuePartidaValidator implements Validator<ChegadaMaiorQuePartida> {

	@SuppressWarnings("unused")
	private ChegadaMaiorQuePartida anotation;
	
	@Override
	public boolean isValid(Object value) {
		Voo voo = (Voo) value;
		Date chegada = voo.getDataChegada();
		Date partida = voo.getDataPartida();
		return chegada.after(partida);
	}

	@Override
	public void initialize(ChegadaMaiorQuePartida parameters) {
		this.anotation = parameters;
	}

}
