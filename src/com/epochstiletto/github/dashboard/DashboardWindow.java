package com.epochstiletto.github.dashboard;

import javax.swing.JFrame;

import com.epochstiletto.github.dashboard.tab.tabs.OverviewTab;

public class DashboardWindow {

	private JFrame frame;
	private String name = "Server Dashboard";

	public DashboardWindow() {

	}

	public void create() {
		frame = new JFrame(name);
		frame.setSize(1200, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		new OverviewTab();
	}

	public JFrame getFrame() {
		return frame;
	}
}
