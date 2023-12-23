// Matthew Hernandez
// NID: ma040619
// COP 3503, Spring 2023

import java.util.*;
import java.io.*;
import java.util.LinkedList;

public class TopoPaths
{
	private static int topoPathsCount, totalVertices ,vertex1 , vertex2;
	private static ArrayList<ArrayList<Integer>> adjList  = new ArrayList<>();

	public static int countTopoPaths(String filename) throws IOException 
	{
		// variables used to read the file and input vertices to adjList
		Scanner in = new Scanner (new File (filename));
		totalVertices = Integer.parseInt(in.nextLine()); 
		ArrayList<Integer> list = new ArrayList<>();

		// grabbing vertices from file and inputting them to adjList
		for (int i = 0; i < totalVertices; i++) 
		{
			list = new ArrayList<>();
			String[] line = in.nextLine().split(" ");
			vertex1 = Integer.parseInt(line[0]);
			list.add(vertex1);
			for (int j = 1; j <= vertex1; j++) 
			{
				vertex2 = Integer.parseInt(line[j]);
				list.add(vertex2);
			}
			adjList.add(list);
		}

		topoPathsCount = findTopoPaths(adjList);
		return topoPathsCount;
		
	}

	// helper method to find all valid TopoPaths
	public static int findTopoPaths (ArrayList<ArrayList<Integer>> adjList) 
	{
		// declaring some variables for later
		int[] inDegree = new int[adjList.size()];
		int count = 0; 
		int source = 0;

		// count the in-degree of each vertex in adjList
		for (int i = 0; i < adjList.size(); i++) 
		{
			for (int j = 1; j < adjList.get(i).size(); j++) 
			{
				int neighbor = adjList.get(i).get(j);
				inDegree[neighbor - 1]++;
			}
		}

		// find all sources (vertices with an in-degree of 0)
		Queue<Integer> sources = new LinkedList<>();
		for (int i = 0; i < inDegree.length; i++) 
		{
			if (inDegree[i] == 0) 
				sources.add(i + 1);
		}

		// count the number of topological sorts
		while (!sources.isEmpty()) 
		{
			source = sources.poll();

			ArrayList<Integer> path = new ArrayList<>();
			path.add(source);

			Queue<ArrayList<Integer>> queue = new LinkedList<>();
			queue.add(path);
			// second while loop that stops when queue is empty of paths
			while (!queue.isEmpty()) 
			{
				ArrayList<Integer> curPath = queue.poll();
				if (curPath.size() == adjList.size()) 
				{
					count++;
					continue;
				}

				int curNode = curPath.get(curPath.size() - 1);

				// getting the adjacency list for vertex curNode by using the .sublist method 
				// while subtracting 1 to exclude the first element 
				for ( int neighbor : adjList.get(curNode - 1).subList(1, adjList.get(curNode - 1).size())) 
				{
					if (--inDegree[neighbor - 1] == 0) 
					{
						ArrayList<Integer> newPath = new ArrayList<>(curPath);
						newPath.add(neighbor);
						queue.add(newPath);
						sources.add(neighbor);
					}
				}
			}
		}
		return count;
	}

	public static double difficultyRating()
	{
		return 4.0;
	}
	public static double hoursSpent()
	{
		return 22.0;
	}
}
