package com.uiFramework.myComp.myApp.helper.deviceConfig.config;


import com.uiFramework.myComp.myApp.helper.deviceConfig.DeviceType;
//Have implemented these methods in property reader class
public interface ConfigReader {

	public int getImpliciteWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public DeviceType getDeviceType();
}
