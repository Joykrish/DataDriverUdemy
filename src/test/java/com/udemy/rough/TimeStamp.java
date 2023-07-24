package com.udemy.rough;

import java.util.Date;

public class TimeStamp {
	
	public static void main(String[] args) {
		Date d=new Date();
		String ScreenshotName=d.toString().replace(":","_").replace(" ", "_");
		System.out.println(ScreenshotName +".JPG");
		System.out.println(d);
	}

}
