package com.epochstiletto.github.security;

public class ValidateKey {

	private String inputKey;
	
	public ValidateKey(String inputKey){
		this.inputKey = inputKey;
	}
	
	public boolean equals(String other){
		return inputKey.equals(other);
	}
}
