package cities;

public class City implements Comparable<City> {

	private String name;
	private Country country;
	private int population;
	public City(String name, Country country, int population) {
		this.country=country;
		this.population=population;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	public Country getCountry() {
		return country;
	}
	public int getPopulation() {
		return population;
	}
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(getName());
		b.append(" (of ");
		b.append(getCountry());
		b.append(")");
		return b.toString();
		}
	
	@Override
	public int compareTo(City o) {
		if(!(getCountry().equals(((City)o).getCountry()))) {//if not equal
			return getCountry().compareTo(o.getCountry());
		}
		//if we have got here than the countries equal
		return getName().compareTo(((City)o).getName());//compareTo of String Class
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof City)) {
			return false;
		}
		if(getCountry().equals(((City)o).getCountry())) {//if thats the same country we will check if the city name is the same
			if(getName().equals(((City)o).getName())){
				return true;
			}
		}
		return false;
	}

}
