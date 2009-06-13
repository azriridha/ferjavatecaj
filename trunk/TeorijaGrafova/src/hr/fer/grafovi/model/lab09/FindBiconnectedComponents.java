package hr.fer.grafovi.model.lab09;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.BiconnectedComponent;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class FindBiconnectedComponents {
	
	private Graph g;
	private ArrayList<BiconnectedComponent> components;
	private boolean visited[];
	private Stack<Edge> stack;
	private int count;
	private int[] low;
	private int[] p;
	private int[] n;
	
	public FindBiconnectedComponents(Graph g)
	{
		this.g = g;
		components = new ArrayList<BiconnectedComponent>();
		visited = new boolean[g.V()];
		stack = new Stack<Edge>();
		low = new int[g.V()];
		p = new int[g.V()];
		n = new int[g.V()];
		count = 0;
		for (int i = 0; i < g.V(); i++)
		{
			if (!visited[i])
				{
					p[i] = -1;
					dfsR(i);
				}
		}
	}

	private void dfsR(int u) 
	{
		visited[u] = true;
		count++;
		n[u] = count;
		low[u] = count;
		AdjList a = g.getAdjList(u);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int v = e.other(u);
			if (!visited[v])
			{
				stack.push(e);
				p[v] = u;
				dfsR(v);
				low[u] = min(low[u], low[v]);
				if (low[v] >= n[u])
				{
					createBiconnectedComponent(e);
				}
			}
			else if (v != p[u])
			{
				low[u] = min(low[u], n[v]);
				if (n[u] > n[v])
					stack.push(e);
			}
		}
		
	}

	private void createBiconnectedComponent(Edge finalEdge) {
		int vCnt = 0;
		boolean vertices[] = new boolean[g.V()];
		ArrayList<Edge> edges = new ArrayList<Edge>();
		Edge e = null;
		while (!finalEdge.equals(e))
		{
			e = stack.pop();
			edges.add(e);
			if (!vertices[e.getV()])
			{
				vertices[e.getV()] = true;
				vCnt++;
			}
			if (!vertices[e.getW()])
			{
				vertices[e.getW()] = true;
				vCnt++;
			}
		}
		BiconnectedComponent bc = new BiconnectedComponent(vCnt, g.V());
		for (Iterator<Edge> it = edges.iterator(); it.hasNext(); )
			bc.insertOriginalEdge(it.next());
		
		components.add(bc);
	}

	private int min(int i, int j) 
	{
		if (i < j)
			return i;
		else 
			return j;
	}
	
	public BiconnectedComponent getComponent(int i)
	{
		if (i >= components.size())
			throw new IllegalArgumentException("nema toliko komponenti");
		return components.get(i);
	}
	
	public int numberOfComponents()
	{
		return components.size();
	}
	

}
