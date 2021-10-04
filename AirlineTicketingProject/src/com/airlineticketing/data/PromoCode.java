package com.airlineticketing.data;

import java.math.BigDecimal;

public class PromoCode {

	private String promoCodeId;
	private BigDecimal discountValue;

	public PromoCode(String promoCodeId, BigDecimal discountValue) {
		super();
		this.promoCodeId = promoCodeId;
		this.discountValue = discountValue;
	}
	
	public PromoCode() {
	}

	public String getPromoCodeId() {
		return promoCodeId;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

}
