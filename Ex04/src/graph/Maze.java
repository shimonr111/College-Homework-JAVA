package graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Maze implements GraphInterface<Place>{
	
	private char[][] mat;
	private int size;
	private int startx;
	private int starty;
	private int endx;
	private int endy;
	
	public Maze(int size, int startx, int starty, int endx, int endy)
	{
		this.size=size;
		this.startx=startx;
		this.starty=starty;
		this.endx=endx;
		this.endy=endy;
		
		if(startx<0 || starty<0 || endx>size-1 || endy >size-1 || startx>size-1 || starty>size-1 || 
				endx<0 || endy<0)
		{
			throw new IllegalArgumentException();
		}
		
		mat = new char[size][size];
		for (int i=0;i<size;i++)
		{
			for (int j=0;j<size;j++)//put dot in each cell in the 2D array
			{
				mat[i][j]='.';
			}
		}
		mat[startx][starty] = 'S';
		mat[endx][endy] = 'E';
	}
	
	public boolean addWall(int x, int y)
	{
		if(x<0 || y<0 || x>size-1 || y >size-1)//out of bounds 
		{
			throw new IllegalArgumentException();
		}
		if ((x == startx && y == starty) || (x == endx && y == endy) || (mat[x][y] == '@'))
		{
			return false;
		}
		mat[x][y] = '@';
		return true;
	}

	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		
		for (int i=0;i<size;i++)
		{
			for (int j=0;j<size;j++)
			{
				s.append(mat[i][j]);
			}
			s.append("\n");
		}
		
		return s.toString();
	}
	
	public boolean isSolvable(){
		Graph<Place> newGraph = new Graph<Place>();
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (mat[i][j] == '.' || mat[i][j] == 'E' || mat[i][j] == 'S')
					try {

						newGraph.addVertex(new Place(i, j, size));
					} catch (GraphException e) {

						e.printStackTrace();
					}
			}
	//finished building the graph
	//now we will go all over the mat and add the places into the newGraph we have built
	Place newPlace;
	for (int i = 0; i < size; i++)
		for (int j = 0; j < size; j++) {
			if(checkExitence(i,j)) {
				newPlace=new Place(i,j,size);
				if(checkExitence(i+1,j)&&isInScope(i+1,j)) {
					try {
						newGraph.addEdge(newPlace, new Place(i+1,j,size));
					}catch (GraphException e) {
						
					}
				}
				if(checkExitence(i-1,j)&&isInScope(i+1,j)) {
					try {
						newGraph.addEdge(newPlace, new Place(i-1,j,size));
					}catch (GraphException e) {
						
					}
				}
				if(checkExitence(i,j+1)&&isInScope(i,j+1)) {
					try {
						newGraph.addEdge(newPlace, new Place(i,j+1,size));
					}catch (GraphException e) {
						
					}
				}
				if(checkExitence(i,j-1)&&isInScope(i,j-1)) {
					try {
						newGraph.addEdge(newPlace, new Place(i,j-1,size));
					}catch (GraphException e) {
						
					}
				}
			}
		}
	try {// check now if the Maze is is solvable (connect between the start point and end point)
		return newGraph.connected(new Place(startx, starty, size), new Place(endx, endy, size));
	} catch (GraphException e) {
		e.printStackTrace();
		return false;
	}
	
	}
	
	private boolean checkExitence(int i,int j) {
		if(isInScope(i,j)) {//check validity first 
		return (mat[i][j] == '.'|| mat[i][j] == 'S' || mat[i ][j] == 'E');
		}
		return false;
	}
	
	private boolean isInScope(int i,int j) {
		if(i < 0 || i > size - 1 || j < 0 || j > size - 1) {
			return false;
		}
		return true;
	}

	@Override
	public Collection<Place> neighbours(Place v) {
		int xVal = v.getX(), yVal = v.getY();
		Set<Place> places = new HashSet<Place>();//create an new hash set
		if (isInScope(xVal, yVal - 1) && checkExitence(xVal, yVal - 1))
			places.add(new Place(xVal,yVal - 1, size));
		if (isInScope(xVal + 1, yVal) && checkExitence(xVal + 1, yVal))
			places.add(new Place(xVal + 1, yVal, size));
		if (isInScope(xVal - 1,yVal) && checkExitence(xVal - 1,yVal))
			places.add(new Place(xVal - 1, yVal, size));
		if (isInScope(xVal, yVal + 1) && checkExitence(xVal, yVal + 1))
			places.add(new Place(xVal, yVal + 1, size));
		return places;
	}
}
