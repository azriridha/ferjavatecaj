package hr.fer.grafovi.model.lab6;

public class Vertex implements Comparable<Vertex>
{
	private int v;
	private int weight;
	
	public Vertex(int v, int weight)
	{
		this.v = v;
		this.weight = weight;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public int getV()
	{
		return v;
	}

	@Override
	public boolean equals(Object other)
	{
		if (! (other instanceof Vertex))
			return false;
		return ((Vertex)other).v == v;  
	}

	@Override
	public int compareTo(Vertex other)
	{
		if (other == null)
			return 1;
		if (other.v == v)
			return 0;
		if (weight > other.weight)
			return 1;
		if (weight < other.weight)
			return -1;
		if (v > other.v)
			return 1;
		return -1;
	}	
}
