package hr.fer.grafovi.model.lab13;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class TopologicalSort {

	private Graph g;
	private int count;
	private boolean[] visited;
	private int[] post, postInverse;
	
	public TopologicalSort(Graph g)
	{
		this.g = g;
		count = g.V();
		visited = new boolean[g.V()];
		post = new int[g.V()];
		postInverse = new int[g.V()];
		
		for (int i = 0; i < g.V(); i++)
			if (!visited[i])
				tsR(i);
	}

	private void tsR(int v)
	{
		visited[v] = true;
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (!visited[w])
				tsR(w);
		}
		count--;
		postInverse[v] = count;
		post[count] = v;
	}
	
	/**
	 * Metoda koja vraca vrh rednog broja i
	 * @param i redni broj vrha
	 * @return vrh rednog broja i
	 */
	public int order(int i)
	{
		return post[i];
	}
	
	/**
	 * Metoda koja vraca redni broj vrha
	 * @param v indeks vrha
	 * @return redni broj vrha
	 */
	public int getVertexOrder(int v)
	{
		return postInverse[v];
	}
}
