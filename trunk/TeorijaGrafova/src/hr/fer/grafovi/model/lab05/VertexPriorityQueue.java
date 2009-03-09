package hr.fer.grafovi.model.lab05;

import java.util.TreeSet;

public class VertexPriorityQueue
{
	TreeSet<Vertex> pq;
	
	public VertexPriorityQueue()
	{
		pq = new TreeSet<Vertex>();
	}

	public void add(Vertex v)
	{
		pq.add(v);
	}
	
	public Vertex getMax()
	{
		return pq.pollLast();
	}
	
	public void reSort(Vertex v, int value)
	{
		pq.remove(v);
		v.setWeight(value);
		pq.add(v);
	}
	
	public boolean isEmpty()
	{
		return pq.isEmpty();
	}
	
	
}
