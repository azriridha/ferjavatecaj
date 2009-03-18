package hr.fer.grafovi.model.lab13;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class LongestPath {

	private int[] weight;
	private Edge[] longestPathTree;
	
	public LongestPath(Graph g)
	{
		weight = new int[g.V()];
		longestPathTree = new Edge[g.V()];
		TopologicalSort ts = new TopologicalSort(g);
		
		for (int i = 0; i < g.V(); i++)
		{
			int v = ts.order(i);
			AdjList a = g.getAdjList(v);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				int w = e.other(v);
				if (weight[w] < weight[v] + e.getWeight())
				{
					weight[w] = weight[v] + e.getWeight();
					longestPathTree[w] = e;
				}
			}
		}
	}
	
	public Edge path(int v)
	{
		return longestPathTree[v];
	}
	
	public int distance(int v)
	{
		return weight[v];
	}
}
