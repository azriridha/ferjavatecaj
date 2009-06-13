package hr.fer.grafovi.model.lab09;

import hr.fer.grafovi.model.BiconnectedComponent;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.utilities.GraphUtilities;

public class Demoucron {

	public Demoucron(Graph g) {
		FindBiconnectedComponents components = new FindBiconnectedComponents(g);
		for (int i = 0; i < components.numberOfComponents(); i++)
		{
			BiconnectedComponent bc = components.getComponent(i);
			System.out.println(i + ". ->");
			Edge[] edges = GraphUtilities.edges(bc);
			for (int j = 0; j < edges.length; j++)
			{
				System.out.print(bc.originalEdge(edges[j].getV(), edges[j].getW()) + " ");
			}
			System.out.println();
		}
	}

}
