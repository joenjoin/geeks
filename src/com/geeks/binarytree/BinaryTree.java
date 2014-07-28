package com.geeks.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

public class BinaryTree {
	/**
	 * Construct a binary tree from preOrder and inOrder sequence
	 * 
	 * @param rootPos
	 * @param preOrder
	 * @param inOrder
	 * 
	 * @return
	 */
	public static <T> TreeNode<T> constructTreeNode(int rootPos, T[] preOrder,
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
	 * Get height of a tree recursively
	 * 
	 * @param tree
	 *            root node
	 * @return
	 */
	public static <T> int getHeight(TreeNode<T> tree) {
		if (tree == null || (tree.left == null && tree.right == null))
			return 0;

		int leftHeight = getHeight(tree.left);
		int rightHeight = getHeight(tree.right);

		int height = leftHeight > rightHeight ? leftHeight : rightHeight;

		return height + 1;
	}

	/**
	 * traverse a binary tree in level order
	 * 
	 * @param tree
	 * @param callback
	 */
	public static <T> void levelOrderTraverse(TreeNode<T> tree,
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

			if (callback != null) {
				callback.callback(node);
			}

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

	public static <T> void postOrderTraverse(TreeNode<T> tree,
			TraverseCallback<TreeNode<T>> callback) {
		if (tree.right != null)
			postOrderTraverse(tree.right, callback);

		if (tree.left != null)
			postOrderTraverse(tree.left, callback);

		callback.callback(tree);
	}

	/**
	 * Using Morris Traversal, we can traverse the tree <strong>without using
	 * stack and recursion</strong>. The idea of Morris Traversal is based on
	 * <strong>Threaded Binary Tree</strong>.
	 * <p>
	 * In this traversal, we first create links to Inorder successor and print
	 * the data using these links, and finally revert the changes to restore
	 * original tree
	 * </p>
	 * 
	 * @param tree
	 *            node root
	 * @param callback
	 *            traverse callback
	 */
	public static <T> void inOrderMorrisTraverse(TreeNode<T> tree,
			TraverseCallback<TreeNode<T>> callback) {
		TreeNode<T> current = tree;

		TreeNode<T> prev;

		while (current != null) {
			if (current.left == null) {
				callback.callback(current);

				current = current.right;
			} else {
				prev = current.left;

				while (prev.right != null && prev.right != current) {
					prev = prev.right;
				}

				if (prev.right == null) {
					// Found the rightmost node in left tree of current node
					prev.right = current;
					current = current.left;
				} else {
					// Comes to this condition only because
					// of prev.right == current

					// Then revert pointers of right mode child node
					prev.right = null;
					callback.callback(current);

					current = current.right;
				}
			}
		}

		System.out.println();
	}

	/**
	 * Convert binary tree to double linked list in post order
	 * 
	 * node left will point to next node; node right will point to previous
	 * 
	 * 
	 * @param tree
	 * @return
	 */
	public static <T> TreeNode<T> convertToDoubleLinkedListInPostOrder(
			TreeNode<T> tree) {

		if (tree == null)
			return null;

		TreeNode<T> leftList = convertToDoubleLinkedListInPostOrder(tree.left);
		TreeNode<T> rightList = convertToDoubleLinkedListInPostOrder(tree.right);

		// Following will concatenate 3 linked lists into one
		// <left list> <right list> <root node>
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

	public static <T> void printBinaryTreeHumanReadable(TreeNode<T> tree,
			TreeNode<T> dummyNode) {
		LinkedList<TreeNode<T>> queue1 = new LinkedList<TreeNode<T>>();
		LinkedList<TreeNode<T>> queue2 = new LinkedList<TreeNode<T>>();

		int level = 0;
		int totalHeight = getHeight(tree);
		boolean firstChildInLevel = false;

		queue1.offer(tree);
		firstChildInLevel = true;
		TreeNode<T> tmp = null;
		int intervalIndent = 0;
		int leadingIndent = 0;

		do {
			tmp = queue1.poll();

			if (tmp != null) {
				if (firstChildInLevel) {
					intervalIndent = leadingIndent;
					leadingIndent = (1 << (totalHeight - level)) - 1;

					for (int i = 0; i < leadingIndent; i++) {
						System.out.print(" ");
					}
					firstChildInLevel = false;
				}

				System.out.print(tmp.elem);

				if (!queue1.isEmpty()) {
					for (int i = 0; i < intervalIndent; i++) {
						System.out.print(" ");
					}
				}

				if (tmp.left != null)
					queue2.offer(tmp.left);
				else if (level != totalHeight) {
					queue2.offer(dummyNode);
				}

				if (tmp.right != null)
					queue2.offer(tmp.right);
				else if (level != totalHeight) {
					queue2.offer(dummyNode);
				}
			} else {
				if (!queue2.isEmpty()) {
					LinkedList<TreeNode<T>> tmpQ = queue1;
					queue1 = queue2;
					queue2 = tmpQ;

					firstChildInLevel = true;

					level++;

				} else {
					break;
				}

				System.out.println();
			}
		} while (true);

		System.out.println();
	}

	/**
	 * LCA problem (Lowest Common Ancestor) of 2 TreeNode in a tree
	 * 
	 * @param node1
	 * @param node2
	 */
	public static <T> void lowestCommonAncestor(TreeNode<T> node1,
			TreeNode<T> node2) {

	}

	public static interface TraverseCallback<T> {
		public void callback(T t);
	}

	public static class TreeNode<T> {
		public TreeNode<T> left;
		public TreeNode<T> right;

		public T elem;
	}
}
