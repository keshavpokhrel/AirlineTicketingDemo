package com.airlineticketing.data;

import java.util.Arrays;
import java.util.List;

public class FrequentFlyerUser {

	private static String[] frequantFlyerUserIds = { "1234", "5467", "6849", "8544", "7654" };
	private static List<String> frequantFlyerList = Arrays.asList(frequantFlyerUserIds);

	public static boolean isFrequestFlyer(String userId) {
		if (frequantFlyerList.contains(userId)) {
			return true;
		}
		return false;
	}
	 
}