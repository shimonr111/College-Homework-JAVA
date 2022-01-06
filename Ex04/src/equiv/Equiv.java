package equiv;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Equiv<E> {
	
	private ArrayList<Set<E>> list;
	
	public Equiv() {
		list=new ArrayList<Set<E>>();//create array List of sets
	}

	public void add(E e1, E e2) {
		int firstIndex=-1,secondIndex=-1;
		if(are(e1,e2))
		{
			return; //if we have reached here than the two are in the same set in the array list
		}
		for(int i=0;i<list.size();i++)
		{
			 {
				if(list.get(i).contains(e1))//find the relevant set in which it's found
					firstIndex=i;
				if(list.get(i).contains(e2))//find the relevant set in which it's found
					secondIndex=i;
			}
		}
		if(firstIndex!=-1 && secondIndex!=-1)//if we have found both indexes
		{
			list.get(secondIndex).addAll(list.get(firstIndex));//take all in the first and put in the second
			list.get(firstIndex).clear();//flash all those in the first
		}
		if(firstIndex==-1 && secondIndex==-1) //if both of them are not found in the array list
		{
			Set<E> temp=new HashSet<E>();// create hash set that implements Set Interface
			temp.add(e1);//according to the hash code in the created object
			temp.add(e2);//according to the hash code in the created object
			list.add(temp);//add the set we have created to the list 
			return;
		}
		if(firstIndex==-1)//if the first is not in one of sets than add to the second
		{
			list.get(secondIndex).add(e1);
			return;
		}
		if(secondIndex==-1)//if the second is not in one of sets than add to the first
		{
			list.get(firstIndex).add(e2);
			return;
		}
		
	}
	
	public boolean are(E e1, E e2) {//check if both e1 and e2 are in the same set in the array list
		for(int indx=0;indx<list.size();indx++) {
			if(list.get(indx).contains(e1)) {
				if(list.get(indx).contains(e2)) {
					return true;
				}
			}
		}
		if(e1.equals(e2)) {
			return true;
		}
		return false;
	}
	
	
}
