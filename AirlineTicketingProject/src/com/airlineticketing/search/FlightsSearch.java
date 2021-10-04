package com.airlineticketing.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import com.airlineticketing.data.FlightData;
import com.airlineticketing.vo.Flight;
import com.airlineticketing.vo.FlightResult;

public class FlightsSearch {
	public static final int MAX_HOPS = 2;

	public static List<FlightResult> searchFlights(String from, String to) {
		List<Stack<Flight>> combinations = FlightsSearch.getAllFlightCombinations(from, to);

		List<FlightResult> list = new ArrayList<FlightResult>();
		if (combinations != null && !combinations.isEmpty()) {
			for (Stack<Flight> stack : combinations) {

				FlightResult flightResult = new FlightResult();

				stack.stream().forEach(flight -> {
					if (flight.getFrom().equals(from)) {
						flightResult.setFrom(from);
					}

					if (flight.getTo().equals(to)) {
						flightResult.setTo(to);
					} else {
						List<String> stops = flightResult.getStops();
						if (stops == null || stops.isEmpty()) {
							stops = new ArrayList<>();
						}
						if (stops.contains(flight.getTo()) || flightResult.getFrom().equals(flight.getTo())) {
							flightResult.setValid(false);
						}
						stops.add(flight.getTo());
						flightResult.setStops(stops);
					}
					flightResult.setTotalDistance(flightResult.getTotalDistance() == null ? flight.getDistance()
							: Double.valueOf(flightResult.getTotalDistance().doubleValue()
									+ flight.getDistance().doubleValue()));
					flightResult.setTotalPrice(flightResult.getTotalPrice() == null ? flight.getPrice()
							: flightResult.getTotalPrice().add(flight.getPrice()));
				});
				list.add(flightResult);
			}
		}
		Collections.sort(list);
		return list.stream().filter(f -> f.isValid()).collect(Collectors.toList());
	}

	public static List<Stack<Flight>> getAllFlightCombinations(String from, String to) {
		List<Stack<Flight>> combinations = new ArrayList<>();

		Map<String, Set<Flight>> cityToFlightsMap = FlightData.cityToFlightsMap();

		int count = 0;
		while (true) {
			if (count >= MAX_HOPS + 1) {
				break;
			}

			if (combinations.isEmpty()) {
				Set<Flight> flights = cityToFlightsMap.get(from);
				if (flights != null && !flights.isEmpty()) {
					for (Flight flight : flights) {
						Stack<Flight> stack = new Stack<>();
						stack.push(flight);
						combinations.add(stack);
					}
				}
			} else {
				List<Stack<Flight>> tempCombinations = new ArrayList<>();
				tempCombinations.addAll(combinations);
				for (Stack<Flight> stack : tempCombinations) {
					Flight lastFlight = stack.peek();
					if (!lastFlight.getTo().equals(to)) {
						Set<Flight> flights = cityToFlightsMap.get(lastFlight.getTo());
						if (flights != null && !flights.isEmpty()) {
							if (flights.size() == 1) {
								stack.push(flights.stream().findFirst().get());
							} else {
								int size = flights.size();
								for (int i = 0; i < size; i++) {
									if (i == size - 1) {
										stack.push((Flight) flights.toArray()[i]);
									} else {
										Stack<Flight> newStack = new Stack<>();
										newStack.addAll(stack);
										newStack.push((Flight) flights.toArray()[i]);
										combinations.add(newStack);
									}
								}
							}
						}
					}
				}
			}

			count++;
		}

		return filter(combinations, from, to);
	}

	private static List<Stack<Flight>> filter(List<Stack<Flight>> combinations, String from, String to) {
		List<Stack<Flight>> result = new ArrayList<>();
		if (combinations != null && !combinations.isEmpty()) {
			for (Stack<Flight> stack : combinations) {
				Flight lastFlight = stack.peek();
				if (lastFlight.getTo().equals(to)) {
					result.add(stack);
				}
			}
		}
		return result;
	}
}
