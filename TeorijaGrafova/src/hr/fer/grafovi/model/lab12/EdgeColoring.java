package hr.fer.grafovi.model.lab12;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.utilities.GraphUtilities;

public class EdgeColoring {

	private final byte RED = 0x01;
	private final byte GREEN = 0x02;
	private final byte BLUE = 0x04;
	private Graph g;
	private byte[] edgeColor;
	private byte[] vertexColors;
	private Edge[] edges;
	private boolean is3Colorable;
	
	public EdgeColoring(Graph g)
	{
		this.g = g;
		if (!checkDegree())
		{
			is3Colorable = false;
			return;
		}
		edgeColor = new byte[g.E()];
		vertexColors = new byte[g.V()];
		edges = GraphUtilities.edges(g);
		is3Colorable = false;
		ecR(0, RED);
	}
	
	private void ecR(int depth, byte color)
	{
		edgeColor[depth] = color;
		int v = edges[depth].getV();
		int w = edges[depth].getW();
		vertexColors[v] |= color;
		vertexColors[w] |= color;
		
		if (depth == g.E() - 1)
		{
			is3Colorable = true;
			return;
		}
		Edge e = edges[depth + 1];
		if (canColor(e, RED))
			ecR(depth + 1, RED);
		if (canColor(e, GREEN) && !is3Colorable)
			ecR(depth + 1, GREEN);
		if (canColor(e, BLUE) && !is3Colorable)
			ecR(depth + 1, BLUE);
	}
	
	private boolean checkDegree()
	{
		for (int v = 0; v < g.V(); v++)
		{
			int degree = 0;
			AdjList a = g.getAdjList(v);
			for (a.beg(); !a.end(); a.nxt())
				degree++;
			if (degree > 3)
				return false;
		}
		return true;
	}
	
	private boolean canColor(Edge e, byte color)
	{
		int v = e.getV();
		int w = e.getW();
		if ((vertexColors[v] & color) != 0)
			return false;
		if ((vertexColors[w] & color) != 0)
			return false;
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
		for (int i = 0; i < g.E(); i++)
			System.out.println(edges[i] + ": " + colorString(edgeColor[i]));
	}

	private String colorString(byte color) {
		switch(color)
		{
			case RED: return "crvena";
			case GREEN: return "zelena";
			case BLUE: return "plava";
			default: throw new IllegalArgumentException("Pogresna boja");
		}
		
	}
}
