package hr.fer.grafovi.model.lab09;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.AdjacencyLists;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

import java.util.ArrayList;
import java.util.HashSet;

public class Fragment {

	private ArrayList<Edge> edges;
	private boolean[] embeddedVertices;
	private HashSet<Integer> contactVertices;
	private Face admissibleFace = null;
	private int nAdmissibleFaces;
	
	public Fragment(boolean[] embeddedVertices)
	{
		this.embeddedVertices = embeddedVertices;
		contactVertices = new HashSet<Integer>();
		edges = new ArrayList<Edge>();
		nAdmissibleFaces = 0;
	}
	
	public void insert(Edge e)
	{
		edges.add(e);
		int v = e.getV();
		int w = e.getW();
		if (embeddedVertices[v])
			contactVertices.add(v);
		if (embeddedVertices[w])
			contactVertices.add(w);
	}

	public void checkFace(Face face) {
		for (Integer i : contactVertices)
			if (!face.contains(i))
				return;
		if (admissibleFace ==  null)
			admissibleFace = face;
		nAdmissibleFaces++;
	}
	
	public int nAdmissibleFaces()
	{
		return nAdmissibleFaces;
	}
	
	public Face getFace()
	{
		return admissibleFace;
	}
	
	public ArrayList<Integer> getAlphaPath()
	{
		Graph g = new AdjacencyLists(embeddedVertices.length);
		ArrayList<Integer> alphaPath = new ArrayList<Integer>();
		for (Edge e : edges)
			g.insert(e);
		int vertex = -1;
		boolean[] visited = new boolean[g.V()];
		for (int v = 0; v < g.V(); v++)
		{
			AdjList a = g.getAdjList(v);
			Edge e = a.beg();
			if (e == null)
				continue;
			if (contactVertices.contains(v))
			{
				vertex = v;
				alphaPath.add(vertex);
				break;
			}
		}
		/////////////////
		if (vertex == -1)
			System.out.println("greska kod alpha path");
		/////////////////
		do{
			visited[vertex] = true;
			AdjList a = g.getAdjList(vertex);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				int w = e.other(vertex);
				if (!visited[w])
				{
					vertex = w;
					break;
				}
			}
			alphaPath.add(vertex);
		} while (!contactVertices.contains(vertex));
		return alphaPath;
	}
	
	public void print()
	{
		for (Edge e : edges)
			System.out.print(e + " ");
		System.out.println();
	}
}
