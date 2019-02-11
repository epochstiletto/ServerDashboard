package com.epochstiletto.github.dashboard.event.tab;

import com.epochstiletto.github.dashboard.Dashboard;
import com.epochstiletto.github.dashboard.event.DashboardEvent;
import com.epochstiletto.github.dashboard.tab.DashboardTab;

public class DashboardTabMouseClickEvent implements DashboardEvent {

	@Override
	public void on(Dashboard dashboard) {
		if (dashboard instanceof DashboardTab) {
			//TODO: Do something real
			System.out.println("Hello");
			dashboard.update();
		} else {
			throw new ClassCastException();
		}
	}
}
