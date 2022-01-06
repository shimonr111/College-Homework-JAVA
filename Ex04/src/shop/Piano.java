package shop;

public class Piano extends Instrument {
	
	private int octaves;
	public Piano(String company, int price, int octaves) {
		super(company,price);
		this.octaves=octaves;
	}
	
	public int getOctaves() {
		return octaves;
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Piano(");
		b.append(getOctaves());
		b.append(" octaves) ");
		b.append(getCompany());
		b.append("(");
		b.append(getSerial());
		b.append("), price = ");
		b.append(getPrice());
		return b.toString();
		
		
	}
}
