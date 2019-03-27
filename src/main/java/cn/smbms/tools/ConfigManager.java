package cn.smbms.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager { //单例类
	 private static ConfigManager configManager;
	 private static Properties  properties;
	 
	 private  ConfigManager(){
		 String  configFile="database.properties";
		 properties =new Properties();
		 InputStream is=ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		 try {
			properties.load(is);
			is.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 }

	 public  static  ConfigManager  getInstance(){
		 if(configManager==null){
			 configManager=new ConfigManager();
		 }
		 return configManager;
	 }
	 
	 public  String  getValue(String key){
		 return properties.getProperty(key);
	 }
}
