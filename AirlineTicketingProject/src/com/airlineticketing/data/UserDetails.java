package com.airlineticketing.data;

public class UserDetails {
	private String userId;
	private String userName;
	private boolean isFrequentFlyerUser;
	private PromoCode promocode;
	private ExtraBaggage extrabaggae;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean getIsFrequentFlyerUser() {
		return isFrequentFlyerUser;
	}

	public void setIsFrequentFlyerUser(boolean isFrequentFlyerUser) {
		this.isFrequentFlyerUser = isFrequentFlyerUser;
	}

	public PromoCode getPromocode() {
		return promocode;
	}

	public void setPromocode(PromoCode promocode) {
		this.promocode = promocode;
	}

	public ExtraBaggage getExtrabaggae() {
		return extrabaggae;
	}

	public void setExtrabaggae(ExtraBaggage extrabaggae) {
		this.extrabaggae = extrabaggae;
	}

}
