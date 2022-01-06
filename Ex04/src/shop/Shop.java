package shop;

import java.util.ArrayList;
import java.util.List;

public class Shop {
	private List<Instrument> listOfInstruments ;
	
	public Shop(){
		listOfInstruments = new ArrayList<>();
	}
	
	public void add(Instrument i) {
		listOfInstruments.add(listOfInstruments.size(),i);
	}
	
	public Instrument get(int serial) {
		
		for(Instrument inst: listOfInstruments) {
			if(inst!=null) {//check first if it's null if not continue
				if(inst.getSerial()==serial) {
					return inst;//found the relevant serial number and return the instrument
				}
			}
		}
		return null; //if we didn't find the right serial number
	}
	
	public List<Integer> allSerials(){
		List<Integer> intList=new ArrayList<>();//create the list
		for(Instrument inst: listOfInstruments) {
			intList.add(inst.getSerial());
		}
		return intList;
	}
	
	public List<Integer> guitarsOfType(Type t){
		List<Integer> intList=new ArrayList<>();//create the list
		for(Instrument inst: listOfInstruments) {
			if(inst instanceof Guitar){//check if its guitar first
				if(((Guitar)inst).getType()==t) {//check if the type equals to type t
					intList.add(inst.getSerial());//if all of those tests passed than add into list
				}
			}
		}
		return intList;
	}

	public void sell(int serial) throws MusicShopException{
		boolean foundSerialNum=false;
		int indexOfGuitar=0;
		for(Instrument inst: listOfInstruments) {
			if(inst!=null) {
				if(inst.getSerial()==serial) {//if we have found the serial number we wanted
					if((inst instanceof Guitar)&& countNumOfGuitars()==1) {//check if it's the last guitar
						throw new MusicShopException("Its the last Guitar in the store, we can't sell it!");
					}
					foundSerialNum=true;//found the serial number
					listOfInstruments.remove(indexOfGuitar);
					break;
				}
			}
			indexOfGuitar++;
		}
		if(foundSerialNum==false) {//didn't find any item to sell  
			throw new MusicShopException("I don't have anything to sell to you");
		}
	}
	
	private int countNumOfGuitars() {
		int countGuitars=0;
		for(Instrument inst: listOfInstruments) {
			if(inst instanceof Guitar) {
				countGuitars++;
			}
		}
		return countGuitars;
	}
	
	public int sellAll(int[] serials) {
		int countNumOfInstruments=serials.length;
		for(int i=0;i<serials.length;i++)
		{
			try {
				sell(serials[i]);
				countNumOfInstruments--;
				
			} catch (MusicShopException e) {
				System.out.println(e.getMessage());
			}
		}
		return countNumOfInstruments;
	}
}
