package hr.fer.grafovi.model.lab08;

import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.utilities.GraphUtilities;
import hr.fer.grafovi.utilities.UnionFind;

public class GraphMST
{
	private Edge[] mst;
	private boolean connected = false;
	
	public GraphMST(Graph g)
	{
		int v, w;
		int k = 0;
		UnionFind uf = new UnionFind(g.V());
		Edge[] edges = GraphUtilities.sortedEdges(g);
		mst = new Edge[g.V() - 1];
		
		for (int i = 0; i < g.E() && k < g.V()-1; i++)
		{
			v = edges[i].getV();
			w = edges[i].getW();
			if (!uf.find(v, w))
			{
				uf.unite(v, w);
				mst[k++] = edges[i];
			}
		}
		if (k == g.V() - 1)
			connected = true;
	}
	
	public void showMST()
	{
		if (!connected)
		{
			System.out.println("Graf nije povezan");
			return;
		}
		for (int i = 0; i < mst.length; i++)
			System.out.print(mst[i] + ", ");
		System.out.println();
	}

	
	
	
}
