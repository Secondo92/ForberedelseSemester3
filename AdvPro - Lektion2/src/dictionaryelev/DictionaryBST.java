package dictionaryelev;

import java.util.Stack;

public class DictionaryBST<K extends Comparable<K>, V> implements
Dictionary<K, V> {

	private Node root;

	public DictionaryBST() {
		root = null;
	}

	@Override
	public V get(K key) {
		Node node = find(key);
		if(node == null){
			return null;
		}
		return node.value;
	}

	private Node find(K key) {
		Node current = root;
		boolean found = false;
		while (!found && current != null) {
			int d = current.key.compareTo(key);
			if (d == 0) {
				found = true;
			} else if (d > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		if (found) {
			return current;
		} else {
			return null;
		}

	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public V put(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Key and value must not be null");
		}

		if(root == null){
			root = new Node(key, value);
			return root.value;
		}
		Node parent = null;
		Node current = root;
		while(current != null){
			int compareKey = key.compareTo(current.key);
			if(compareKey == 0){
				V oldValue = current.value;
				current.value = value;
				return oldValue;
			}
			parent = current;
			if(compareKey < 0){
				current = current.left;
			} else {
				current = current.right;
			}
		}
		int comparison = key.compareTo(parent.key);
		if (comparison < 0) {
			parent.left = new Node(key, value);
		} else {
			parent.right = new Node(key, value);
		}
		return null;
	}

	@Override
	public V remove(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key must not be null");
		}

		Node parent = null;
		Node current = root;
		boolean isLeftChild = true;

		// Search for the node to be removed
		while (current != null && !current.key.equals(key)) {
			parent = current;
			int comparison = key.compareTo(current.key);
			if (comparison < 0) {
				isLeftChild = true;
				current = current.left;
			} else {
				isLeftChild = false;
				current = current.right;
			}
		}

		// If the node wasn't found, return null
		if (current == null) {
			return null;
		}

		V oldValue = current.value;

		// Case 1: Node to be deleted has no children (leaf node)
		if (current.left == null && current.right == null) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		}
		// Case 2: Node to be deleted has one child (right child)
		else if (current.left == null) {
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		}
		// Case 2: Node to be deleted has one child (left child)
		else if (current.right == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		}
		// Case 3: Node to be deleted has two children
		else {
			// Find the successor (smallest in the right subtree)
			Node successorParent = current;
			Node successor = current.right;
			while (successor.left != null) {
				successorParent = successor;
				successor = successor.left;
			}

			// Connect the successorParent to the successor's right child
			if (successorParent != current) {
				successorParent.left = successor.right;
				successor.right = current.right;
			}
			successor.left = current.left;

			// Replace current with successor
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
		}

		return oldValue;
	}

	@Override
	public int size() {
		if(root == null){
			return 0;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		int counter = 0;

		while(!stack.isEmpty()){
			Node node = stack.pop();    // 1. Stack er tom nu
			counter++;					// 2. Stack er nu elementet til højre eller venstre

			if(node.left != null){
				stack.push(node.left);  // 1. Stack har venstre element fra roden
			}
			if(node.right != null){
				stack.push(node.right); // 1. Stack har venstre element fra roden
			}
		}
		return counter;
	}

	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}


	}

}
