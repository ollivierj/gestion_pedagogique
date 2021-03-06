package net.eni.gestion.pedagogie.commun.composant.propriete;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class PropertyFileLoader {

	private static PropertyFileLoader instance = null;
	private static final String PROPERTIES_FILE = ".properties";
	Properties properties = null;

	public PropertyFileLoader() {
		super();
	}

	public static PropertyFileLoader getInstance(String name) {
		if (instance == null) {
			instance = new PropertyFileLoader(name);
		}
		return instance;
	}

	public PropertyFileLoader(String name) {
		loadProperties(name);
	}

	public void loadProperties(String name) {
		properties = new Properties();
		StringBuilder lStrBuilder = new StringBuilder();
		lStrBuilder.append("/net/eni/gestion/pedagogie/commun/configuration/");
		lStrBuilder.append(name + PROPERTIES_FILE);
		InputStream in = this.getClass().getResourceAsStream(lStrBuilder.toString());
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(in);
		}

	}

	public String getValue(String key) {
		return properties.getProperty(key);
	}
}
