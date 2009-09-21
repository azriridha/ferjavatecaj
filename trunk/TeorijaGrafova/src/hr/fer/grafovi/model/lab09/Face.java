package hr.fer.grafovi.model.lab09;

import java.util.ArrayList;

public class Face {

	private ArrayList<Integer> vertices;
	
	public Face()
	{
		vertices = new ArrayList<Integer>();
	}
	
	public void insert(int v)
	{
		vertices.add(v);
	}
	
	public Face copy()
	{
		Face copy = new Face();
		for (Integer v : vertices)
			copy.insert(v);
		return copy;
	}

	public boolean contains(Integer v) {
		return vertices.contains(v);
	}
	
	public void print()
	{
		int i;
		for (i = 0; i < vertices.size(); i++)
			System.out.print(vertices.get(i) + "-");
		System.out.println(vertices.get(0));
	}
	
	public Face createFace(ArrayList<Integer> alphaPath)
	{
		Face newFace = new Face();
		int first = -1;
		int last = -1;
		int iii;
		for(iii = 0; iii < vertices.size(); iii++)
		{
			if (alphaPath.get(0) == vertices.get(iii))
				first = iii;
			if (alphaPath.get(alphaPath.size()-1) == vertices.get(iii))
				last = iii;
		}

		if (first == -1 || last == -1 || first == last)
			throw new IllegalArgumentException("Pogresan alphaPath u funkciji createFace");
		
		if (first < last)
		{
			for (int i = first + 1; i < last; i++)
			{
				newFace.insert(vertices.get(first + 1));
				vertices.remove(first);
			}
			vertices.remove(first);
			vertices.remove(first);
			int spot = newFace.vertices.size();
			for (int i = 0; i < alphaPath.size(); i++)
			{
				newFace.vertices.add(spot, alphaPath.get(i));
				vertices.add(first + i, alphaPath.get(i));
			}
		} else
		{
			for (int i = last + 1; i < first; i++)
			{
				newFace.insert(vertices.get(last + 1));
				vertices.remove(last);
			}
			vertices.remove(last);
			vertices.remove(last);
			for (int i = 0; i < alphaPath.size(); i++)
			{
				newFace.insert(alphaPath.get(i));
				vertices.add(last, alphaPath.get(i));
			}
		}
		return newFace;
	}
}
