package com.demo.cloverboard.cloverboardlibrary.data;

public class Appliance {

	private String parent_id;
	private String applianceName;
	private Boolean isSlider;
	private int status;
	private Boolean isAuto;
	private Boolean isOn;
	private String other_appliance_id;

	public String getTheme_id() {
		return theme_id;
	}


	public String getOther_appliance_id() {
		return other_appliance_id;
	}

	private String theme_id;

	public enum ApplianceType{
		LIGHT_SOURCE_DIMMABLE,
		ONLY_LIGHT_SOURCE,
		NON_LIGHT_SOURCE_DIMMABLE,
		NON_LIGHT_SOURCE
	}

	public enum Role{
		OWN,
		OTHER_APPLIANCE,
		THEME
	}

	public String getParent_id() {
		return parent_id;
	}

	public Role roleAssigned = Role.OWN;
	public ApplianceType applianceType = ApplianceType.LIGHT_SOURCE_DIMMABLE;

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}


	public void setRoleToOtherAppliance(String appliance_id){

		roleAssigned = Role.OTHER_APPLIANCE;
		other_appliance_id = appliance_id;

	}

	public void setRoleToTheme(String theme_id){

		roleAssigned = Role.THEME;
		this.theme_id = theme_id;

	}

	public void setRoleToOwn(){
		roleAssigned = Role.OWN;
	}

	public Boolean getIsOn() {
		return isOn;
	}

	public void setIsOn(Boolean isOn) {
		this.isOn = isOn;
	}
	private String appliance_id;
	
	public String getAppliance_id() {
		return appliance_id;
	}


	public void setAppliance_id(String appliance_id) {
		this.appliance_id = appliance_id;
	}


	public String getID(){
		return appliance_id;
	}
	
	public Appliance(String parent_id, String applianceName, int status, Boolean isSlider, Boolean isAuto, Boolean isOn){
		this.parent_id = parent_id;
		this.applianceName = applianceName;
		this.status = status;
		this.isAuto = isAuto;
		this.isSlider = isSlider;
		this.isOn = isOn;
		appliance_id = parent_id + " " + applianceName;
	}
	
    
    public String getParentID() {
		return parent_id;
	}
	public String getApplianceName() {
		return applianceName;
	}
	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}
	public Boolean getIsSlider() {
		return isSlider;
	}
	public void setIsSlider(Boolean isSlider) {
		this.isSlider = isSlider;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Boolean getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
	}
}
