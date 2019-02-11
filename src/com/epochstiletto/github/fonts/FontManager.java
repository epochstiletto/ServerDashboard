package com.epochstiletto.github.fonts;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontManager {

	private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private static Font[] fonts = ge.getAllFonts();
	// ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Adobe
	// Garamond Pro Regular.ttf")));

	public static Font getGaramondProRegularFont() {
		for (Font font : fonts) {
			if (font.getName().equalsIgnoreCase("adobe garamond pro")) {
				return font;
			}
		}
		//private CustomFont font = new Custom
		return null;
	}

	public static Font[] getAllFonts() {
		return fonts;
	}
}
