package com.airlineticketing.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

import com.airlineticketing.vo.FlightResult;

public class BookingTicket {
	
	public List<Object> bookticket(FlightResult flightResult, String userId,String userName, String extraBaggage, String promoCode) {	
		
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(userId);
		userDetails.setUserName(userName);
		PromoCode promoCodeDeatils = null;
		userDetails.setIsFrequentFlyerUser(FrequentFlyerUser.isFrequestFlyer(userId));
		ExtraBaggage extraBaggageDeatils = new ExtraBaggage();
		if (!promoCode.isEmpty()) {
			promoCodeDeatils = FlightData.promoCodeMap().get(promoCode.toUpperCase());
			if(promoCodeDeatils==null) {
				Reporter.log("Supplied promo code is not valid, Hence not applying.");
			}
			userDetails.setPromocode(promoCodeDeatils);
		}
		if (!extraBaggage.isEmpty()) {
			extraBaggageDeatils.setExtraBaggage(Double.valueOf(extraBaggage));
			userDetails.setExtrabaggae(extraBaggageDeatils);
		}

		BigDecimal ticketPrice = calculateFinalTicketPrice(flightResult.getTotalPrice(), userDetails);
		String bookingID = genrateBookingId(6);
		List<Object> bookingDetails = new ArrayList<Object>();
		bookingDetails.add(bookingID);
		bookingDetails.add(ticketPrice);
		bookingDetails.add(userDetails);
		return bookingDetails;

	}

	private static String genrateBookingId(int count) {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	private static BigDecimal calculateFinalTicketPrice(BigDecimal totalPrice, UserDetails userDetails) {

		BigDecimal cost = totalPrice;
		if (userDetails.getExtrabaggae() != null) {
			BigDecimal additionalAmount = BigDecimal.valueOf(userDetails.getExtrabaggae().getExtraBaggage().doubleValue()*ExtraBaggage.EXTRA_BAGGAGE_COST_PER_KG.doubleValue());
			cost = cost.add(additionalAmount);
		}
		if (userDetails.getPromocode() != null) {
			BigDecimal discountAmount = userDetails.getPromocode().getDiscountValue();
			cost = cost.subtract(discountAmount);
		}
		return cost;
	}
}
