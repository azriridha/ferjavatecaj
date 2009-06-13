package hr.fer.grafovi.model;

public class AdjacencyMatrix implements Graph
{
	private int Vcnt;
	private int Ecnt = 0;
	private final boolean digraph;
	private final boolean weighted;
	private Edge matrix[][];
	
	public AdjacencyMatrix(int vcnt)
	{
		this(vcnt, false, false);
	}
	
	public AdjacencyMatrix(int vcnt, boolean weighted, boolean digraph)
	{
		this.weighted = weighted;
		this.digraph = digraph;
		Vcnt = vcnt;
		matrix = new Edge[vcnt][vcnt];
	}

	@Override
	public int E()
	{
		return Ecnt;
	}

	@Override
	public int V()
	{
		return Vcnt;
	}
	

	@Override
	public boolean directed()
	{
		return digraph;
	}
	
	@Override
	public boolean weighted()
	{
		return weighted;
	}

	@Override
	public Edge edge(int v, int w)
	{
		return matrix[v][w];
	}

	@Override
	public AdjList getAdjList(int v)
	{
		return new AdjArray(v);
	}

	@Override
	public void insert(Edge e)
	{
		int v = e.getV();
		int w = e.getW();
		if (matrix[v][w] == null)
			Ecnt++;
		matrix[v][w] = e;
		if (!digraph)
			matrix[w][v] = e;
	}

	@Override
	public void remove(Edge e)
	{
		int v = e.getV();
		int w = e.getW();
		if (matrix[v][w] != null)
			Ecnt--;
		matrix[v][w] = null;
		if (!digraph)
			matrix[w][v] = null;
	}
	
	private class AdjArray implements AdjList
	{
		private int i,v;
		
		private AdjArray(int v)
		{
			this.v = v;
			i = -1;
		}
		
		@Override
		public Edge beg()
		{
			i = -1;
			return nxt();
		}

		@Override
		public Edge nxt()
		{
			for (i++; i < V(); i++)
				if (edge(v, i) != null)
					return edge(v, i);
			return null;
		}
		
		@Override
		public boolean end()
		{
			return i >= V();
		}
	}
}
