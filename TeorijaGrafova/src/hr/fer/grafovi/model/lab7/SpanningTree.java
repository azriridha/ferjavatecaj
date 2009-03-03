package hr.fer.grafovi.model.lab7;

import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.utilities.GraphUtilities;
import hr.fer.grafovi.utilities.UnionFind;

public class SpanningTree
{
	private Edge[] st;
	
	public SpanningTree(Graph g)
	{
		int v,w;
		int k = 0;
		st = new Edge[g.V() - 1];
		Edge[] edges = GraphUtilities.edges(g);
		UnionFind uf = new UnionFind(g.V());
		for (int i = 0; i < edges.length; i++)
		{
			v = edges[i].getV();
			w = edges[i].getW();
			if (!uf.find(v, w))
			{
				uf.unite(v, w);
				st[k++] = edges[i];
				if (k == st.length)
					break;
			}
		}
		if (k != st.length)
			st = null;
	}
	
	public void showST()
	{
		if (st == null)
		{
			System.out.println("Graf nije povezan");
			return;
		}
		for (int i = 0; i < st.length; i++)
			System.out.print(st[i] + " ");
		System.out.println();
	}
}
