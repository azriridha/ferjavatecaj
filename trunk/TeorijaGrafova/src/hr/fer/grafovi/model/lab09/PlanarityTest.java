package hr.fer.grafovi.model.lab09;

import hr.fer.grafovi.model.BiconnectedComponent;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.utilities.GraphUtilities;

public class PlanarityTest {

	private boolean isPlanar;
	
	public PlanarityTest(Graph g) {
		FindBiconnectedComponents components = new FindBiconnectedComponents(g);
		for (int i = 0; i < components.numberOfComponents(); i++)
		{
			BiconnectedComponent bc = components.getComponent(i);
			System.out.print((i+1) + ". komponenta ->");
			Edge[] edges = GraphUtilities.edges(bc);
			for (int j = 0; j < edges.length; j++)
			{
				System.out.print(edges[j] + "(" + bc.originalEdge(edges[j].getV(), edges[j].getW()) + ") ");
			}
			System.out.println();
			if (!checkPlanarity(bc))
			{
				isPlanar = false;
				return;
			}
		}
		isPlanar = true;
	}
	
	private boolean checkPlanarity(BiconnectedComponent bc) 
	{
		if (bc.V() < 3)
		{
			System.out.println("OK! (v < 3)");
			return true;
		}
		if (bc.E() > 3*bc.V() - 6)
		{
			System.out.println("Komponenta nije planarna (e > 3v - 6)");
			return false;
		}
		Demoucron d = new Demoucron(bc);
		d.run();
		return d.isPlanar();
	}

	public boolean isPlanar()
	{
		return isPlanar;
	}

}
