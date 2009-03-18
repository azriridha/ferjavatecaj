package hr.fer.grafovi.model.lab13;

import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.utilities.GraphUtilities;

public class CriticalPath {
	
	private LongestPath lp = null;
	private int endTask = 0;
	
	public CriticalPath(Graph g)
	{
		if (!GraphUtilities.isDAG(g))
		{
			System.out.println("Graf nije usmjeren i aciklicki");
			return;
		}
		lp = new LongestPath(g);
		for (int i = 1; i < g.V(); i++)
			if (lp.distance(i) > lp.distance(endTask))
				endTask = i;
	}
	
	public void showCriticalPath()
	{
		if (lp == null)
			return;
		printPath(endTask);
		System.out.println();
	}
	
	private void printPath(int v)
	{
		Edge e = lp.path(v);
		if (e != null)
		{
			printPath(e.other(v));
			System.out.print(e + "[" + lp.distance(v) + "] ");
		}
	}

}
