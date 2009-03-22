package hr.fer.grafovi.model.lab04;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.WrongGraphException;
import hr.fer.grafovi.utilities.GraphUtilities;

public class TravellingSalesmanExact {

	private Graph g;
	private Edge[] cycle;
	private Edge[] shortestCycle;
	private boolean[] visited;
	private int minLength;
	
	public TravellingSalesmanExact(Graph g) throws WrongGraphException
	{
		if (g.directed())
			throw new WrongGraphException("Graf ne smije biti usmjeren");
		if (!g.weighted())
			throw new WrongGraphException("Graf nije tezinski");
		if (!GraphUtilities.isComplete(g))
			throw new WrongGraphException("Graf nije potpun");
		this.g = g;
		cycle = new Edge[g.V()];
		shortestCycle = new Edge[g.V()];
		visited = new boolean[g.V()];
		minLength = 100 * g.V();
		tsR(0, 0, 0);
	}

	private void tsR(int depth, int v, int length)
	{
		if (depth == g.V() - 1)
		{
			Edge e = g.edge(0, v);
			length += e.getWeight();
			cycle[depth] = e;
			if (length < minLength)
			{
				minLength = length;
				System.arraycopy(cycle, 0, shortestCycle, 0, g.V());
			}
			return;
		}
		
		visited[v] = true;
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (visited[w])
				continue;
			cycle[depth] = e;
			tsR(depth + 1, w, length + e.getWeight());
		}
		visited[v] = false;
	}
	
	public void printShortestCycle()
	{
		for (int i = 0; i < shortestCycle.length; i++)
			System.out.print(shortestCycle[i] + " ");
		System.out.println();
	}
	
	public int getMinLength()
	{
		return minLength;
	}
}
