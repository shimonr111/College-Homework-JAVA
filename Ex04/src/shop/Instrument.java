package shop;

public abstract class Instrument {

	public static int serialNumCounter=0;
	private String company;
	private int price;
	private int serialNum;
	
	public Instrument(String company, int price) {
		this.company=company;
		this.price=price;
		this.serialNum=serialNumCounter++;
	}
	
	public int getPrice() {
		return price;
	}
	public String getCompany() {
		return company;
	}
	public int getSerial() {
		return serialNum;
	}
}
