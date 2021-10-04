package com.airlineticketing.data;

import java.util.Arrays;
import java.util.List;

public class TravelerCode {

	private static String[] travlerCodeDataArray = { "1234", "5467", "6849", "8544", "7654" };
	private static List<String> travlerCodeList = Arrays.asList(travlerCodeDataArray);

	public static boolean validateTravelerCode(String travelerCode) {
		if (travlerCodeList.contains(travelerCode)) {
			return true;
		}
		return false;
	}
}