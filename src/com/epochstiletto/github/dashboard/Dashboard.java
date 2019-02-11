package com.epochstiletto.github.dashboard;

import com.epochstiletto.github.Main;
import com.epochstiletto.github.dashboard.event.DashboardEvent;
import com.epochstiletto.github.dashboard.event.Eventable;

public interface Dashboard {

	/**
	 * Update the DashBoard. Call this after an event has been executed.
	 */
	void update();

	/**
	 * This method triggers the event.
	 * 
	 * @param event
	 *            - the event being called.
	 */
	default void triggerEvent(DashboardEvent event) {
		event.on(this);
	}

	/**
	 * Check if a class inherits this interface.
	 * 
	 * @param clazz
	 *            - class to check.
	 * @return true or false based on whether a class inherits this interface.
	 */
	default boolean isDashboard(Class<?> clazz) {
		return Dashboard.class.isAssignableFrom(clazz);
	}

	/**
	 * Check if the class inheriting this interface also inherits
	 * {@link Eventable}.
	 * 
	 * @return true if class is inherits eventable.
	 */
	default boolean isEventable(Class<? extends Dashboard> clazz) {
		return DashboardEvent.class.isAssignableFrom(clazz);
	}

	/**
	 * The default window for all components of the DashBoard.
	 * 
	 * @return the main window.
	 */
	default DashboardWindow getMainWindow() {
		return Main.getMainWindow();
	}
}
