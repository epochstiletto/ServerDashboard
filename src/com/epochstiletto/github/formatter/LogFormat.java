package com.epochstiletto.github.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import com.epochstiletto.github.utils.StreamColor;

public class LogFormat extends Formatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder formatBuilder = new StringBuilder();

		formatBuilder.append(StreamColor.WHITE + "[");
		formatBuilder.append(currentTime(record));
		formatBuilder.append("]");
		formatBuilder.append(" [" + StreamColor.RESET);

		/*
		 * Check what Level is being logged, and then change the colors
		 * accordingly.
		 */
		if (record.getLevel().equals(Level.INFO)) {
			formatBuilder.append(StreamColor.LIGHT_BLUE + "INFO" + StreamColor.RESET);
		} else if (record.getLevel().equals(Level.SEVERE)) {
			formatBuilder.append(StreamColor.LIGHT_RED + "SEVERE" + StreamColor.RESET);
		} else if (record.getLevel().equals(Level.WARNING)) {
			formatBuilder.append(StreamColor.LIGHT_YELLOW + "WARNING" + StreamColor.RESET);
		} else if (record.getLevel() == null) {
			// Assume that when the record is null, it is from the chat.
			formatBuilder.append(StreamColor.MAGENTA + "CHAT" + StreamColor.RESET);
		} else {
			formatBuilder.append(StreamColor.MAGENTA + "UNKNOWN LOGGER" + StreamColor.RESET);
		}

		formatBuilder.append(StreamColor.WHITE + "] " + StreamColor.RESET);
		formatBuilder.append(StreamColor.WHITE + record.getMessage() + "\n");

		return formatBuilder.toString();
	}

	private String currentTime(LogRecord record) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}
}
