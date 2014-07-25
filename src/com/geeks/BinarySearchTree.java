package com.geeks;

import com.geeks.BinaryTree.TreeNode;

public class BinarySearchTree extends BinaryTree {
	public static void main(String[] args) {
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

	protected static <T extends Comparable<T>> TreeNode<T> insertNode(
			TreeNode<T> root, T elem) {
		if (root == null) {
			root = new TreeNode<T>();
			root.elem = elem;
		} else {
			if (elem.compareTo(root.elem) < 0) {
				root.left = insertNode(root.left, elem);
			} else if (elem.compareTo(root.elem) > 0) {
				root.right = insertNode(root.right, elem);
			} else {
				// TODO for same value node, need a counter
			}
		}

		return root;
	}

	protected static <T> void inOrderTraverse(TreeNode<T> tree) {
		if (tree == null)
			return;

		inOrderTraverse(tree.left);

		System.out.println(tree.elem);

		inOrderTraverse(tree.right);
	}

	public static <T> TreeNode<T> convertDDLInPreOrderToBST(TreeNode<T> listHead) {
		// In place merge
		return null;
	}

	public static <T> TreeNode<T> mergeBST(TreeNode<T> tree1, TreeNode<T> tree2) {
		return null;
	}

}
