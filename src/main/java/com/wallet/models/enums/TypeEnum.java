package com.wallet.models.enums;

public enum TypeEnum {
	EN("ENTRADA"), SD("SAIDA");

	private final String value;

	TypeEnum(String parametro) {
		value = parametro;
	}

	public String getValue() {
		return value;
	}

	/***
	 * Se o tipo for igual retorna uma enum
	 * 
	 * @param - String valor ex: "ENTRADA"
	 * @return - TypeEnum o tipo encontrado ou nulo
	 **/
	public static TypeEnum getEnum(String value) {
		for (TypeEnum t : values()) {
			if (t.getValue().equals(value)) {

				return t;
			}

		}

		return null;
	}
}
