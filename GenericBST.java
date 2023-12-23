// Matthew Hernandez
// NID: ma040619
// COP 3503, Spring 2023

// ====================
// GenericBST: BST.java
// ====================
// Basic binary search tree (BST) implementation that supports insert() and
// delete() operations. This framework is provided for you to modify as part of
// Programming Assignment #2.


import java.io.*;
import java.util.*;

// Node class that allows the BST to hold any type of data
class Node<AnyType>
{
	AnyType data;
	Node<AnyType> left, right;
	// constructor method for Node and AnyType of data
	Node (AnyType data)
	{
		this.data = data;
	}
}


public class GenericBST<AnyType extends Comparable<AnyType>>
{
	private Node<AnyType> root;

	// wrapper method for the insert method
	public void insert(AnyType data)
	{
		root = insert(root, data);
	}
	// a method that inserts a new Node depending on the situation i.e a empty tree 
	private Node<AnyType> insert(Node<AnyType> root, AnyType data)
	{
		if (root == null)
		{
			return new Node<AnyType>(data);
		}
		// checks if data is less than or greater than data in current node 
		// then inserts depending on that
		else if (data.compareTo(root.data) < 0)
		{
			root.left = insert(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			root.right = insert(root.right, data);
		}

		return root;
	}

	public void delete(AnyType data)
	{
		root = delete(root, data);
	}
	// a method that deletes a nod utilizing the deletion rule for BST
	private Node<AnyType> delete(Node<AnyType> root, AnyType data)
	{
		if (root == null)
		{
			return null;
		}
		// checks if data is less than or greater than data in current node 
		// then it goes down the subtree
		else if (data.compareTo(root.data) < 0)
		{
			root.left = delete(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, data);
		}
		// if the code gets to this else statement it means the you got to your desired node
		else
		{
			if (root.left == null && root.right == null)
			{
				return null;
			}
			else if (root.left == null)
			{
				return root.right;
			}
			else if (root.right == null)
			{
				return root.left;
			}
			// replaces the parent node with data from the left child and then deletes the child
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private AnyType findMax(Node<AnyType> root)
	{
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}
	// wrapper method for the contains method
	public boolean contains(AnyType data)
	{
		return contains(root, data);
	}
	// this methods checks if the tree contains a node with a specific value
	private boolean contains(Node<AnyType> root, AnyType data)
	{
		if (root == null)
		{
			return false;
		}
		else if (data.compareTo(root.data) < 0)
		{
			return contains(root.left, data);
		}
		else if (data.compareTo(root.data) > 0)
		{
			return contains(root.right, data);
		}
		else
		{
			return true;
		}
	}
	// wrapper method to call inorder method
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}
	// method that prints a BST using inorder organization
	private void inorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}
	// wrapper method to call preorder method
	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}
	// method that prints a BST using preorder organization
	private void preorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}
	// wrapper method to call postorder
	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}
	// method that prints a BST using postorder organization 
	private void postorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}
	public static double difficultyRating()
	{
		return 1.0;
	}
	public static double hoursSpent()
	{
		return 3.0;
	}

}
