package br.com.caelum.aeris.validators;

import java.util.Date;

import org.hibernate.validator.Validator;

import br.com.caelum.aeris.entity.Voo;

public class ChegadaMaiorQuePartidaValidator implements Validator<ChegadaMaiorQuePartida> {

	@SuppressWarnings("unused")
	private ChegadaMaiorQuePartida anotation;
	
	public boolean isValid(Object value) {
		Voo voo = (Voo) value;
		Date chegada = voo.getDataChegada();
		Date partida = voo.getDataPartida();
		return chegada.after(partida);
	}

	public void initialize(ChegadaMaiorQuePartida parameters) {
		this.anotation = parameters;
	}

}
