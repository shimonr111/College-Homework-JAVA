package shop;

public class Guitar extends Instrument{
	private Type type;
	
	public Guitar(String company, int price, Type type) {
		super(company,price);
		this.type=type;
	}

	public Type getType() {
		return type;
	}
	
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Guitar(");
		b.append(getType());
		b.append(") ");
		b.append(getCompany());
		b.append("(");
		b.append(getSerial());
		b.append("), price = ");
		b.append(getPrice());
		return b.toString();
	}
}
