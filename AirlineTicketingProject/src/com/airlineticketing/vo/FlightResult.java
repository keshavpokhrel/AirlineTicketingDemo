package com.airlineticketing.vo;

import java.math.BigDecimal;
import java.util.List;

public class FlightResult implements Comparable<FlightResult> {

	private String from;
	private String to;
	private List<String> stops;
	private BigDecimal totalPrice;
	private Double totalDistance;
	private BigDecimal baggage = BigDecimal.valueOf(20);
	private boolean valid = true;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public List<String> getStops() {
		return stops;
	}
	public void setStops(List<String> stops) {
		this.stops = stops;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public BigDecimal getBaggage() {
		return baggage;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Flight Details : \n").append("Route : " + from);
		if(stops!=null && !stops.isEmpty()) {
			for(String stop: stops) {
				sb.append(" => " + stop);
			}
		}
		sb.append(" => " + to);
		sb.append("\nTotal Price : " + totalPrice);
		sb.append("\nTotal Distance : " + totalDistance);
		return sb.toString();
	}
	@Override
	public int compareTo(FlightResult o) {
		if(totalDistance.compareTo(o.totalDistance) == 0) {
			return totalPrice.compareTo(o.totalPrice);
		}
		return totalDistance.compareTo(o.totalDistance);
	}
	


}
