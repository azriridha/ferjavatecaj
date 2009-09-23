package hr.fer.grafovi.model.lab04;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.WrongGraphException;

public class LongestClosedTrail {

	private Graph g;
	private Edge[] longestTrail;
	private Edge[] trail;
	private int start;
	private int length = 0;
	
	public LongestClosedTrail(Graph g) throws WrongGraphException
	{
		if (g.directed())
			throw new WrongGraphException("Graf ne smije biti usmjeren");
		this.g = g;
		trail = new Edge[g.E()];
		longestTrail = new Edge[g.E()];
		for (int i = 0; i < g.V(); i++)
		{
			start = i;
			lctR(0, i);
		}
	}

	private void lctR(int depth, int v) 
	{
		if (v == start && depth > length)
		{
			length = depth;
			System.arraycopy(trail, 0, longestTrail, 0, length);
		}
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			trail[depth] = e;
			g.remove(e);
			lctR(depth + 1, e.other(v));
			g.insert(e);
		}
	}
	
	public void printLongestTrail()
	{
		if (length == 0)
		{
			System.out.println("graf nema ciklusa");
			return;
		}	
		for (int i = 0; i < length; i++)
			System.out.print(longestTrail[i] + " ");
		System.out.println();
	}
}
