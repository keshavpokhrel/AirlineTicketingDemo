package com.airlineticketing.test;

import java.math.BigDecimal;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.airlineticketing.data.TravelerCode;
import com.airlineticketing.search.FlightsSearch;
import com.airlineticketing.vo.FlightResult;

public class VerifyFlightDetails {

	static List<FlightResult> filghtDetails;

	@Test(priority = 0)
	@Parameters({ "origin", "destination" })
	public void pickShortestRoute(String origin, String destination) {
		filghtDetails = FlightsSearch.searchFlights(origin, destination);
		Double shortestDistance = filghtDetails.get(0).getTotalDistance();
		for (FlightResult result : filghtDetails) {
			if (shortestDistance > result.getTotalDistance()) {
				Assert.fail("Distance : " + shortestDistance + " from " + result.getFrom() + " to " + result.getTo()
						+ " is not correct.");
			}
		}

	}

	@Test(priority = 1)
	@Parameters({ "origin", "destination" })
	public void pickShortestThenChepestRoute(String origin, String destination) {
		filghtDetails = FlightsSearch.searchFlights(origin, destination);
		filghtDetails = FlightsSearch.searchFlights(origin, destination);
		Double shortestDistance = filghtDetails.get(0).getTotalDistance();
		BigDecimal cost = filghtDetails.get(0).getTotalPrice();
		for (FlightResult result : filghtDetails) {
			if (shortestDistance > result.getTotalDistance() && cost.compareTo(result.getTotalPrice()) > 0) {
				Assert.fail("Distance : " + shortestDistance + " from " + result.getFrom() + " to " + result.getTo()
						+ " is not correct.");
			}

		}
	}

	@Test(dependsOnMethods = { "pickShortestThenChepestRoute" })
	@Parameters({ "origin", "destination", "baggagesize" })
	public void addExtraBaggageToFilght(String origin, String destination, String baggage) {
		BigDecimal cost = filghtDetails.get(0).getTotalPrice();
		cost = cost.add(filghtDetails.get(0).getBaggage());
		Reporter.log("Final flight cost with Baggage :" + cost, true);
	}

	@Test // (dependsOnMethods = { "pickShortestThenChepestRoute" })
	@Parameters({ "origin", "destination", "promocode" })
	public void applyPromoCode(String origin, String destination, String promoCode) {
		BigDecimal cost = filghtDetails.get(0).getTotalPrice();
		BigDecimal discountPercentage = BigDecimal.valueOf(Math.round(Double.valueOf(promoCode)) / 100.00);
		BigDecimal discountAmount = cost.multiply(discountPercentage);
		cost = cost.subtract(discountAmount);
		Reporter.log("Final flight cost after " + promoCode + "% discount is :" + cost, true);
	}

	@Test // (dependsOnMethods = { "pickShortestThenChepestRoute" })
	@Parameters({ "origin", "destination", "travlerid" })
	public void checkFrequentFlyerTraveler(String origin, String destination, String travlerId) {
		if (TravelerCode.validateTravelerCode(travlerId)) {
			Reporter.log("Traveler:" + travlerId + " is eligible for " + "special lounge.", true);
		} else {
			Reporter.log("Traveler:" + travlerId + " is not eligible for " + "special lounge.", true);
		}

	}
}
