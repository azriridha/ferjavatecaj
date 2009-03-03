package hr.fer.grafovi.model.lab7;

import hr.fer.grafovi.model.AdjList;
import hr.fer.grafovi.model.Edge;
import hr.fer.grafovi.model.Graph;

public class KirchhoffsTheorem
{
	private double numSpanningTrees;

	public KirchhoffsTheorem(Graph g)
	{
		double[][] 	laplacianMatrix = createLaplacianMatrix(g);
		double[][] cofactor = findCofactor(laplacianMatrix);
		numSpanningTrees = findDeterminant(cofactor);
	}

	private double[][] createLaplacianMatrix(Graph g)
	{
		double[][] mat = new double[g.V()][g.V()];
		for (int v = 0; v < g.V(); v++)
		{
			int deg = 0;
			AdjList a = g.getAdjList(v);
			for (Edge e = a.beg(); !a.end(); e = a.nxt())
			{
				deg++;
				mat[v][e.other(v)] = -1;
				mat[e.other(v)][v] = -1;
			}
			mat[v][v] = deg;
		}
		return mat;
	}

	private double[][] findCofactor(double[][] matrix)
	{
		int size = matrix.length - 1;
		double cofactor[][] = new double[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				cofactor[i][j] = matrix[i][j];
		return cofactor;
	}

	private double findDeterminant(double[][] matrix)
	{
		int p = LUPDecomposition(matrix);
		double det = 1;
		for (int i = 0; i < matrix.length; i++)
			det *= matrix[i][i];
		return det * p;
	}

	public int LUPDecomposition(double[][] matrix)
    {
		int size = matrix.length;
		int p = 1;
		for (int i = 0; i < size - 1; i++)
		{
//			for (int j = i+1; j < size; j++)
//			{
//				if (Math.abs(matrix[j][i]) > Math.abs(matrix[i][i]))
//				{
//					switchRows(matrix, i, j);
//					p *= -1;
//				}
//			}
                    if (Math.abs(matrix[i][i]) < 1E-6)
                            throw new IllegalArgumentException("Matrix doesn't admit LUP decomposition");
                    
                    for (int j = i+1; j < size; j++)
                    {
                            matrix[j][i] /= matrix[i][i];
                            for (int k = i+1; k < size; k++)
                            {
                                    matrix[j][k] -= matrix[j][i] * matrix[i][k];
                            }
                    }
            }
            if (Math.abs(matrix[size - 1][size - 1]) < 1E-6)
                    throw new IllegalArgumentException("Matrix doesn't admit LUP decomposition");
            
            return p;
            
    }

	 public void switchRows(double[][] matrix, int row1, int row2)
     {
             double temp;
             for (int i = 0; i < matrix.length; i++)
             {
                     temp = matrix[row1][i];
                     matrix[row1][i] = matrix[row2][i];
                     matrix[row2][i] = temp;
             }
     }


	public double getNumSpanningTrees()
	{
		return numSpanningTrees;
	}
	
	
}
