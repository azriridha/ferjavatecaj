package hr.fer.grafovi.controller;

import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.WrongGraphException;
import hr.fer.grafovi.model.lab04.TravellingSalesmanExact;
import hr.fer.grafovi.model.lab04.TravellingSalesmanHeuristic;
import hr.fer.grafovi.model.lab05.Dijkstra;
import hr.fer.grafovi.model.lab06.LongestClosedTrail;
import hr.fer.grafovi.model.lab06.ShortestCycle;
import hr.fer.grafovi.model.lab07.KirchhoffsTheorem;
import hr.fer.grafovi.model.lab07.SpanningTree;
import hr.fer.grafovi.model.lab08.GraphMST;
import hr.fer.grafovi.model.lab09.Demoucron;
import hr.fer.grafovi.model.lab11.VertexColoring;
import hr.fer.grafovi.model.lab12.EdgeColoring;
import hr.fer.grafovi.model.lab13.CriticalPath;
import hr.fer.grafovi.model.lab14.MarriageProblem;

public class LabsController
{

	public void startTravelingSalesman()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;

		try {
			TravellingSalesmanExact tse = new TravellingSalesmanExact(g);
			System.out.println("Najkraci put (iscrpna pretraga):");
			tse.printShortestCycle();
			System.out.println("Duljina najkraceg puta: " + tse.getMinLength());
			System.out.print("proteklo vrijeme: ");
			Stopwatch.printElapsedTime();
			
			Stopwatch.start();
			TravellingSalesmanHeuristic tsh = new TravellingSalesmanHeuristic(g);
			System.out.println("Najkraci put (heuristika):");
			tsh.printShortestCycle();
			System.out.println("Duljina najkraceg puta: " + tsh.getMinLength());
		} catch (WrongGraphException e) {
			System.out.println(e.getMessage());
		} 
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
		
	}
	
	public void startDijkstra(int source, int sink)
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		Dijkstra d = new Dijkstra(g, sink);
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
		
		d.printLongPath(source);
	}
	
	public void startKruskal()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		GraphMST mst = new GraphMST(g);
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
		
		mst.showMST();
	}
	
	public void countSpanningTrees()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		KirchhoffsTheorem kt;
		try
		{
			kt = new KirchhoffsTheorem(g);
		} catch (IllegalArgumentException e)
		{
			System.out.println("Graf nije povezan");
			return;
		} finally
		{
			System.out.print("proteklo vrijeme: ");
			Stopwatch.printElapsedTime();
		}
				
		System.out.println("Broj razapinjucih stabala: " + Math.round(kt.getNumSpanningTrees()));
		System.out.println("Broj razapinjucih stabala: " + kt.getNumSpanningTrees());
		SpanningTree st = new SpanningTree(g);
		st.showST();
	}
	
	public void findCriticalPath()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		CriticalPath cp;
		try {
			cp = new CriticalPath(g);
			System.out.print("Kriticni put... ");
			cp.showCriticalPath();
		} catch (WrongGraphException e) {
			System.out.println(e.getMessage());
		}
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
	}
	
	public void startMatching()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		try {
			MarriageProblem mp = new MarriageProblem(g);
			System.out.println("Broj potpunih sparivanja: " + mp.getNumberOfCompleteMatchings());
		} catch (WrongGraphException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
	}
	
	public void findCycles()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		try {
			System.out.println("Najkraca zatvorena staza:");
			ShortestCycle sc = new ShortestCycle(g);
			sc.printShortestCycle();
			
			System.out.print("proteklo vrijeme: ");
			Stopwatch.printElapsedTime();
			Stopwatch.start();
			
			System.out.println("Najdulja zatvorena staza:");
			LongestClosedTrail lct = new LongestClosedTrail(g);
			lct.printLongestTrail();
		} catch (WrongGraphException e) {
			System.out.println(e.getMessage());
		}
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
	}
	
	public void colorVertices()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		VertexColoring vc = new VertexColoring(g);
		if (vc.is3Colorable())
		{
			System.out.println("Graf je 3-obojiv");
			vc.printColoring();
		}
		else
			System.out.println("Graf nije 3-obojiv");
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
		
	}
	
	public void colorEdges()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		EdgeColoring ec = new EdgeColoring(g);
		if (ec.is3Colorable())
		{
			System.out.println("Graf je bridno 3-obojiv");
			ec.printColoring();
		}
		else
			System.out.println("Graf nije bridno 3-obojiv");
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();	
	}
	
	public void planarityTest()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		if (g == null)
			return;
		
		Demoucron d = new Demoucron(g);
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();	
	}

	private static class Stopwatch
	{
		private static long start = 0;
		private static long elapsed;
		
		public static void start()
		{
			start = System.currentTimeMillis();
		}
		
		public static void printElapsedTime()
		{
			elapsed = System.currentTimeMillis() - start;
			System.out.println(elapsed / 1000 + "s " + elapsed % 1000 + "ms");
		}
		
	}
}
