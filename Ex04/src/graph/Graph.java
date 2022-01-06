package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<V> {
	private Set<V> vertices;
	private Map<V, Set<V>> edges;
	private Set<V> setsHaveBeenUsed=new HashSet<V>();
	
	public void addVertex(V v) throws GraphException{
		if(vertices==null) {
			vertices=new HashSet<V>();
		}
		if(vertices.contains(v)) {
			throw new GraphException("Already exists!");
		}
		vertices.add(v);
		
	}

	public void addEdge(V v1, V v2) throws GraphException{
		if(edges==null) {
			edges= new HashMap<V,Set<V>>();//create if hasn't created yet
		}
		if(hasEdge(v1,v2)) { //check if the edge between v1 and v2 exists
			throw new GraphException("Edge already exists!");
		}
		if(vertices.contains(v1)==false || vertices.contains(v2)==false) {
			throw new GraphException("one or more of the vertices is not in the Map!");
		}
		if(edges.containsKey(v1)) {//check if v1 already exist as a key in the hash map
			edges.get(v1).add(v2);//if exists than get the hash set and add v2 into the hash set
		}
		else {
			Set<V> tempHashset1 = new HashSet<V>();
			tempHashset1.add(v2);
			edges.put(v1,tempHashset1);
		}
		if(edges.containsKey(v2)) {//check if v2 already exist as a key in the hash map
			edges.get(v2).add(v1);//if exists than get the hash set and add v1 into the hash set
		}
		else {
			Set<V> tempHashset2 = new HashSet<V>();
			tempHashset2.add(v1);
			edges.put(v2,tempHashset2);
		}
		
	}
	
	public boolean hasEdge(V v1, V v2) {
		if ((vertices.contains(v1) ==false || vertices.contains(v2)==false))//check if exist in vertices
			return false;
		if (edges.containsKey(v1)) {//check if the hash map contains key v1
			return edges.get(v1).contains(v2);//if v2 is in the hash set of key v1 than return true
		}
		if (edges.containsKey(v2)) {//check if the hash map contains key v2
			return edges.get(v2).contains(v1);//if v1 is in the hash set of key v2 than return true
		}
		return false;
	}
	
	public boolean connected(V v1, V v2) throws GraphException{
		//
		if ((vertices.contains(v1) ==false|| vertices.contains(v2)==false))//check if exist in vertices
			throw new GraphException("one or more of the vertices is not in the Map!");
		if (v1.equals(v2)) {
			setsHaveBeenUsed.removeAll(setsHaveBeenUsed);
			return true;//found it 
		} else
			{
			setsHaveBeenUsed.add(v1);
			for (V tempV : vertices) {//go all over the vertices
				if (hasEdge(tempV, v1)==true && setsHaveBeenUsed.contains(tempV) == false && connected(tempV, v2)) {
					setsHaveBeenUsed.removeAll(setsHaveBeenUsed);
					return true;
				}
			}
			return false;
		}
	}
}
