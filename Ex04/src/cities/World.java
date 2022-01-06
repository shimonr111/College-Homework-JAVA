package cities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
	private Map<String, Country> countries;
	
	public World() {
		countries = new HashMap<String, Country>();
	}
	
	public void addCountry(String name) {
		countries.put(name, new Country(name));
	}
	
	public void addCity(String name, String countryName, int population) {
		if(countries.get(countryName)==null) {//if we didn't find the country in the hash map
			throw new IllegalArgumentException("The country you have asked is not in the data base");
		}
		City newCity = new City(name,countries.get(countryName),population);
		countries.get(countryName).addCity(newCity);
	}
	
	public int population() {
		int sum=0;
		for(Country c:countries.values()) {
			sum+=c.population();
		}
		return sum;
	}
	
	public List<City> smallCities(int under){
		List<City> citiesUnder = new ArrayList<City>();

		for (Country country : countries.values()) {
			citiesUnder.addAll(country.smallCities(under));
		}
		return citiesUnder;
	}
	
	public String report() {
		StringBuilder b = new StringBuilder();
		for (Country country : countries.values()) {
			b.append(country.report()+"\n");	
		}
		b.append("Total population is "+population()+"\n");
		return b.toString();
	}
}
