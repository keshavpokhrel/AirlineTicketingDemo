package com.airlineticketing.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.airlineticketing.constent.City;
import com.airlineticketing.search.FlightsSearch;
import com.airlineticketing.vo.Flight;
import com.airlineticketing.vo.FlightResult;

public class FlightData {


	public static void main(String[] args) {
		List<FlightResult> result = FlightsSearch.searchFlights(City.MIAMI, City.CALIFORNIA);
		result.stream().forEach(r -> {
			System.out.println(r);
			System.out.println("***********");
			});
	}

	public static List<Flight> getAllFlights(){
		List<Flight> flights = new ArrayList<>();
		flights.add(new Flight(City.MIAMI, City.DENVER, Double.valueOf(450.0), new BigDecimal(400)));
		flights.add(new Flight(City.DENVER, City.WASHINGTON, Double.valueOf(550.0), new BigDecimal(500)));
		//below is extra line
		flights.add(new Flight(City.MIAMI, City.CALIFORNIA, Double.valueOf(1050.0), new BigDecimal(800)));
	
		flights.add(new Flight(City.WASHINGTON, City.CALIFORNIA, Double.valueOf(600.0), new BigDecimal(300)));		
		flights.add(new Flight(City.MIAMI, City.FLORIDA, Double.valueOf(600.0), new BigDecimal(400)));
		flights.add(new Flight(City.FLORIDA, City.CALIFORNIA, Double.valueOf(700.0), new BigDecimal(600)));
		flights.add(new Flight(City.MIAMI, City.CALIFORNIA, Double.valueOf(1500.0), new BigDecimal(1100)));
		flights.add(new Flight(City.DENVER, City.MIAMI, Double.valueOf(1500.0), new BigDecimal(1100)));
		flights.add(new Flight(City.MIAMI, City.WASHINGTON, Double.valueOf(450.0), new BigDecimal(400)));

		return flights;
	}
	
	public static Map<String, Set<Flight>> cityToFlightsMap(){
		Map<String, Set<Flight>> map = new HashMap<>();
		List<Flight> allFlights = getAllFlights();
		for(Flight flight: allFlights) {
			Set<Flight> flightsFromCity = null;
			if(map.containsKey(flight.getFrom())) {
				flightsFromCity = map.get(flight.getFrom());
			} else {
				flightsFromCity = new HashSet<Flight>();
			}
			flightsFromCity.add(flight);
			map.put(flight.getFrom(), flightsFromCity);
		}


		
		return map;
	}
}
