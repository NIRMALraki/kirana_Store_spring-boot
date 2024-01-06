package com.kirana.transaction.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
	
	
	public static LocalDateTime parseDateTime(String date) throws Exception
	{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime time = LocalDate.parse(date, formatter).atStartOfDay();
		System.out.println(time);
		
		return time;
		
		
	}
	
		
		

}
