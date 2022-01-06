package cities;

public class Road {
	private City city1;
	private City city2;
	private int length;

	public Road(City city1, City city2, int length) {
		this.city1 = city1;
		this.city2 = city2;
		this.length = length;
		this.city1.connect(this);// connect city1 with this road
		this.city2.connect(this);// connect city2 with this road
	}

	public City getCity1() {
		return city1;
	}

	public City getCity2() {
		return city2;
	}

	public int getLength() {
		return length;
	}
}
