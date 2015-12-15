// Kaylene Stocking
// Basic sudoku solver using brute force algorithm

import java.util.*;
import java.io.*;

public class SudokuSolver
{
	public static int[][] initial = 
	{
		{0, 0, 6, 0, 0, 8, 5, 0, 0},
		{0, 0, 0, 0, 7, 0, 6, 1, 3},
		{0, 0, 0, 0, 0, 0, 0, 0, 9},
		{0, 0, 0, 0, 9, 0, 0, 0, 1},
		{0, 0, 1, 0, 0, 0, 8, 0, 0},
		{4, 0, 0, 5, 3, 0, 0, 0, 0},
		{1, 0, 7, 0, 5, 3, 0, 0, 0},
		{0, 5, 0, 0, 6, 4, 0, 0, 0},
		{3, 0, 0, 1, 0, 0, 0, 6, 0}
	};
	
	public static void main(String[] args)
	{
		boolean[][] unmutable = new boolean[9][9];
		int[][] grid = new int[9][9];
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				grid[i][j] = initial[i][j];
				if (initial[i][j] != 0)
					unmutable[i][j] = true;
			}
		}
		
		
		int col = 0;
		int row = 0;
		
		while (unmutable[row][col] == true)
		{
			if (col < 8)
				col++;
			else
			{
				col = 0;
				row++;
			}
		}
		
		while (row < 9)
		{
			while (col < 9)
			{
				if (grid[row][col] < 9)
				{
					grid[row][col]++;
					boolean valid = test(grid, row, col);
					if (valid)
					{
						do
						{
							if (row == 8 && col == 8)
							{
								printSolution(grid);
								return;
							}
							
							if (col < 8)
								col++;
							else
							{
								col = 0;
								row++;
							}
							// System.out.printf("row: %d, col: %d\n", row, col);
							
						} while (unmutable[row][col] == true);
					}
				}
				else
				{
					if (row == 0 && col == 0)
					{
						System.out.println("This puzzle doesn't have a valid solution!");
						System.exit(1);
					}
					else	
					{
						grid[row][col] = 0;
						do
						{
							if (col > 0)
								col--;
							else
							{
								col = 8;
								row--;
							}
						} while (unmutable[row][col] == true);
					}						
				}
			}
			
		}
		
	}
	
	public static boolean test(int[][] grid, int row, int col)
	{
		int num = grid[row][col];
		
		// Check the row for the same number
		for (int i = 0; i < 9; i++)
		{
			if (grid[row][i] == num && i != col)
				return false;
		}
		
		// Check the column for the same number
		for (int j = 0; j < 9; j++)
		{
			if (grid[j][col] == num && j != row)
				return false;
		}
		
		// Check the 3x3 box for the same number
		int istart, jstart;
		if (row < 3)
			istart = 0;
		else if (row < 6)
			istart = 3;
		else 
			istart = 6;
		
		if (col < 3)
			jstart = 0;
		else if (col < 6)
			jstart = 3;
		else
			jstart = 6;
		
		for (int i = istart; i < istart + 3; i++)
		{
			for (int j = jstart; j < jstart + 3; j++)
			{
				if (grid[i][j] == num && (i != row || j != col))
				return false;
			}
		}
		
		// If none of the checks find the same number, the test is passed
		return true;

	}
	
	public static void printSolution(int[][] sol)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				System.out.printf("%d  ", sol[i][j]);
			}
			System.out.println("");
		}
	}
}