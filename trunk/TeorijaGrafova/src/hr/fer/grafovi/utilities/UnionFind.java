package hr.fer.grafovi.utilities;

public class UnionFind
{
	private int[] id, size;
	
	public UnionFind(int N)
	{
		id = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++)
		{
			id[i] = i;
			size[i] = 1;
		}
	}
	public boolean find(int p, int q)
	{
		return (find(p) == find(q));
	}
	
	public void unite(int p, int q)
	{
		int i = find(p);
		int j = find(q);
		if (i == j)
			return;
		if (size[i] < size[j])
		{
			id[i] = j;
			size[j] += size[i];
		} else
		{
			id[j] = i;
			size[i] += size[j];
		}
	}
	
	private int find(int x)
	{
		while(x != id[x])
			x = id[x];
		return x;
	}
}