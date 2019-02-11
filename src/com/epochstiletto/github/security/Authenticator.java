package com.epochstiletto.github.security;

import com.epochstiletto.github.machine.Client;

public class Authenticator {

	private Client client;
	private String inputValue = "";
	// inputValue = client.getAuthenticatorInputValue

	private char[] possibleUUIDCharacters = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	public Authenticator(Client client) {
		this.client = client;
	}

	public void authenticate() {
		/*
		 * TODO: Check if input is equal to the requestUniqueIdFromServer return
		 * value.
		 */
	}

	public String requestUniqueIdFromServer() {
		StringBuilder unique = new StringBuilder();
		for (int i = 0; i < 256; i++) {
			// TODO: Use regex instead of array.
			int randomNumber = client.getRandom().nextInt(possibleUUIDCharacters.length);
			unique.append(possibleUUIDCharacters[randomNumber]);
		}
		return unique.toString();
	}

}
