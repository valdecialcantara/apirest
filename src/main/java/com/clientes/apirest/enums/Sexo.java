package com.clientes.apirest.enums;

public enum Sexo {

    M("Masculino"), // masculino
	F("Feminino"); 	// feminino
    
    private String label;
    
    private Sexo(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}