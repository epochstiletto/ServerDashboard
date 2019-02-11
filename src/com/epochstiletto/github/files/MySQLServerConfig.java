package com.epochstiletto.github.files;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MySQLServerConfig extends AbstractFile {

	public MySQLServerConfig() throws FileNotFoundException {
		super("mysql.ini");
	}

	@Override
	public void setDefaultValues() {
		try {
			createNewValuePath("port", 3306);
			createNewValuePath("host", "localhost");
			createNewValuePath("database", "databaseName");
			createNewValuePath("username", "username");
			createNewValuePath("password", "password");
			getFileWriter().flush();
			getFileWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
