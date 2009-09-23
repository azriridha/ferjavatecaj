package hr.fer.grafovi.model.lab04;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.WrongGraphException;

public class ShortestCycle {

	private Graph g;
	private int[] rank;
	private Edge[] path;
	private Edge[] cycle;
	private int length;
	
	public ShortestCycle(Graph g) throws WrongGraphException 
	{
		if (g.directed())
			throw new WrongGraphException("Graf ne smije biti usmjeren");
		this.g = g;
		rank = new int[g.V()];
		path = new Edge[g.V()];
		length = g.V() + 1;
		for(int i = 0; i < g.V(); i++)
			rank[i] = -1;
		for(int i = 0; i < g.V(); i++)
			if (rank[i] == -1)
				dfsR(0, i);
	}

	private void dfsR(int depth, int v) {
		rank[v] = depth;
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			path[depth] = e;
			int w = e.other(v);
			if (rank[w] == -1)
				dfsR(depth + 1, w);
			else
			{
				int distance = rank[v] - rank[w];
				if (distance > 1 && distance < length)
				{
					length = distance + 1;
					cycle = new Edge[length];
					System.arraycopy(path, rank[w], cycle, 0, length);
				}
			}
		}
	}
	
	public void printShortestCycle()
	{
		if (length > g.V())
		{
			System.out.println("graf nema ciklusa");
			return;
		}	
		for (int i = 0; i < length; i++)
			System.out.print(cycle[i] + " ");
		System.out.println();
	}
	
	public int getShortestCycleLength()
	{
		return length;
	}
	
}
