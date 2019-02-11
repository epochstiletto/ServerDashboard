package com.epochstiletto.github.dashboard.tab;

import java.awt.Color;

public class DashboardTabBuilder {

	private String name;
	private int width;
	private int height;
	private Color color;
	private Color activatedColor;
	private boolean isActivated;
	private boolean isMainTab;

	public DashboardTabBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public DashboardTabBuilder setWidth(int width) {
		this.width = width;
		return this;
	}

	public DashboardTabBuilder setHeight(int height) {
		this.height = height;
		return this;
	}

	public DashboardTabBuilder setColor(Color color) {
		this.color = color;
		return this;
	}

	public DashboardTabBuilder setActivatedColor(Color color) {
		this.color = color;
		return this;
	}

	public DashboardTabBuilder setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
		return this;
	}

	public DashboardTabBuilder setIsMainTab(boolean isMainTab) {
		this.isMainTab = isMainTab;
		return this;
	}
}
