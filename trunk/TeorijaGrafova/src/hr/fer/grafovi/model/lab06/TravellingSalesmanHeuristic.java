package hr.fer.grafovi.model.lab06;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.WrongGraphException;
import hr.fer.grafovi.utilities.GraphUtilities;

public class TravellingSalesmanHeuristic {

	private Graph g;
	private boolean[] visited;
	private Edge[] shortestCycle;
	private int minLength;
	
	public TravellingSalesmanHeuristic(Graph g) throws WrongGraphException
	{
		if (g.directed())
			throw new WrongGraphException("Graf ne smije biti usmjeren");
		if (!g.weighted())
			throw new WrongGraphException("Graf nije tezinski");
		if (!GraphUtilities.isComplete(g))
			throw new WrongGraphException("Graf nije potpun");
		this.g = g;
		shortestCycle = new Edge[g.V()];
		visited = new boolean[g.V()];
		tsR(0, 0);
	}

	private void tsR(int depth, int v) {
		if (depth == g.V() - 1)
		{
			Edge e = g.edge(0, v);
			shortestCycle[v] = e;
			minLength += e.getWeight();
			return;
		}
		visited[v] = true;
		Edge e = shortestEdgeFrom(v);
		shortestCycle[v] = e;
		minLength += e.getWeight();
		tsR(depth + 1, e.other(v));
	}

	private Edge shortestEdgeFrom(int v) {
		int minWeight = 100;
		Edge min = null;
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (!visited[w] && e.getWeight() < minWeight)
			{
				min = e;
				minWeight = e.getWeight();
			}
		}
		return min;
	}
	
	public void printShortestCycle()
	{
		int v = 0;
		for (int i = 0; i < shortestCycle.length; i++)
		{
			System.out.print(shortestCycle[v] + " ");
			v = shortestCycle[v].other(v);
		}
		System.out.println();
	}
	
	public int getMinLength()
	{
		return minLength;
	}
}
