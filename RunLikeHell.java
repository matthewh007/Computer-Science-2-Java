// Matthew Hernandez
// NID: ma040619
// COP 3503, Spring 2023

import java.util.*;
import java.io.*;

public class RunLikeHell
{
	 public static int maxGain(int [] blocks)
	 {
	 	// initializing some variables
	 	int num = blocks.length;
	 	int[] helperArray = new int[num];

	 	// base case to check if blocks array is empty 
	 	if (num == 0)
	 		return 0;
	 	else
	 		helperArray[0] = blocks[0];

	 	// anther base case to check if blocks array has one element
	 	if (num == 1)
	 		return blocks[0];

	 	// here I assign helperArray with the max between the first two elements in blocks
	 	// I then use a for loop starting at the third elment in the blocks array
	 	// I use the loop to check the max between block i-2 and block i

	 	helperArray[1] = Math.max(blocks[0], blocks[1]);
	 	for (int i = 2; i < num; i++) 
	 	{
	 		helperArray[i] = Math.max(helperArray[i-1], helperArray[i-2] + blocks[i]);
	 	}
	 	// this maximum value gets stored in the last element of helperArray 
	 	return helperArray[num-1];
	 }

	public static double difficultyRating()
	{
		return 2.0;
	}
	public static double hoursSpent()
	{
		return 7.0;
	}

}