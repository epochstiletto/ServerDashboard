package com.epochstiletto.github.fonts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * If you want to get a custom font by a specific name, see {@link CustomFonts}
 * 
 * @author Stile
 *
 */
public class CustomFont {

	private static List<CustomFont> customFonts = new ArrayList<>();
	private String path;
	private File file;

	/**
	 * Create a new CustomFont object.
	 * <p>
	 * This constructor will add an object of this class to the list of custom
	 * fonts.
	 * 
	 * @param path
	 *            to the font file.
	 * @param name
	 *            of the font file.
	 * @throws IOException
	 */
	public CustomFont(String path, String name) throws IOException {
		this.path = path;
		file = new File(path, name);
		if (!file.exists()) {
			throw new IOException("There is no font file with that name.");
		}
		customFonts.add(this);
	}

	/**
	 * Create a new CustomFont object.
	 * <p>
	 * This constructor will add an object of this class to the list of custom
	 * fonts.
	 * <p>
	 * This assumes the path to the file is "fonts". If you are not sure if that
	 * is the case, use {@link #CustomFont(String, String)}.
	 * 
	 * @param name
	 *            of the font file.
	 * @throws IOException
	 */
	public CustomFont(String name) throws IOException {
		this("fonts", name);
	}

	// TODO: Add custom fonts before I die.

	/*
	 * public void loadCustomFonts() { GraphicsEnvironment ge =
	 * GraphicsEnvironment.getLocalGraphicsEnvironment(); File[] files = new
	 * File(path).listFiles(); for(File currentFile : files){
	 * if(currentFile.isFile()){
	 * 
	 * if(currentFile.getName().split("\\.")[1].equalsIgnoreCase(CustomFonts.))
	 * } } }
	 */

	public static List<CustomFont> getCustomFonts() {
		return customFonts;
	}

	public File getFile() {
		return file;
	}
	
	public String getPath(){
		return path;
	}
}
