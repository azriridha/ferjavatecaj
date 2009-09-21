package hr.fer.grafovi.model;

public class Edge
{
	private int v;
	private int w;
	private int weight;
	private boolean weighted;
	
	public Edge(int v, int w)
	{
		this.v = v;
		this.w = w;
		weight = 1;
		weighted = false;
	}
	
	public Edge(int v, int w, int weight) 
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
		weighted = true;
	}

	public int getV()
	{
		return v;
	}

	public int getW()
	{
		return w;
	}

	public int getWeight()
	{
		return weight;
	}
	
	public boolean from(int x)
	{
		if (x == v)
			return true;
		if (x == w)
			return false;
		else throw new IllegalArgumentException("Edge.from(int): argument nije vrh brida");
	}
	
	public int other(int x)
	{
		if (x == v)
			return w;
		if (x == w)
			return v;
		else throw new IllegalArgumentException("Edge.other(int): argument nije vrh brida");
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + v;
		result = prime * result + w;
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (v != other.v)
			return false;
		if (w != other.w)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		if (weighted)
			return v + "-" + w + "(" + weight + ")";
		else
			return v + "-" + w; 
	}

	
}
