package com.epochstiletto.github.fonts;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class CustomFonts {

	private static final String[] fontFileExtension = new String[] { ".ABF", ".ACFM", ".AFM", ".AMFM", ".BDF", ".CHA",
			".COMPOSITEFONT", ".DFONT", ".EOT", ".ETX", ".EUF", ".F3F", ".FEA", ".FFIL", ".FNT", ".FON", ".FOT", ".GDR",
			".GF", ".GLIF", ".GXF", ".LWFN", ".MCF", ".MF", ".MXF", ".NFTR", ".ODTTF", ".OTF", ".PCF", ".PFA", ".PFB",
			".PFM", ".PFR", ".PK", ".PMT", ".SFD", ".SFP", ".SUIT", ".T65", ".TFM", ".TTC", ".TTE", ".TTF", ".TXF",
			".UFO", ".VFB", ".VLW", ".VNF", ".WOFF", ".WOFF2", ".XFN", ".XFT", ".YTF" };

	/**
	 * Loops through all CustomFont objects, then checks if the name specified
	 * is equal to that fonts file name.
	 * 
	 * @param name
	 *            - name of the file, must specify which file extension.
	 * @return
	 * @throws IOException
	 * @throws FontFormatException
	 */
	public static Font getCustomFont(String name) throws IOException, FontFormatException {
		for (CustomFont customFont : CustomFont.getCustomFonts()) {
			/*
			 * String fileName = customFont.getFile().getName(); if
			 * (name.equalsIgnoreCase(fileName.split("\\.")[0])) {
			 * 
			 * }
			 */
			if (name.equalsIgnoreCase(customFont.getFile().getName())) {
				return Font.createFont(Font.TRUETYPE_FONT, customFont.getFile());
			}
		}
		return null;
	}

	public static String[] getFontFileExtensions() {
		// System.out.println(fontFileExtension[1].split("\\.")[1].substring(0,
		// 1));
		return fontFileExtension;
	}

	public static int getCustomFontPosition(String[] arr, String extension) {
		for (String s : arr) {
			s = s.substring(1);
		}
		int l = 0, r = arr.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;

			int res = extension.compareTo(arr[m]);

			// Check if x is present at mid
			if (res == 0)
				return m;

			// If x greater, ignore left half
			if (res > 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}
		return -1;
	}
}
