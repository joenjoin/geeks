package com.geeks;

import java.util.Arrays;

public class BinarySearchTree {
	public static void main(String[] args) {
		Character[] inOrder = { 'D', 'B', 'E', 'A', 'F', 'C' };
		Character[] preOrder = { 'A', 'B', 'D', 'E', 'C', 'F' };

		TreeNode<Character> tree = constructTreeNode(0, preOrder, inOrder);

		postOrderTraverse(tree, new TraverseCallback() {

			public <T> void callback(T t) {
				TreeNode<Character> treeNode = (TreeNode<Character>) t;
				System.out.println(treeNode.elem);
			}
		});
	}

	/**
	 * 
	 * @param inOrder
	 * @param preOrder
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

	private static <T> void postOrderTraverse(TreeNode<T> tree,
			TraverseCallback callback) {
		if (tree.right != null)
			postOrderTraverse(tree.right, callback);

		if (tree.left != null)
			postOrderTraverse(tree.left, callback);

		callback.callback(tree);
		// System.out.println(tree.elem);
	}

	private static interface TraverseCallback {
		public <T> void callback(T t);
	}

	private static class TreeNode<T> {
		TreeNode<T> left;
		TreeNode<T> right;

		T elem;
	}
}
