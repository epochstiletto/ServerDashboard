package com.epochstiletto.github.dashboard.event;

import com.epochstiletto.github.dashboard.Dashboard;

/**
 * This interface is only used so that we do not need to specify what event it
 * is.
 * 
 * @author Stile
 *
 */
public interface DashboardEvent {

	/**
	 * Method being executed when an event is being called.
	 * 
	 * @param dashboard
	 *            - the DashBoard.
	 */
	void on(Dashboard dashboard);
}
