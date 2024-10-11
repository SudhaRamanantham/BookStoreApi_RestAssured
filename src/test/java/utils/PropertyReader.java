package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	public static Properties prop;

	public static String getStringProperty(String key) {
		return prop.getProperty(key);
	}

	public static Properties loadProperties() {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("src/test/resources/envVariables.properties");
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return prop;
	}

}
