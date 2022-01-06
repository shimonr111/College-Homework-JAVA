package graph;

public class Place {

	private int x;
	private int y;
	private final static  int prime = 31;
	
	public Place(int x, int y, int bound)
	{
		this.x=x;
		this.y=y;
		if (x<0 || y<0 || x>bound-1 || y>bound-1)
		{
			throw new IllegalArgumentException();
		}
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	@Override
	public int hashCode() 
	{
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	@Override
	public boolean equals(Object o) 
	{
		if (!(o instanceof Place))
		{
			return false;
		}
		
		if(x==((Place) o).x && y==((Place) o).y)
		{
			return true;
		}
			
		return false;
	}
}
