package com.demo.cloverboard.cloverboardlibrary.data;

public class Theme {
	
	private String themeName;
	private String themeId;
	private int themeImageResource;
	public String getThemeId() {
		return themeId;
	}
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
	public String getThemeName() {
		return themeName;
	}
	public String getPlace_name() {
		return place_id;
	}
	public Theme(String themeName, String place_id, String themeId) {
		super();
		this.themeName = themeName;
		this.themeId = themeId;
		this.place_id = place_id;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public int getThemeImageResource() {
		return themeImageResource;
	}

	public void setThemeImageResource(int themeImageResource) {
		this.themeImageResource = themeImageResource;
	}

	public Theme(String themeName, String place_id, String themeId, int themeImageResource) {
		super();
		this.themeName = themeName;
		this.themeId = themeId;
		this.place_id = place_id;
		this.themeImageResource  = themeImageResource;
	}
	private String place_id;

}
