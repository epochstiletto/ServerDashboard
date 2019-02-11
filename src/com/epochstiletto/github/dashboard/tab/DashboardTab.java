package com.epochstiletto.github.dashboard.tab;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.epochstiletto.github.Main;
import com.epochstiletto.github.dashboard.Dashboard;
import com.epochstiletto.github.dashboard.event.DashboardEvent;
import com.epochstiletto.github.dashboard.event.Eventable;
import com.epochstiletto.github.dashboard.event.tab.DashboardTabMouseClickEvent;

public class DashboardTab implements Dashboard, Eventable {

	private List<DashboardTab> tabs = new ArrayList<>();

	private JPanel tabPanel = new JPanel();

	/*
	 * The name of this tab.
	 */
	private String name;

	/*
	 * The width of this tab.
	 */
	private int width;

	/*
	 * The height of this tab.
	 */
	private int height;

	/*
	 * Color of this tab.
	 */
	private Color color;

	/*
	 * The color when this tab is activated.
	 */
	private Color activatedColor;

	/*
	 * Whether the tab open is this tab.
	 */
	private boolean isActivated;

	/*
	 * Is this tab the default tab? (The tab that is displayed when application
	 * is started.)
	 */
	private boolean isMainTab;

	public DashboardTab(DashboardTabBuilder builder) {
		tabs.add(this);
		tabPanel.setSize(600, 600);
		tabPanel.setVisible(true);
		Main.getMainWindow().getFrame().add(tabPanel);
	}

	@Override
	public void update() {
		// TODO: Switch tabs.
		// TODO: Draw the new tab
	}

	@Override
	public void triggerEvent(DashboardEvent event) {
		event.on(this);
	}

	/**
	 * Create the tab.
	 */
	public void createTab() {
		JLabel label = new JLabel();
		label.setText(name);
		label.setSize(width, height);
		label.setPreferredSize(new Dimension(width, height));
		label.setBackground(color);
		label.setVisible(true);
		JButton button = new JButton("Test");
		button.setSize(100, 100);
		button.setVisible(true);

		/*
		 * Trigger the event for our tab, so that we can do something later if
		 * we want to.
		 */
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (isEventable(DashboardTab.class)) {
					triggerEvent(new DashboardTabMouseClickEvent());
				} else {
					System.out.println("not eventable");
				}
			}
		});

		/*
		 * Add this tab to the main window.
		 */
		Main.getMainWindow().getFrame().add(label);
	}

	public List<DashboardTab> getTabs() {
		return tabs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getActivatedColor() {
		return activatedColor;
	}

	public void setActivatedColor(Color activatedColor) {
		this.activatedColor = activatedColor;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public boolean isMainTab() {
		return isMainTab;
	}

	public void setMainTab(boolean isMainTab) {
		this.isMainTab = isMainTab;
	}
}
