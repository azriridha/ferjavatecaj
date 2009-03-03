package hr.fer.grafovi.controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import hr.fer.grafovi.model.*;
import hr.fer.grafovi.utilities.Constants;
import hr.fer.grafovi.view.GraphView;
import hr.fer.grafovi.view.MainView;

/**
 * @author hrvoje
 * 
 */
public class MainController implements Constants
{
	public static final MainController ctrl = new MainController();
	public static final LabsController labsCtrl = new LabsController();
	private Graph g = null;
	private MainView view;
	private GraphView graphPanel;
	private OutputStream textAreaOutput;

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	private MainController()
	{
		super();
	}
	
	public Graph getGraph()
	{
		return g;
	}
	
	public boolean weightedGraph()
	{
		if (g == null)
			return false;
		return g.weighted();
	}
	
	public void addView(MainView view)
	{
		this.view = view;
		graphPanel = view.getGraphView();
		textAreaOutput = graphPanel.getOutputStream();
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void open(File f)
	{
		try
		{
			g = GraphIO.load(f);
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(view, e.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
		updateView();
	}

	public void save(File f, String type)
	{
		if (g == null)
			return;
		try
		{
			GraphIO.save(g, f, type);
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(view, e.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createGraph(int type, int[] args)
	{
		boolean digraph = false;
		boolean weighted = false;
		
		if(args[0] != 0)
			digraph = true;
		if(args[1] != 0)
			weighted = true;
		
		switch (type) {
		case COMPLETE:
			g = GraphFactory.createCompleteGraph(args[2], digraph, weighted);
			break;
		case WHEEL:
			g = GraphFactory.createWheelGraph(args[2], digraph, weighted);
			break;
		case CUBE:
			g = GraphFactory.createCubeGraph(args[2], digraph, weighted);
			break;
		case CYCLE:
			g = GraphFactory.createCycleGraph(args[2], digraph, weighted);
			break;
		case BIPARTITE:
			g = GraphFactory.createCompleteBipartiteGraph(args[2], args[3], digraph, weighted);
			break;
		case RANDOM:
			g = GraphFactory.createRandomGraph(args[2], args[3], digraph, weighted);
			break;
		default:
			return;
		}
		updateView();
	}
	
	public void convertTo(int type)
	{
		if (g == null)
			return;
		Graph temp;
		switch (type) {
		case MATRICA_SUSJEDSTVA:
			temp = new AdjecencyMatrix(g.V(), g.weighted(), g.directed());
			break;
		case LISTA_SUSJEDSTVA:
			temp = new AdjecencyLists(g.V(), g.weighted(), g.directed());
			break;
		default:
			return;
		}
		for (int i = 0; i < temp.V(); i++)
		{
			AdjList a = g.getAdjList(i);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				if (!temp.directed() && i > e.other(i))
					continue;
				temp.insert(e);
			}
		}
		g = temp;
		updateView();
	}
	
	public void clearInfo()
	{
		propertyChangeSupport.firePropertyChange("info", null, null);
	}

	public void updateView()
	{
		if (g == null)
			return;
		propertyChangeSupport.firePropertyChange("vertices", null, g.V()-1);
		if (g instanceof AdjecencyMatrix)
			propertyChangeSupport.firePropertyChange("graph", null, MATRICA_SUSJEDSTVA);
		else if (g instanceof AdjecencyLists)
			propertyChangeSupport.firePropertyChange("graph", null, LISTA_SUSJEDSTVA);
		int type = graphPanel.getSelected();
		show(type);
	}
	
	private void show(int type)
	{
		if (g == null)
			return;
		graphPanel.clear();
		if (g.V() > 1000)
			return;
		switch (type) {
		case GraphView.LISTA_SUSJEDSTVA:
			GraphIO.showAdjecencyLists(g, textAreaOutput);
			break;
		case GraphView.MATRICA_SUSJEDSTVA:
			GraphIO.showAdjecencyMatrix(g, textAreaOutput);
			break;
		case GraphView.MATRICA_INCIDENCIJE:
			if (g.E() > 1000)
				return;
			GraphIO.showIncidenceMatrix(g, textAreaOutput);
			break;
		}
	}
	
	public void addVertex(int numberOfVertices)
	{
		Graph temp;
		if (g instanceof AdjecencyLists)
			temp = new AdjecencyLists(g.V()+numberOfVertices, g.weighted(), g.directed());
		else if(g instanceof AdjecencyMatrix)
			temp = new AdjecencyMatrix(g.V()+numberOfVertices, g.weighted(), g.directed());
		else 
			return;
		for (int i = 0; i < g.V(); i++)
		{
			AdjList a = g.getAdjList(i);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				if (!temp.directed() && i > e.other(i))
					continue;
				temp.insert(e);
			}
		}
		g = temp;
		updateView();
	}
	
	public void removeVertex(int index)
	{
		if (g == null)
			return;
		if (index >= g.V())
			return;
		Graph temp;
		if (g instanceof AdjecencyLists)
			temp = new AdjecencyLists(g.V()-1, g.weighted(), g.directed());
		else if(g instanceof AdjecencyMatrix)
			temp = new AdjecencyMatrix(g.V()-1, g.weighted(), g.directed());
		else 
			return;
		
		int v;
		int w;
		for (int i = 0; i < g.V(); i++)
		{
			if (i == index)
				continue;
			v = i;
			if (v > index)
				v--;
			AdjList a = g.getAdjList(i);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				w = e.other(i);
				if (w == index)
					continue;
				if (w > index)
					w--;
				if (!temp.directed() && v > w)
					continue;
				if (temp.weighted())
					temp.insert(new Edge(v, w, e.getWeight()));
				else
					temp.insert(new Edge(v, w));
			}
		}
		g = temp;
		updateView();
	}
	
	public void addEdge(int v, int w, int value)
	{
		if (g == null || v == w || v>=g.V() || w >= g.V())
			return;
		if (g.edge(v, w) == null)
			if (g.weighted())
				g.insert(new Edge(v, w, value));
			else
				g.insert(new Edge(v, w));
		updateView();
	}
	
	public void removeEdge(int v, int w)
	{
		if (g == null)
			return;
		g.remove(new Edge(v, w));
		updateView();
	}
}
