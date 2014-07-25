package com.geeks.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.geeks.BinaryTree;
import com.geeks.BinaryTree.TraverseCallback;
import com.geeks.BinaryTree.TreeNode;

public class TreeOperationsTest {
	TraverseCallback<TreeNode<Character>> printCallback = new TraverseCallback<TreeNode<Character>>() {

		@Override
		public void callback(TreeNode<Character> t) {
			if (t == null) {
				System.out.println(" ");
			} else
				System.out.print(t.elem);
		}
	};

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("------------------------------");
	}

	@Test
	public void testConstructTree() {
		Character[] preOrder = { 'A', 'B', 'D', 'G', 'E', 'H', 'C', 'F', 'I',
				'J' };
		Character[] inOrder = { 'D', 'G', 'B', 'H', 'E', 'A', 'C', 'I', 'F',
				'J' };

		TreeNode<Character> tree = BinaryTree.constructTreeNode(0, preOrder,
				inOrder);

		TreeNode<Character> dummyNode = new TreeNode<Character>();
		dummyNode.elem = '_';
		BinaryTree.printBinaryTreeHumanReadable(tree, dummyNode);
	}

	@Test
	public void testInOrderMorrisTraverse() {
		Character[] preOrder = { 'A', 'B', 'D', 'G', 'E', 'H', 'C', 'F', 'I',
				'J' };
		Character[] inOrder = { 'D', 'G', 'B', 'H', 'E', 'A', 'C', 'I', 'F',
				'J' };

		TreeNode<Character> tree = BinaryTree.constructTreeNode(0, preOrder,
				inOrder);

		BinaryTree.inOrderMorrisTraverse(tree, printCallback);
	}

	@Test
	public void testConvertToDoubleLinkedListInPostOrder() {
		Character[] preOrder = { 'A', 'B', 'D', 'G', 'E', 'H', 'C', 'F', 'I',
				'J' };
		Character[] inOrder = { 'D', 'G', 'B', 'H', 'E', 'A', 'C', 'I', 'F',
				'J' };
		TreeNode<Character> tree = BinaryTree.constructTreeNode(0, preOrder,
				inOrder);

		TreeNode<Character> ddl = BinaryTree
				.convertToDoubleLinkedListInPostOrder(tree);

		while (ddl != null) {
			System.out.print(ddl.elem);
			ddl = ddl.left;
		}

	}

	@Test
	public void testLevelOrderTraverse() {
		Character[] preOrder = { 'A', 'B', 'D', 'G', 'E', 'H', 'C', 'F', 'I',
				'J' };
		Character[] inOrder = { 'D', 'G', 'B', 'H', 'E', 'A', 'C', 'I', 'F',
				'J' };
		TreeNode<Character> tree = BinaryTree.constructTreeNode(0, preOrder,
				inOrder);

		BinaryTree.levelOrderTraverse(tree, null);
	}
}
