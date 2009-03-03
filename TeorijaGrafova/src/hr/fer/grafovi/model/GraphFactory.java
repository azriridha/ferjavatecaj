package hr.fer.grafovi.model;

import java.util.Random;

public class GraphFactory
{
	public static Graph createCompleteGraph(int vcnt, boolean digraph, boolean weighted)
	{
		Graph g = new AdjecencyMatrix(vcnt, weighted, digraph);
		for (int i = 0; i < vcnt; i++)
			for (int j = i + 1; j<vcnt; j++)
			{
				g.insert(createEdge(i, j, weighted));	
				if (!digraph)
					continue;
				g.insert(createEdge(j, i, weighted));
			}
		return g;
	}
	
	public static Graph createCompleteBipartiteGraph(int r, int s, boolean digraph, boolean weighted)
	{
		Graph g = new AdjecencyLists(r+s, weighted, digraph);
		for(int i = 0; i < r; i++)
			for (int j = r; j < r+s; j++)
			{
				g.insert(createEdge(i, j, weighted));
				if (!digraph)
					continue;
				g.insert(createEdge(j, i, weighted));
			}
		return g;
	}
	
	public static Graph createWheelGraph(int vcnt, boolean digraph, boolean weighted)
	{
		Graph g = new AdjecencyLists(vcnt, weighted, digraph);
		for (int i = 1; i < vcnt - 1; i++)
		{
			g.insert(createEdge(i, i+1, weighted));
			g.insert(createEdge(i, 0, weighted));
		}
		g.insert(createEdge(vcnt-1, 1, weighted));
		g.insert(createEdge(vcnt-1, 0, weighted));
		return g;
	}
	
	public static Graph createCubeGraph(int k, boolean digraph, boolean weighted)
	{
		int v = (int) Math.pow(2,k);
		Graph g = new AdjecencyLists(v, weighted, digraph);
		
		int[] potencije = new int[k];
		potencije[0] = 1;
		for (int i = 1; i < k; i++)
			potencije[i] = potencije[i-1] * 2;
		for (int i = 0; i < v; i++)
			for (int j = 0; j < k; j++)
			{
				g.insert(createEdge(i, i ^ potencije[j], weighted));
				if (!digraph)
					continue;
				g.insert(createEdge(i ^ potencije[j], i, weighted));
			}
		return g;
	}
	
	public static Graph createCycleGraph(int vcnt, boolean digraph, boolean weighted)
	{
		Graph g = new AdjecencyLists(vcnt, weighted, digraph);
		
		for (int i = 0; i < vcnt; i++)
			g.insert(createEdge(i, (i+1)%vcnt, weighted));
		return g;
	}
	
	public static Graph createRandomGraph(int vcnt, int e, boolean digraph, boolean weighted)
	{
		Graph g = new AdjecencyLists(vcnt, weighted, digraph);
		double vjerojatnost = 2.0 * e / vcnt / (vcnt-1);
		for (int i = 0; i < vcnt; i++)
			for (int j = 0; j < vcnt; j++)
			{
				if (i == j)
					continue;
				if (!digraph && i < j)
					continue;
				if (Math.random() < vjerojatnost)
					g.insert(createEdge(i, j, weighted));
			}
		return g;
	}
	
	private static Edge createEdge(int i, int j, boolean weighted)
	{
		Edge e;
		Random rnd = new Random();
		int weight = 1;
		if (weighted)
		{
			weight = rnd.nextInt(99) + 1;
			e = new Edge(i, j, weight);
		} else 
			e = new Edge(i,j);
		return e;
	}
}
