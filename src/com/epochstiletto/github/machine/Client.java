package com.epochstiletto.github.machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;

import com.epochstiletto.github.security.Authenticator;
import com.epochstiletto.github.utils.StreamColor;

public class Client implements Runnable, Machine {

	private Socket socket;

	private final PrintWriter outputStream;
	private final BufferedReader inputStream;
	private String name = UUID.randomUUID().toString();
	private boolean isAuthenticated = false;
	private String authenticationKey;

	// This clients random unique identifier. (Used in Authenticator)
	private Random random;

	public Client(Socket socket, PrintWriter outputStream, BufferedReader inputStream) throws IOException {
		this.socket = socket;
		this.outputStream = outputStream;
		this.inputStream = inputStream;
		this.random = new Random();
	}

	@Override
	public void run() {
		String received;
		while (true) {
			try {
				received = inputStream.readLine();

				/*
				 * Check whether the received variable is NOT null. This works
				 * because if the GUI (currently command prompt) is closed,
				 * received will be null.
				 */
				if (received != null) {

					/*
					 * Loop through all clients and send them a message.
					 */
					for (Client client : Server.getClients()) {
						client.sendMessage(name + ": " + StreamColor.MAGENTA + received + StreamColor.RESET);
					}
					
					/*
					 * Send the received message to the server.
					 */
					Server.getInstance()
							.sendMessage(StreamColor.WHITE + "[" + currentTime() + StreamColor.WHITE + "] ["
									+ StreamColor.MAGENTA + "CHAT" + StreamColor.WHITE + "] " + name + ": "
									+ StreamColor.RESET + received);
				} else {
					/*
					 * Prevent memory leaks by removing this client from the
					 * current connections list.
					 */
					Server.getClients().remove(this);

					/*
					 * Log that this client is disconnecting.
					 */
					Server.getLogger().log(Level.INFO,
							Server.getInstance().getLeaveMessage().replaceAll("%name%", name));

					/*
					 * Close the socket.
					 */
					socket.close();
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String currentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}

	/**
	 * @return this clients socket.
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * 
	 * @return this clients output stream.
	 */
	public PrintWriter getOutputStream() {
		return outputStream;
	}

	/**
	 * 
	 * @return this clients input stream.
	 */
	public BufferedReader getInputStream() {
		return inputStream;
	}

	/**
	 * @return this clients name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @see {@link Machine#sendMessage(String)}
	 */
	@Override
	public void sendMessage(String message) {
		outputStream.println(message);
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	/**
	 * {@link Authenticator}
	 * 
	 * @return this clients random object.
	 */
	public Random getRandom() {
		return random;
	}
}
