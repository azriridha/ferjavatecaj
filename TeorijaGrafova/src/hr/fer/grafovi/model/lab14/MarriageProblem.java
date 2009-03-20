package hr.fer.grafovi.model.lab14;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class MarriageProblem {
	
	private Graph g;
	private WomenSort ws;
	private int numberOfWomen;
	private int numberOfCompleteMatchings = 0;
	private boolean[] taken;
	private int[] matching;
	
	public MarriageProblem(Graph g)
	{
		this.g = g;
		ws = new WomenSort(g);
		numberOfWomen = ws.getNumberOfWomen();
		matching = new int[numberOfWomen];
		taken = new boolean[g.V()];
		mpR(0);
	}

	public int getNumberOfCompleteMatchings()
	{
		return numberOfCompleteMatchings;
	}
	private void mpR(int depth) 
	{
		if (depth == numberOfWomen)
		{
			numberOfCompleteMatchings++;
			printMatching();
			return;
		}
		int v = ws.getOrderedWoman(depth);
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (taken[w])
				continue;
			taken[w] = true;
			matching[v] = w;
			mpR(depth + 1);
			taken[w] = false;
		}
		
	}

	private void printMatching() 
	{
		for (int i = 0; i < numberOfWomen; i++)
			System.out.print(i + "-" + matching[i] + " ");
			
		System.out.println();	
	}


}
