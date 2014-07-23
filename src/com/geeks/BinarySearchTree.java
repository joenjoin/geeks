package com.geeks;

public class BinarySearchTree {
	public static void main(String[] args) {
		/***********************
		 * A / \ B C / \ / D E F
		 */

		TreeNode<Character> tree = null;
		tree = insertNode(tree, 'A');
		tree = insertNode(tree, 'B');
		tree = insertNode(tree, 'D');
		tree = insertNode(tree, 'E');
		tree = insertNode(tree, 'F');
		tree = insertNode(tree, 'G');
		tree = insertNode(tree, 'H');
		tree = insertNode(tree, 'I');
		tree = insertNode(tree, 'C');
 
		inOrderTraverse(tree);
	}

	public static <T extends Comparable<T>> TreeNode<T> insertNode(
			TreeNode<T> root, T elem) {
		if (root == null) {
			root = new TreeNode<T>();
			root.elem = elem;
			root.height = 0;
		} else {
			if (elem.compareTo(root.elem) < 0) {
				root.left = insertNode(root.left, elem);
				root.height++;
			} else if (elem.compareTo(root.elem) > 0) {
				root.right = insertNode(root.right, elem);
				root.height++;
			} else {
				// TODO for same value node, need a counter
			}
		}

		return root;
	}

	public static <T> void inOrderTraverse(TreeNode<T> tree) {
		if (tree == null)
			return;

		inOrderTraverse(tree.left);

		System.out.println(tree.elem);

		inOrderTraverse(tree.right);
	}

	private static class TreeNode<T> {
		TreeNode<T> left;
		TreeNode<T> right;

		T elem;

		int height;
	}
}
