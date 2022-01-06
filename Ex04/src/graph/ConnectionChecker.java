package graph;

import java.util.HashSet;
import java.util.Set;

public class ConnectionChecker<V>{
	private GraphInterface<V> graph;
	private Set<V> setsHaveBeenUsed=new HashSet<V>();
	
	
	public ConnectionChecker(GraphInterface<V> graph) {
		this.graph=graph;
	}

	
	public boolean check(V v1, V v2) {
		if (v1.equals(v2)) {
			this.setsHaveBeenUsed.removeAll(setsHaveBeenUsed);
			return true;
		} 
		setsHaveBeenUsed.add(v1);
			for (V v : graph.neighbours(v1)) {
				if (v.equals(v1)==false && setsHaveBeenUsed.contains(v)==false && check(v,v2)) {
					return true;
				}

			}
			return false;	
	}
}
