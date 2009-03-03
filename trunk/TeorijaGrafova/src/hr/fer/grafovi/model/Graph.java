package hr.fer.grafovi.model;

public interface Graph
{
	int V();
	int E();
	boolean directed();
	boolean weighted();
	void insert(Edge e);
	void remove(Edge e);
	Edge edge(int v, int w);
	AdjList getAdjList(int v);
}
