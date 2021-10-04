package com.airlineticketing.vo;

import java.math.BigDecimal;

public class Flight {


	private String from;
	private String to;
	private Double distance;
	private BigDecimal price;

	public Flight(String from, String to, Double distance, BigDecimal price) {
		super();
		this.from = from;
		this.to = to;
		this.distance = distance;
		this.price = price;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public Double getDistance() {
		return distance;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [from=" + from + ", to=" + to + ", distance=" + distance + ", price=" + price + "]";
	}
	
}
