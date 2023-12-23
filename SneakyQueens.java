// Matthew Hernandez
// UCFID:5357015
// COP 3503, Spring 2023

import java.util.*;

public class SneakyQueens
{
	public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)	
	{
		// declaring the arrays that I will be using 
		Boolean[] columns = new Boolean[boardSize+1];
		Boolean[] rows = new Boolean[boardSize+1];
		Boolean[] diaPos = new Boolean[2*(boardSize)+1];
		Boolean[] diaNeg = new Boolean[2*(boardSize)+1];
		// making them all True to avoid nullPointer Exception
		Arrays.fill(columns, Boolean.TRUE);
		Arrays.fill(rows, Boolean.TRUE);
		Arrays.fill(diaPos, Boolean.TRUE);
		Arrays.fill(diaNeg, Boolean.TRUE);

		// for loop to check if the queens(strings) are open for attack or interception 
		for (int i = 0; i < coordinateStrings.size(); i++)
		{
			String str = coordinateStrings.get(i);
			int toDemical = 0;
			int colSum = 0;
			int rowSum = 0;
			char c = 'a';
			// processing the characters of the string in one go, while utilizing Horner's Rule 
			for (int k = 0; k < str.length(); k++)
			{
				c = str.charAt(k);

				// checks the letters of the string to convert into an integer with base 26
				if ('a' <= c && c <= 'z')
				{
					toDemical = c - 96;
					colSum = colSum * 26;
					colSum = colSum + toDemical;
				}
				// checks the numbers of the string to convert into an integer and add it all up
				else if ('0' <= c && c <= '9')
				{
					toDemical = c - 48;
					rowSum = rowSum * 10;
					rowSum = rowSum + toDemical;
				}
			}
			// checking if the rows or columns for the current Queen is dangerous
			if (columns[colSum] == false || rows[rowSum] == false)
			{
				return false;
			}
			// checking if the positive or negative diagonals are dangerous for the current Queen
			if (diaPos[boardSize+colSum-rowSum] == false || diaNeg[colSum+rowSum-1] == false)
			{
				return false;
			}
			// flagging the column,row and diagonals as dangerous
			diaPos[boardSize+colSum-rowSum] = false;
			diaNeg[colSum+rowSum-1] = false;
			columns[colSum] = false;
			rows[rowSum] = false;	
		}
		return true;
	}
	public static double difficultyRating()
	{
		return 4.0;
	}
	public static double hoursSpent()
	{
		return 15.0;
	}

}