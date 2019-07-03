package fr.epita.quiz.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author mahesh
 *
 */
public class ConfigurationService {

	private static ConfigurationService instance;
	

	
	
	Properties props = new Properties();
	boolean init = false;
	/**
	 * Configuration of the data base
	 */
	private ConfigurationService() {
		// properties loading
		try {
			File confFile = new File("conf.properties");
			FileInputStream ips = new FileInputStream(confFile);
			props.load(ips);
			init =true;
		} catch (Exception e) {
			init=false;
		}

	}
/**
 * 
 * @returns ConfigurationService instance
 */
	public static ConfigurationService getInstance() {
		if (instance == null) {
			instance = new ConfigurationService();
		}
		return instance;
	}
	
	public boolean isInit() {
		return init;
	}

/**
 * gets ConfigurationValue
 * @param key
 * @param defaultValue
 * @return property value
 */

	public String getConfigurationValue(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}
/**
 * 
 * @param key
 * @param defaultValue
 * @return Property 
 */
	public String getConfigurationValue(ConfigEntry key, String defaultValue) {
		return props.getProperty(key.getKey(), defaultValue);
	}

}
