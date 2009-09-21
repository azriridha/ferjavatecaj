package hr.fer.grafovi.model.lab09;

import java.util.ArrayList;
import java.util.HashSet;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.BiconnectedComponent;
import hr.fer.grafovi.model.Edge;

public class Demoucron {

	private boolean isPlanar;
	private BiconnectedComponent g;
	private boolean embeddedVertices[];
	private ArrayList<Face> faces;
	private ArrayList<Fragment> fragments;
	private ArrayList<Edge> saveEdges;
	
	public Demoucron(BiconnectedComponent g)
	{
		this.g = g;
		embeddedVertices = new boolean[g.V()];
		faces = new ArrayList<Face>();
		saveEdges = new ArrayList<Edge>(g.E());
	}
	
	public void run()
	{
		chooseCycle();
		while(true)
		{
			System.out.println("ispis lica");
			for (Face f : faces)
				f.print();
			computeFragments();
			if (fragments.isEmpty())
			{
				isPlanar = true;
				System.out.println("nema vise fragmenata");
				break;
			}
			computeAdmissibleFaces();
			
			Fragment chosenFragment = chooseFragment();
			if (chosenFragment == null)
			{
				isPlanar = false;
				break;
			}
			ArrayList<Integer> alphaPath = chosenFragment.getAlphaPath();
			System.out.println("alpha path: " + alphaPath);

			faces.add(chosenFragment.getFace().createFace(alphaPath));
			for (int i = 0; i < alphaPath.size() - 1; i++)
			{
				removeEdge(new Edge(alphaPath.get(i), alphaPath.get(i+1)));
				embeddedVertices[alphaPath.get(i)] = true;
			}
		}

		
		
		returnEdges();
	}

	private Fragment chooseFragment() {
		for (Fragment fragment : fragments)
		{
			if (fragment.nAdmissibleFaces() == 0)
				return null;
			if (fragment.nAdmissibleFaces() == 1)
				return fragment;
		}
		return fragments.get(0);
	}

	private void computeAdmissibleFaces() 
	{
		for (Fragment fragment : fragments)
			for (Face face : faces)
				fragment.checkFace(face);
	}

	private void computeFragments()
	{
		boolean[] inFragment = new boolean[embeddedVertices.length];
		System.arraycopy(embeddedVertices, 0, inFragment, 0, inFragment.length);
		fragments = new ArrayList<Fragment>();
		for (int v = 0; v < inFragment.length; v++)
		{
			if (!inFragment[v])
			{
				Fragment fragment = new Fragment(embeddedVertices);
				makeFragment(fragment, v, inFragment);
				fragments.add(fragment);
			}
		}
		HashSet<Edge> visited = new HashSet<Edge>();
		for (int v = 0; v < embeddedVertices.length; v++)
		{
			if (embeddedVertices[v])
			{
				AdjList a = g.getAdjList(v);
				for (Edge e = a.beg(); !a.end(); e = a.nxt())
				{
					if (embeddedVertices[e.other(v)] && !visited.contains(e))
					{
						Fragment f = new Fragment(embeddedVertices);
						f.insert(e);
						fragments.add(f);
						visited.add(e);
					}
				}
			}
		}
		//////////////////
		int k = 0;
		for (Fragment f : fragments)
		{
			System.out.print("fragment " + k++ + ":");
			f.print();
		}
		//////////////////s
	}

	private void makeFragment(Fragment fragment, int v, boolean[] inFragment) {
		inFragment[v] = true;
		AdjList a = g.getAdjList(v);
		for (Edge e = a.beg(); !a.end(); e = a.nxt())
		{
			int w = e.other(v);
			if (!inFragment[w] || embeddedVertices[w] )
				fragment.insert(e);
			if (!inFragment[w])
				makeFragment(fragment, w, inFragment);
		}
		
		
	}

	private void chooseCycle() 
	{
		ArrayList<Integer> path = new ArrayList<Integer>();
		boolean visited[] = new boolean[g.V()];
		int u = 0;
		int previous = 0;
		while(!visited[u])
		{
			path.add(u);
			visited[u] = true;
			AdjList a = g.getAdjList(u);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				if (e.other(u) != previous)
				{
					previous = u;
					u = e.other(u);
					break;
				}
			}
		}
		previous = u;
		Face face = new Face();
		for (int i = path.size() - 1; i > 0; i--)
		{
			int v = path.get(i);
			face.insert(v);
			embeddedVertices[v] = true;
			removeEdge(new Edge(v, previous));
			previous = v;
			if (v == u)
				break;
		}
		faces.add(face);
		faces.add(face.copy());		
	}

	private void removeEdge(Edge e) {
		g.remove(e);
		saveEdges.add(e);
	}

	private void returnEdges() {
		for(Edge e : saveEdges)
			g.insert(e);	
	}

	
	public boolean isPlanar() {
		return isPlanar;
	}

}
