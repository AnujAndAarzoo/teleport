package com.teleport.logistics.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogisticsDateUtil {

	public static String generateCurrentDateInFormat() {
		ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneOffset.of("+08:00"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

		return currentDateTime.format(formatter);
	}
}
