package com.epochstiletto.github.machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.epochstiletto.github.data.MySQLServer;
import com.epochstiletto.github.formatter.LogFormat;
import com.epochstiletto.github.security.Authenticator;

public class Server implements Machine {

	/*
	 * This is the socket server.
	 */
	private ServerSocket serverSocket;

	/*
	 * This handle client connections.
	 */
	private Socket socket;

	private MySQLServer mySQLServer;

	/*
	 * The server output stream, to where data will be sent. e.x to the console
	 * when a client has sent a message.
	 * 
	 * Can be other forms of information too.
	 */
	private PrintWriter outputStream;

	/*
	 * The server input stream, where the data will be read and converted.
	 */
	private BufferedReader inputStream;

	/*
	 * A list of connected clients.
	 */
	private static List<Client> clients = new ArrayList<Client>();

	/*
	 * An instance of this.
	 */
	private static Server instance;

	/*
	 * This server's logger.
	 */
	private static Logger logger = Logger.getLogger("server");

	/*
	 * Message sent when a client is connecting.
	 */
	private String joinMessage = "A client with the name %name% has connected.";

	/*
	 * Message sent when a client is disconnecting.
	 */
	private String leaveMessage = "A client with the name %name% has disconnected.";

	/**
	 * Initializes the server on the specified port.
	 * 
	 * @param port
	 *            - server port.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Server(int port) {
		try {
			long startTime = System.nanoTime();

			this.serverSocket = new ServerSocket(port);
			// Connection con = DriverManager.getConnection(url, user, password)

			// Format logger.
			logger.setUseParentHandlers(false);
			ConsoleHandler handler = new ConsoleHandler();
			Formatter formatter = new LogFormat();
			handler.setFormatter(formatter);
			logger.addHandler(handler);

			long endTime = System.nanoTime();
			long totalTime = (endTime - startTime) / 1000000;

			logger.log(Level.INFO, "Server has successfully started!");
			logger.log(Level.INFO, "Took " + totalTime + " ms.");

			this.mySQLServer = new MySQLServer(port, "localhost", "user", "root", "password");
			Thread thread = new Thread(mySQLServer);
			thread.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
		instance = this;
	}

	/**
	 * Create an infinite loop before using this method, otherwise it will not
	 * accept incoming client requests.
	 * 
	 * <p>
	 * This will create a new thread for each client joining.
	 * 
	 * See {@link Client}
	 * 
	 * 
	 * @throws IOException
	 */
	public void connect() throws IOException {
		try {
			// Accept the connection request.
			this.socket = serverSocket.accept();

			this.outputStream = new PrintWriter(socket.getOutputStream(), true);
			this.inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Create a new client from the connection this server accepted.
			Client client = new Client(socket, outputStream, inputStream);
			logger.log(Level.WARNING, client.getName() + " is not authenticated.");

			long startTime = System.nanoTime();
			Authenticator authenticator = new Authenticator(client);
			System.out.println(authenticator.requestUniqueIdFromServer());
			long endTime = System.nanoTime();

			long duration = ((endTime - startTime) / 1000000);
			System.out.println(duration + " ms");
			logger.log(Level.INFO, joinMessage.replaceAll("%name%", client.getName()));

			// Create a new thread for each client.
			Thread thread = new Thread(client);

			// Add client to all current clients.
			clients.add(client);
			// thread.setName(client.getName());

			// Start thread.
			thread.start();

		} catch (Exception e) {
			// Close socket in case there was an exception.
			socket.close();
			e.printStackTrace();
		}
	}

	/**
	 * @return this server.
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	/**
	 * @return this servers connections.
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @return a list of all connected clients.
	 */
	public static List<Client> getClients() {
		return clients;
	}

	/**
	 * @return the instance of this server.
	 */
	public static Server getInstance() {
		return instance;
	}

	/*
	 * This server's logger.
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * @return the message sent when a client is connecting.
	 */
	public String getJoinMessage() {
		return joinMessage;
	}

	/**
	 * @return the message sent when a client is disconnecting.
	 */
	public String getLeaveMessage() {
		return leaveMessage;
	}

	/**
	 * This servers output stream, to where data will be sent. e.x to the
	 * console when a client has sent a message.
	 * <p>
	 * Can be other forms of information too.
	 * 
	 * @return this servers output stream.
	 */
	public PrintWriter getOutputStream() {
		return outputStream;
	}

	/**
	 * This servers input stream, where the data will be read and converted.
	 * 
	 * return this servers input stream.
	 */
	public BufferedReader getInputStream() {
		return inputStream;
	}

	/**
	 * @see {@link Machine#sendMessage(String)}
	 */
	@Override
	public void sendMessage(String message) {
		System.out.println(message);
	}
}
