package com.epochstiletto.github.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public abstract class AbstractFile {

	private String name;
	private Writer fileWriter;
	private Reader fileReader;

	public AbstractFile(String name) throws FileNotFoundException {
		this.name = name;
	}

	/**
	 * Create a new file.
	 * 
	 */
	public void create() {
		try {
			File tempFile = new File(name);
			if (!tempFile.exists()) {
				fileWriter = new BufferedWriter(new FileWriter(tempFile));
				fileReader = new BufferedReader(new FileReader(tempFile));
				setDefaultValues();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void createNewValuePath(String path, Object value) throws IOException {
		fileWriter.write(path + "=" + value + "\n");
	}

	public abstract void setDefaultValues();

	public String getName() {
		return name;
	}

	public Writer getFileWriter() {
		return fileWriter;
	}

	public Reader getFileReader() {
		return fileReader;
	}
}
