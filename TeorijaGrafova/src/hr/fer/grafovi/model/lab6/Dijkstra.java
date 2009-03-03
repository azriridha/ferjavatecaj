package hr.fer.grafovi.model.lab6;

import java.util.TreeSet;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class Dijkstra
{
	private Vertex[] vertex;
	private Edge[] longPathTree;
	private int sink;
	private TreeSet<Vertex> queue;
	public Dijkstra(Graph g, int sink)
	{
		this.sink = sink;
		int V = g.V();
		queue = new TreeSet<Vertex>();
		vertex = new Vertex[V];
		longPathTree = new Edge[V];
		for(int i = 0; i < V; i++)
		{
			vertex[i] = new Vertex(i, 0);
			queue.add(vertex[i]);
		}
		reSort(vertex[sink], 1);
		while(!queue.isEmpty())
		{
			Vertex v = queue.pollLast();
			if (v.getV() != sink && longPathTree[v.getV()] == null)
				return;
			AdjList a = g.getAdjList(v.getV());
			for(Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				int w = e.other(v.getV());
				if (!queue.contains(vertex[w]))
					continue;
				int newWeight = vertex[v.getV()].getWeight() + e.getWeight();
				if (newWeight > vertex[w].getWeight())
				{
					reSort(vertex[w], newWeight);
					longPathTree[w] = e;
				}
			}
		}
	}
	
	private void reSort(Vertex v, int value)
	{
		queue.remove(v);
		v.setWeight(value);
		queue.add(v);
	}
	
	public void printLongPath(int source)
	{
		if (longPathTree[source] == null)
		{
			System.out.println("ne postoji put od vrha " + source + " do vrha " + sink);
			return;
		}
		int v = source;
		while(v != sink)
		{
			System.out.print(longPathTree[v] + " ");
			v = longPathTree[v].other(v);
		}
	}
	
}
