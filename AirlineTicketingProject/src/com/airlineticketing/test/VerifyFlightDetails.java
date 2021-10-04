package com.airlineticketing.test;

import java.math.BigDecimal;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.airlineticketing.data.BookingTicket;
import com.airlineticketing.data.UserDetails;
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
		Double shortestDistance = filghtDetails.get(0).getTotalDistance();
		BigDecimal cost = filghtDetails.get(0).getTotalPrice();
		for (FlightResult result : filghtDetails) {
			if (shortestDistance > result.getTotalDistance() && cost.compareTo(result.getTotalPrice()) > 0) {
				Assert.fail("Distance : " + shortestDistance + " from " + result.getFrom() + " to " + result.getTo()
						+ " is not correct.");
			}

		}
	}

	@Test
	@Parameters({ "userId", "userName", "extraBaggage", "promoCode" })
	public void bookTicket(String userId, String userName, String extraBaggage, String promoCode) {
		List<Object> bookingDetails = new BookingTicket().bookticket(filghtDetails.get(0), userId, userName,
				extraBaggage, promoCode);
		String bookingId = (String) bookingDetails.get(0);
		BigDecimal ticketPrice = (BigDecimal) bookingDetails.get(1);
		boolean isFrequentFlyer = false;
		for (Object bookingDetail : bookingDetails) {
			if (bookingDetail instanceof UserDetails) {
				userName = ((UserDetails) bookingDetail).getUserName();
				if (((UserDetails) bookingDetail).getExtrabaggae() != null
						&& ((UserDetails) bookingDetail).getPromocode() == null) {
					Assert.assertTrue(ticketPrice.compareTo(filghtDetails.get(0).getTotalPrice()) > 0);
				}
				isFrequentFlyer = ((UserDetails) bookingDetail).getIsFrequentFlyerUser();
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Ticket Id : " + bookingId + "\n");
		sb.append("Flight Details : \n").append("Route : " + filghtDetails.get(0).getFrom());
		if (filghtDetails.get(0).getStops() != null && !filghtDetails.get(0).getStops().isEmpty()) {
			for (String stop : filghtDetails.get(0).getStops()) {
				sb.append(" => " + stop);
			}
		}
		sb.append(" => " + filghtDetails.get(0).getTo());
		sb.append("\nUser Id : " + userId);
		sb.append("\nUser Name : " + userName);
		sb.append("\nTotal Ticket Price : " + ticketPrice);
		sb.append("\nTotal Distance : " + filghtDetails.get(0).getTotalDistance());
		if (!promoCode.isEmpty()) {
			sb.append("\nApplied Promo Code :" + promoCode);
		}
		if (!extraBaggage.isEmpty()) {
			sb.append("\nExtra Baggage  is :" + extraBaggage + " K.G.");
		}
		if (isFrequentFlyer) {
			sb.append("\nUser " + userName + " is eligible for special lounge.");
		} else {
			sb.append("\nUser " + userName + " is not eligible for special lounge.");
		}
		Reporter.log(sb.toString(), true);

	}

}
