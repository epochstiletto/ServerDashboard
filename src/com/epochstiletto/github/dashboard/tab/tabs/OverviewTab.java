package com.epochstiletto.github.dashboard.tab.tabs;

import java.awt.Color;

import com.epochstiletto.github.dashboard.tab.DashboardTab;
import com.epochstiletto.github.dashboard.tab.DashboardTabBuilder;

public class OverviewTab extends DashboardTab {

	public OverviewTab() {
		super(new DashboardTabBuilder().setName("Overview").setWidth(200).setHeight(200).setColor(Color.BLUE)
				.setActivatedColor(Color.CYAN).setIsActivated(true).setIsMainTab(true));
		createTab();
	}
}
