package resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadPropertyFile {
	public static String filePath= System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties";
	public static File file=new File(filePath);
	public static FileInputStream fis;
	public static Properties prop=new Properties();
	
	public static String URL;
	public static String username;
	public static String password;
	
	
	
	
	public static String getBaseURL() throws Exception {
		try {
			fis=new FileInputStream(file);
			prop.load(fis);
			URL=prop.getProperty("baseUrl");
			System.out.println(URL);
		}catch (Exception e) {
			throw e;
		}
		return URL;
	}
	
	
	
}
