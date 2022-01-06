package cities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Country implements Comparable<Country> {
	private Set<City> cities;
	private String name;
	
	public Country(String name) {
		this.name=name;
	}
	
	public void addCity(City city) {
		if(this!=city.getCountry()) {//check if the city is in this country if not throw exception
			throw new IllegalArgumentException();
		}
		if(cities==null)//still empty
		{
			cities = new TreeSet<City>();
		}
		cities.add(city);//add to the tree set
	}
	public int population() {
		int sumOfPopulation=0;
		for(City c:cities) {
			sumOfPopulation+=c.getPopulation();
		}
		return sumOfPopulation;
	}
	
	public String toString() {
		return name;
	}
	
	public List<City> smallCities(int under){
		ArrayList<City> citiesBeneathUnderPopulation=new ArrayList<City>();
		for(City city:cities)
		{
			if(city.getPopulation()<under)// only the cities has population number smaller then under 
			{
				citiesBeneathUnderPopulation.add(city);
			}
		}
		return citiesBeneathUnderPopulation;//cities are already stored sorted in the tree set
	}

	@Override
	public int compareTo(Country o) {
		return name.compareTo(o.name);//use compareTo of String
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Country)) {
			return false;
		}
		return name.equals(((Country)o).name);//equals of String
	}
	
	public String report() {
		StringBuilder b = new StringBuilder();
		b.append(this);
		b.append("("+population()+") : ");
		for(City c:cities) {
			b.append(c.getName());
			b.append("(");
			b.append(c.getPopulation());
			b.append("), ");
		}
		b.deleteCharAt(b.length()-1);
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
