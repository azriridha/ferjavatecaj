package hr.fer.grafovi.utilities;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

import java.util.Arrays;
import java.util.Comparator;

public class GraphUtilities
{
	/**
	 * Metoda koja provjerava da li je graf usmjeren i aciklicki (Directed Acyclic Graph). 
	 * @param g graf koji se provjerava
	 * @return vraca true ako je graf usmjeren i aciklicki
	 */
	public static boolean isDAG(Graph g)
	{
		if (!g.directed())
			return false;
		boolean[] startVisit = new boolean[g.V()];
		boolean[] endVisit = new boolean[g.V()];
		for(int i = 0; i < g.V(); i++)
			if (!startVisit[i])
				if (!dfsR(new Edge(i, i), g, startVisit, endVisit))
					return false;
		return true;
	}
	
	/**
	 * Metoda koja provjerava da li je graf potpun
	 * @param g graf koji se provjerava
	 * @return true ako je graf potpun
	 */
	public static boolean isComplete(Graph g)
	{
		for (int v = 0; v < g.V(); v++)
		{
			int degree = 0;
			AdjList a = g.getAdjList(v);
			for (a.beg(); !a.end(); a.nxt())
				degree++;
			if (degree != g.V() - 1)
				return false;
		}
		return true;
	}
	
	/**
	 * Metoda koja se rekurzivno poziva pri provjeri da li usmjereni graf sadrzi barem jedan ciklus.
	 * @param edge brid kojim trenutno prolazimo
	 * @param g graf koji provjeravamo
	 * @param startVisit polje u kojem je zapisano koje smo vrhove poceli obilaziti
	 * @param endVisit polje u kojem je zapisano kod kojih smo vrhova zavrsili s obilaskom
	 * @return
	 */
	private static boolean dfsR(Edge edge, Graph g, boolean[] startVisit, boolean[] endVisit) {
		int v = edge.getW();
		startVisit[v] = true;
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (!startVisit[w])
				if (!dfsR(e, g, startVisit, endVisit))
					return false;
			if (!endVisit[w])
				return false;
		}
		endVisit[v] = true;
		return true;
	}
	
	/**
	 * Metoda koja vraca polje svih bridova u grafu
	 * @param g graf iz kojeg se uzimau bridovi
	 * @return polje bridova
	 */
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
	
	/**
	 * Metoda koja vraca polje svih bridova u grafu, sortiranih po tezini od najmanjeg prema najvecem
	 * @param g graf iz kojeg se uzimau bridovi
	 * @return polje bridova
	 */
	public static Edge[] sortedEdges(Graph g)
	{
		Edge[] edges = edges(g);
		Arrays.sort(edges, new EdgeComparator());
		return edges;
	}
	

	/**
	 * Komparator koji sluzi za sortiranje bridova po tezini. Nije konzistentan sa metodom equals u klasi Edge
	 */
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
