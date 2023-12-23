// Matthew Hernandez
// NID: ma040619
// COP 3503, Spring 2023

import java.util.*;
import java.io.*;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class SneakyKnights
{
	
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		// base case for boardSize so it can't be 0 or greater than the integer max value
		if (boardSize <= 0 || boardSize > Integer.MAX_VALUE) 
		{
			return false;
		}
		// this is also a base case to ensure that values don't go over out of bounds
		if (boardSize != Integer.MAX_VALUE)
		{
			boardSize=boardSize+1;
		}
		// creation of hashset to store points 
		HashSet<Point> positions = new HashSet<>();

		// main loop to check if the knights(strings) are open for attack/interception
		for (String str : coordinateStrings)
		{
			int toDemical = 0;
			int colSum = 0;
			int rowSum = 0;
			char c = 'a';

			// processing the characters of the string in one go, while utilizing Horner's Rule 
			for (int k = 0; k < str.length(); k ++)
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

			// Note: x is letters which are the columns
			// Note: y is numbers which are the rows
			int coordX = colSum;
			int coordY = rowSum;

			// checks that first set of coordinates are not out of bounds
			if (coordX <= 0 || coordX > boardSize || coordY <= 0 || coordY > boardSize )
			{    
				return false;
			}
			// line 63-88 checks the spaces around the knight to make sure they cannot attack each
			for (int i = -2; i <= 2; i ++) 
			{
				for (int j = -2; j <= 2; j ++) 
				{
					if (Math.abs(i) + Math.abs(j) == 3) 
					{
						int coordX2 = coordX + i;
						int coordY2 = coordY + j;

						// makes sure second set of coordinates are in bounds
						if (coordX2 < 0 || coordX2 > boardSize || coordY2 < 0 || coordY2 > boardSize) 
						{
							continue;
						}
						if (coordX2 > Integer.MAX_VALUE || coordY2 > Integer.MAX_VALUE)
						{
							continue;
						}

						// creates temporary attack point that checks if hashSet contains it
						Point attackHere = new Point(coordX2, coordY2);
						if (positions.contains(attackHere)) 
						{	
							return false;
						}
					}
				}
			}
			// creating new knight and adding to hashSet
			Point knight = new Point(coordX,coordY);
			positions.add(knight);
		}
		return true;
	}

	public static double difficultyRating()
	{
		return 3.0;
	}
	public static double hoursSpent()
	{
		return 9.0;
	}
}