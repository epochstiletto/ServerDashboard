package com.epochstiletto.github;

import java.io.IOException;

import com.epochstiletto.github.dashboard.DashboardWindow;
import com.epochstiletto.github.files.MySQLServerConfig;
import com.epochstiletto.github.machine.Server;

public class Main {

	private static Server server;

	/*
	 * The default window for every component to the DashBoard.
	 */
	private static DashboardWindow mainWindow = new DashboardWindow();

	// TODO: Fix custom fonts.
	public static void main(String[] args) throws IOException {

		// int result =
		// CustomFonts.getCustomFontPosition(CustomFonts.getFontFileExtensions(),
		// ".ETX");
		// System.out.println(result);

		// SwingUtilities.invokeLater(new Runnable() {
		// @Override
		// public void run() {
		// mainWindow.create();
		// }
		// });
		MySQLServerConfig config = new MySQLServerConfig();
		config.create();

		server = new Server(23);
		while (true) {
			server.connect();
		}

	}

	public static DashboardWindow getMainWindow() {
		return mainWindow;
	}

	public static Server getServer() {
		return server;
	}
}
