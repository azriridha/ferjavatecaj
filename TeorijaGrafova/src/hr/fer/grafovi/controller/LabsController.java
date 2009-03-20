package hr.fer.grafovi.controller;

import hr.fer.grafovi.model.Graph;
import hr.fer.grafovi.model.lab05.Dijkstra;
import hr.fer.grafovi.model.lab07.KirchhoffsTheorem;
import hr.fer.grafovi.model.lab07.SpanningTree;
import hr.fer.grafovi.model.lab08.GraphMST;
import hr.fer.grafovi.model.lab13.CriticalPath;
import hr.fer.grafovi.model.lab14.MarriageProblem;

public class LabsController
{

	public void startDijkstra(int source, int sink)
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
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
		
		CriticalPath cp = new CriticalPath(g);
		System.out.print("Kriticni put... ");
		cp.showCriticalPath();
		
		System.out.print("proteklo vrijeme: ");
		Stopwatch.printElapsedTime();
	}
	
	public void startMatching()
	{
		System.out.println("start");
		Stopwatch.start();
		
		Graph g = MainController.ctrl.getGraph();
		
		try {
			MarriageProblem mp = new MarriageProblem(g);
			System.out.println("Broj potpunih sparivanja: " + mp.getNumberOfCompleteMatchings());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
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
