package hr.fer.grafovi.model.lab14;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.WrongGraphException;

public class WomenSort 
{
	
	private final int F = 1;
	private final int M = 2;
	private Graph g;
	private int[] post;
	private int[] vertexSex;
	private int[] degree;
	private int numberOfWomen;
	
	public WomenSort(Graph g) throws WrongGraphException
	{
		if (g.directed())
			throw new WrongGraphException("Graf ne smije biti usmjeren");
		this.g = g;
		vertexSex = new int[g.V()];
		findWomen();
		sortWomen();
	}

	private void findWomen() throws WrongGraphException
	{
		for (int v = g.V() - 1; v >= 0; v--)
		{
			if (vertexSex[v] == F)
			{
				numberOfWomen = v + 1;
				break;
			}
			vertexSex[v] = M;
			AdjList a = g.getAdjList(v);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				int w = e.other(v);
				if (vertexSex[w] == M)
					throw new WrongGraphException("Graf nije pravilno zadani bipartitni graf");
				vertexSex[w] = F;
			}
		}
		
		degree = new int[numberOfWomen];
		
		for (int v = 0; v < numberOfWomen; v++)
		{
			vertexSex[v] = F;
			AdjList a = g.getAdjList(v);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				degree[v]++;
				int w = e.other(v);
				if (vertexSex[w] != M)
					throw new WrongGraphException("Graf nije pravilno zadani bipartitni graf");
			}
		}
	}

	

	private void sortWomen() {
		post = new int[numberOfWomen];
		for (int i = 0; i < numberOfWomen; i++)
			post[i] = i;
		for (int i = 0; i < numberOfWomen - 1; i++)
		{
			for(int j = i + 1; j < numberOfWomen; j++)
			{
				if (degree[post[i]] > degree[post[j]])
				{
					int temp = post[i];
					post[i] = post[j];
					post[j] = temp;
				}
			}
		}
	}
	
	public int getNumberOfWomen()
	{
		return numberOfWomen;
	}
	
	public int getOrderedWoman(int i)
	{
		return post[i];
	}

}
