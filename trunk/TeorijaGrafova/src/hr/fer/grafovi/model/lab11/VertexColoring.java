package hr.fer.grafovi.model.lab11;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class VertexColoring {

	private final int RED = 1;
	private final int GREEN = 2;
	private final int BLUE = 3;
	private Graph g;
	private int[] vertexColor;
	private boolean is3Colorable;
	
	public VertexColoring(Graph g)
	{
		this.g = g;
		vertexColor = new int[g.V()];
		is3Colorable = true;
		for (int v = 0; v < g.V(); v++)
		{
			if (vertexColor[v] == 0)
			{
				if (!vcR(v))
				{
					is3Colorable = false;
					break;
				}
			}
		}
	}
	
	private boolean vcR(int v)
	{
		boolean colorOK = true;
		for (int color = RED; color <= BLUE; color++)
		{
			if (canColor(v, color) )
			{
				vertexColor[v] = color;
				AdjList a = g.getAdjList(v);
				for (Edge e = a.beg(); !a.end(); e = a.nxt())
				{
					int w = e.other(v);
					if (vertexColor[w] != 0)
						continue; //vec je obojan
					if (!vcR(w))
					{
						colorOK = false;
						break; //nije uspjelo bojanje
					}
				}
				if (colorOK)
					return true; //uspjelo je bojanje 
			}
		}
		return false; //ne moze se obojati niti jednom bojom
	}
	
	private boolean canColor(int v, int color)
	{
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (vertexColor[w] == color)
				return false;
		}
		return true;
	}
	
	public boolean is3Colorable()
	{
		return is3Colorable;
	}
	
	public void printColoring()
	{
		if (!is3Colorable)
			return;
		for (int i = 0; i < g.V(); i++)
			System.out.println(i + " - " + colorString(vertexColor[i]));
	}

	private String colorString(int color) {
		switch(color)
		{
			case RED: return "crvena";
			case GREEN: return "zelena";
			case BLUE: return "plava";
			default: throw new IllegalArgumentException("Pogresna boja");
		}
		
	}
}
