package com.epochstiletto.github.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySQLServer implements Runnable {

	private Connection connection;
	private Statement statement;

	private int port;

	private String host;
	private String database;
	private String username;
	private String password;

	public MySQLServer(int port, String host, String database, String username, String password) {
		this.port = port;
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public DataSource getDataSource() {
		MysqlDataSource source;

		source = new MysqlDataSource();
		source.setURL("jdbc:mysql://" + host + ":" + port + "/" + database);
		source.setUser(username);
		source.setPassword(password);
		try {
			source.getJdbcModifiableProperty("maxAllowedPacket").setValue(Integer.MAX_VALUE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return source;
	}

	public void openConnection() throws Exception {
		if (connection != null && !connection.isClosed()) {
			System.out.println("There is already a connection on this port.");
			return;
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = getDataSource().getConnection();
		System.out.println("MySQL connection established");
		this.statement = connection.createStatement();
	}

	public void createStatement(String statementString) throws SQLException {
		ResultSet set = statement.executeQuery(statementString);
		while (set.next()) {
			String username = set.getString("USERNAME");
			System.out.println(username);
		}
	}

	@Override
	public void run() {
		try {
			openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
