package com.siebel.md5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigurationFile {
	public static String getConfiguration(){
		Properties prop = new Properties();
		FileInputStream fileInput = null;

		try {
			String filename = "config.properties";
			fileInput= new FileInputStream(filename);
			prop.load(fileInput);
			return prop.getProperty("port");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally{
			if(fileInput!=null){
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}
}
