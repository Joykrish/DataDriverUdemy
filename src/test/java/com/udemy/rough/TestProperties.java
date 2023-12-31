package com.udemy.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	public static void main(String[] args) throws IOException  {
		System.out.println(System.getProperty("user.dir"));
		Properties config=new Properties();
		Properties OR=new Properties();
		
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
		config.load(fs);
		FileInputStream fsi=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
		config.load(fs);
		OR.load(fsi);
		
		System.out.println(config.getProperty("browser"));
		System.out.println(OR.getProperty("bmlbutton"));
	}

}
