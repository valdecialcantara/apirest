package com.clientes.apirest.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

	public static int calculaIdade(Date dataNasc){

		Calendar dateOfBirth = new GregorianCalendar();
		dateOfBirth.setTime(dataNasc);

		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);

		if (today.before(dateOfBirth)) {
			age--;
		}

		return age;
	}
	
}
