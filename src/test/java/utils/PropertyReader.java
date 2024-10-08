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
			FileInputStream fis = new FileInputStream("src/test/resources/EnvVariables.properties");
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return prop;
	}

}

//Both methods do the same thing. So you can write anything you understand better

//public static final String BASE_URI = loadPropertiesWithString("baseUri");

//public static String loadPropertiesWithString(String key) {
//	prop = new Properties();
//
//	try {
//		FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
//		prop.load(fis);
//	} catch (IOException e) {
//		throw new RuntimeException(e);
//	}
//	return prop.getProperty(key);
//}

//public static final String BASE_URI = loadProperties().getProperty("baseUri");
//
//public static Properties loadProperties() {
//    Properties prop = new Properties();
//    try {
//        InputStream is = AppConfig.class.getResourceAsStream("/global.properties");
//        prop.load(is);
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//    return prop;
//}
