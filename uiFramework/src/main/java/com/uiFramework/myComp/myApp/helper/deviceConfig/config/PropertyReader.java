package com.uiFramework.myComp.myApp.helper.deviceConfig.config;

import java.io.FileInputStream;
import java.util.Properties;
import com.uiFramework.myComp.myApp.helper.deviceConfig.DeviceType;
import com.uiFramework.myComp.myApp.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader{

	Properties OR;
	FileInputStream file ;
	public PropertyReader() {
	String filePath = ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
	try {
		 file = new FileInputStream(filePath);
		 OR = new Properties();
			OR.load(file);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	public int getImpliciteWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public DeviceType getDeviceType() {
		return DeviceType.valueOf(OR.getProperty("deviceType"));
	}

	
}
