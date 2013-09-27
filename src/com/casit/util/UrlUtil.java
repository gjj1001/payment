package com.casit.util;

import java.io.IOException;
import java.util.Properties;

public class UrlUtil {
	private static Properties p = new Properties();
	static {
		try {
			p.load(UrlUtil.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String readUrl(String key) {
		
		return (String)p.get(key);
	}
}
