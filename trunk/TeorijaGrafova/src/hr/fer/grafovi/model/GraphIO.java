package hr.fer.grafovi.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class GraphIO
{
	public static final String ADJECENY_MATRIX = "matrica susjedstva";
	public static final String ADJECENY_LISTS = "lista susjedstva";
	
	public static Graph load(File f) throws IOException
	{
		int v;
		boolean digraph = false;
		boolean weighted = false;
		Graph g;
	
		BufferedReader br = new BufferedReader(new FileReader(f));
		String type = br.readLine();
		String line = br.readLine();
		if (line.equalsIgnoreCase("digraf"))
		{
			digraph = true;
			line = br.readLine();
		}
		if (line.equalsIgnoreCase("tezinski"))
		{
			weighted = true;
			line = br.readLine();
		}
		String[] s = line.split(":");
		if ( ! s[0].equalsIgnoreCase("broj vrhova") )
			throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
		v = Integer.parseInt(s[1]);
		
		if (type.equalsIgnoreCase(ADJECENY_MATRIX))
			g = loadAdjecencyMatrix(br, v, digraph, weighted);
		else if (type.equalsIgnoreCase(ADJECENY_LISTS))
			g = loadAdjecencyLists(br, v, digraph, weighted);
		else
			throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
		return g;
	}

	private static Graph loadAdjecencyLists(BufferedReader br, int brojVrhova, boolean digraph, boolean weighted) throws IOException
	{
		int v,w;
		Graph g = new AdjecencyLists(brojVrhova, weighted, digraph);
		String line;
		String[] splitLine;
		try{
			for (int i = 0; i < brojVrhova; i++)
			{
				line = br.readLine();
				if (line == null)
					throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
				splitLine = line.split("-");
				if (splitLine.length > 2)
					throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
				if (splitLine.length == 1)
					continue;
				
				v = Integer.parseInt(splitLine[0]);
				if (v != i)
					throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
				
				splitLine = splitLine[1].trim().split(" ");
				String[] edge;
				for (int j = 0; j < splitLine.length; j++)
				{
					int weight = 1;
					edge = splitLine[j].split(":");
					w = Integer.parseInt(edge[0]);
					if (!g.directed() && v > w)
						continue;
					if (weighted)
					{
						weight = Integer.parseInt(edge[1]);
						g.insert(new Edge(v ,w, weight));
					} else
						g.insert(new Edge(v, w));
				}
			}
		} catch (NumberFormatException e)
		{
			throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
		}
		return g;
	}

	private static Graph loadAdjecencyMatrix(BufferedReader br, int brojVrhova, boolean digraph, boolean weighted) throws IOException
	{	
		Graph g = new AdjecencyMatrix(brojVrhova, weighted, digraph);
		String line;
		String[] splitLine;
		try{
			for (int v = 0; v < brojVrhova; v++)
			{
				line = br.readLine();
				if (line == null)
					throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
				line = line.replace("  ", " ");
				splitLine = line.trim().split(" ");
				if (splitLine.length != brojVrhova)
					throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
				
				for (int w = 0; w < brojVrhova; w++)
				{
					int x = Integer.parseInt(splitLine[w]);
					if (x != 0)
						if (weighted)
							g.insert(new Edge(v, w, x));
						else
							g.insert(new Edge(v, w));
				}
			}
		} catch (NumberFormatException e)
		{
			throw new IOException("U datoteci se ne nalazi ispravno napisan graf");
		}
		return g;
	}
	
	public static void showAdjecencyMatrix(Graph g, OutputStream os)
	{
		PrintWriter out = new PrintWriter(os);
		int[][] matrix = new int[g.V()][g.V()];
		for (int i = 0; i < g.V(); i++)
		{
			AdjList a = g.getAdjList(i);
			for (Edge j = a.beg(); !a.end(); j = a.nxt())
				matrix[i][j.other(i)] = j.getWeight();
		}
		for (int i = 0; i < g.V(); i++)
		{
			for (int j = 0; j < g.V(); j++)
				out.printf("%3d", matrix[i][j]);
			out.println();
		}
		out.flush();
	}

	public static void showAdjecencyLists(Graph g, OutputStream os)
	{
		PrintWriter out = new PrintWriter(os);
		for (int i = 0; i < g.V(); i++)
		{
			out.print(i + "-");
			AdjList a = g.getAdjList(i);
			for (Edge j = a.beg(); !a.end(); j = a.nxt())
			{
				out.print(" " + j.other(i));
				if (g.weighted())
					out.print(":" + j.getWeight());
			}
			out.println();
		}
		out.flush();
	}
	
	public static void showIncidenceMatrix(Graph g, OutputStream os)
	{
		PrintWriter out = new PrintWriter(os);
		int[][] matrix = new int[g.V()][g.E()];
		int col = 0;
		for (int i = 0; i < g.V(); i++)
		{
			AdjList a = g.getAdjList(i);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				int j = e.other(i);
				if (g.directed() || i <= j)
				{
					if (col != g.E())
					matrix[i][col] = e.getWeight();
					matrix[j][col] = e.getWeight();
					col++;
				}
			}
		}
		for (int i = 0; i < g.V(); i++)
		{
			for (int j = 0; j < g.E(); j++)
				out.printf("%3d", matrix[i][j]);
			out.println();
		}
		out.flush();
		
	}

	public static void save(Graph g, File f, String type) throws IOException
	{
		OutputStream out = new FileOutputStream(f);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		
		bw.write(type);
		bw.newLine();
		if (g.directed())
		{
			bw.write("digraf");
			bw.newLine();
		}
		if(g.weighted())
		{
			bw.write("tezinski");
			bw.newLine();
		}
		bw.write("broj vrhova:" + g.V());
		bw.newLine();
		bw.flush();
		if (type.equals(ADJECENY_MATRIX))
			showAdjecencyMatrix(g, out);
		else if (type.equals(ADJECENY_LISTS))
			showAdjecencyLists(g, out);
		out.close();
	}
}
