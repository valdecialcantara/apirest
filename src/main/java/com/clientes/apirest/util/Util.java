package com.clientes.apirest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

	private Util() {}
	
	public static int calculaIdade(Date dataNasc){

		Calendar dateOfBirth = new GregorianCalendar();
		int age = 0;
		
		if (dataNasc != null) {
			dateOfBirth.setTime(dataNasc);
			
			Calendar today = Calendar.getInstance();
			age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
			
			dateOfBirth.add(Calendar.YEAR, age);
			
			if (today.before(dateOfBirth)) {
				age--;
			}
			
		}

		return age;
	}
	
	public static Date parseDate(String valor) {
		String formato = "yyyy-MM-dd";
		SimpleDateFormat formater = new SimpleDateFormat(formato);
		if ((valor == null) || valor.isEmpty()) {
			return null;
		}
		try {
			return formater.parse(valor);
		} catch (ParseException ex) {
			throw new IllegalArgumentException(ex);
		}
	}	
	
}
