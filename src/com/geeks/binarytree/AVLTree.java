package com.geeks.binarytree;

/**
 * 
 * Adelson-Velskii and Landis tree
 * 
 * @author joenjoin
 * 
 */
public class AVLTree extends BinaryTree {

	public static class TreeNode<T> {
		public TreeNode<T> left;
		public TreeNode<T> right;

		public T elem;

		public int height;
	}

}
