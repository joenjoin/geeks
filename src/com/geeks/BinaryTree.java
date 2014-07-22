package com.geeks;

import java.util.Arrays;
import java.util.LinkedList;

public class BinaryTree {
	public static void main(String[] args) {
		Character[] inOrder = { 'D', 'B', 'E', 'A', 'F', 'C' };
		Character[] preOrder = { 'A', 'B', 'D', 'E', 'C', 'F' };

		TreeNode<Character> tree = constructTreeNode(0, preOrder, inOrder);

		TraverseCallback<TreeNode<Character>> printCallback = new TraverseCallback<TreeNode<Character>>() {

			@Override
			public void callback(TreeNode<Character> t) {
				// if (t == null) {
				// System.out.println(" ");
				// } else
				// System.out.println(t.elem);
			}
		};

		// postOrderTraverse(tree, printCallback);

		// levelOrderTraverse(tree, printCallback);

		TreeNode<Character> ddl = convertToDoubleLinkedListInPostOrder(tree);

		while (ddl != null) {
			System.out.print(ddl.elem);
			ddl = ddl.left;
		}

	}

	/**
	 * Construct a binary tree from preOrder and inOrder sequence
	 * 
	 * @param rootPos
	 * @param preOrder
	 * @param inOrder
	 * 
	 * @return
	 */
	private static <T> TreeNode<T> constructTreeNode(int rootPos, T[] preOrder,
			T[] inOrder) {
		if (rootPos == preOrder.length) {
			return null;
		}

		if (inOrder == null || inOrder.length == 0)
			return null;

		T childRoot = preOrder[rootPos];

		int i = 0;
		for (; i < inOrder.length; i++) {
			if (childRoot.equals(inOrder[i]))
				break;
		}

		T[] leftChildInOrder = Arrays.copyOfRange(inOrder, 0, i);

		T[] rightChildInOrder = null;
		if (i < inOrder.length - 1) {
			rightChildInOrder = Arrays.copyOfRange(inOrder, i + 1,
					inOrder.length);
		}

		TreeNode<T> leftChild = constructTreeNode(rootPos + 1, preOrder,
				leftChildInOrder);
		TreeNode<T> rightChild = constructTreeNode(rootPos + i + 1, preOrder,
				rightChildInOrder);

		TreeNode<T> node = new TreeNode<T>();
		node.left = leftChild;
		node.right = rightChild;
		node.elem = childRoot;

		return node;
	}

	/**
	 * traverse a binary tree in level order
	 * 
	 * @param tree
	 * @param callback
	 */
	private static <T> void levelOrderTraverse(TreeNode<T> tree,
			TraverseCallback<TreeNode<T>> callback) {
		LinkedList<TreeNode<T>> queue1 = new LinkedList<TreeNode<T>>();
		LinkedList<TreeNode<T>> queue2 = new LinkedList<TreeNode<T>>();

		queue1.push(tree);

		do {
			if (queue1.isEmpty()) {
				LinkedList<TreeNode<T>> tmp = queue1;
				queue1 = queue2;
				queue2 = tmp;

				System.out.println();
			}

			if (queue1.isEmpty()) {
				break;
			}

			TreeNode<T> node = queue1.poll();
			callback.callback(node);

			if (node != null)
				queue2.offer(node.left);

			if (node != null)
				queue2.offer(node.right);

			if (node != null) {
				System.out.print(node.elem);
				System.out.print("\t");
			} else {
				System.out.print("_");
				System.out.print("\t");
			}
		} while (true);
	}

	private static <T> void postOrderTraverse(TreeNode<T> tree,
			TraverseCallback<TreeNode<T>> callback) {
		if (tree.right != null)
			postOrderTraverse(tree.right, callback);

		if (tree.left != null)
			postOrderTraverse(tree.left, callback);

		callback.callback(tree);
	}

	/**
	 * Convert binary tree to double linked list
	 * 
	 * node left will point to next node; node right will point to parent
	 * 
	 * 
	 * @param tree
	 * @return
	 */
	private static <T> TreeNode<T> convertToDoubleLinkedListInPostOrder(
			TreeNode<T> tree) {

		if (tree == null)
			return null;

		TreeNode<T> leftList = convertToDoubleLinkedListInPostOrder(tree.left);
		TreeNode<T> rightList = convertToDoubleLinkedListInPostOrder(tree.right);

		TreeNode<T> head = null;
		TreeNode<T> node = null;

		if (leftList != null) {
			head = node = leftList;

			while (node.left != null) {
				node = node.left;
			}
		}

		if (rightList != null) {
			if (node == null) { // left list is null
				head = node = rightList;
			} else {
				node.left = rightList;
				rightList.right = node;
			}

			while (node.left != null) {
				node = node.left;
			}
		}

		if (node == null) { // left and right list are both null
			head = node = tree;
		} else {
			node.left = tree;
		}

		tree.right = node;
		tree.left = null;

		return head;
	}

	private static interface TraverseCallback<T> {
		public void callback(T t);
	}

	private static class TreeNode<T> {
		TreeNode<T> left;
		TreeNode<T> right;

		T elem;
	}
}
