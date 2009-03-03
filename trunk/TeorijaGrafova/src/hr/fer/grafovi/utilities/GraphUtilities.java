package hr.fer.grafovi.utilities;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

import java.util.Arrays;
import java.util.Comparator;

public class GraphUtilities
{
	public static Edge[] edges(Graph g)
	{
		Edge[] edges = new Edge[g.E()];
		int k = 0;
		
		for (int i = 0; i < g.V(); i++)
		{
			AdjList a = g.getAdjList(i);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
				if (i > e.other(i))
					edges[k++] = e;
			
		}
		return edges;
	}
	
	public static Edge[] sortedEdges(Graph g)
	{
		Edge[] edges = edges(g);
		Arrays.sort(edges, new EdgeComparator());
		return edges;
	}
	
//	public static int[] GraphDegree(Graph g)
//	{
//		int deg[] = new int[g.V()];
//		for (int v = 0; v < g.V(); v++)
//		{
//			deg[v] = 0;
//			AdjList a = g.getAdjList(v);
//			for (a.beg(); !a.end(); a.nxt())
//				deg[v]++;
//		}
//		return deg;
//	}

	private static class EdgeComparator implements Comparator<Edge>
	{

		@Override
		public int compare(Edge e1, Edge e2)
		{
			if (e1 == null)
			{
				if (e2 == null)
					return 0;
				else 
					return -1;
			} else 
				if(e2 == null)
					return 1;
			if (e1.getWeight() > e2.getWeight())
				return 1;
			else if (e1.getWeight() < e2.getWeight())
				return -1;
			else
				return 0;
		}
		
	}

}
