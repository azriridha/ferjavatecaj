package hr.fer.grafovi.model;

public class BiconnectedComponent extends AdjacencyLists {

	private int[] originalVertex;
	private int[] newVertex;
	private int addedVertices;
	
	public BiconnectedComponent(int vcnt, int maxv) 
	{
		super(vcnt);
		originalVertex = new int[vcnt];
		newVertex = new int[maxv];
		addedVertices = 0;
		for (int i = 0; i < maxv; i++)
			newVertex[i] = -1;
	}
	
	public void insertOriginalEdge(Edge e)
	{
		int oldV = e.getV();
		int oldW = e.getW();
		
		if (newVertex[oldV] == -1)
		{
			newVertex[oldV] = addedVertices;
			originalVertex[addedVertices] = oldV;
			addedVertices++;
		}	
		if (newVertex[oldW] == -1)
		{
			newVertex[oldW] = addedVertices;
			originalVertex[addedVertices] = oldW;
			addedVertices++;
		}
		super.insert(new Edge(newVertex[oldV], newVertex[oldW]));
	}
	
	public Edge originalEdge(int v, int w)
	{
		return new Edge(originalVertex[v], originalVertex[w]);
	}
	
	
	

}
