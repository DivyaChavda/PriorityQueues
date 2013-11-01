package com.aplshd.priorityqueues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Vector;

/**
 * Implements the Priority Queue using Binary Heap data structure
 */
public class BinomialHeap {

	/**
	 * The head of the singly linked root list of BinomialHeap.
	 */
	public Node head;

	/** Creates an empty binomial heap. */
	public BinomialHeap() {
		head = null; // make an empty root list
	}

	/**
	 * Creates a new heap containing no elements
	 */
	public BinomialHeap makeHeap() {
		BinomialHeap H = new BinomialHeap();
		return H;
	}

	/**
	 * Deletes a node.
	 * 
	 * @param key
	 */
	public void delete(double key) {
		Node x = findNode(head, key);
		if (x == null) {
			if (head == null){
				System.out.println("The Priority Queue is Empty");
				return;
			}
			else{
				System.out.println("Key Not found");
			return;
			}
		}
		// Bubble x up to be a root, which is what would happen if we
		// could decrease its key to negative infinity. Because the
		// contents of nodes change during bubbleUp, the node
		// containing x's contents may change, and so we have to
		// update x.
		x = fetchUp(x, true);

		// Now remove x from the root list.
		if (head == x)
			removeFromRootList(x, null); // easy case
		else {
			// Find x's predecessor.
			Node pred = head;
			while (pred.sibling != x)
				pred = pred.sibling;

			// At this point, pred.sibling is x, so pred is x's
			// predecessor.
			removeFromRootList(x, pred);
		}
		System.out.println(" Key " + key
				+ " deleted successfully.");
	}

	/**
	 * This is to fetch key up
	 * 
	 * @param u
	 *            the node to fetchup
	 * @param r
	 *            if true fetch upto the root otherwise till its value is less
	 *            than or equal to its parent
	 * @return
	 */
	public Node fetchUp(Node u, boolean r) {
		Node a = u;
		Node b = a.parent;

		while (b != null && (r || a.key < b.key)) {
			// Exchange the key values of two nodes
			double temp = a.key;
			a.key = b.key;
			b.key = temp;

			a.handle.node = b;
			b.handle.node = a;

			Handle aHandle = a.handle;
			a.handle = b.handle;
			b.handle = aHandle;

			// Go up one more level.
			a = b;
			b = a.parent;

		}
		return a;
	}

	/**
	 * inserts the element
	 * 
	 * @param key
	 */
	public double insert(Double key) {

		Node x = new Node(key);
		BinomialHeap heap = new BinomialHeap();
		heap.head = x;
		BinomialHeap newHeap = (BinomialHeap) this.union(heap);
		head = newHeap.head;
		System.out.println(head.key + "");
		return head.key;
	}

	/**
	 * deletes the node from the heap whose key is minimum, returning a pointer
	 * to the node
	 * 
	 * @return
	 */
	public double extractMin() {
		// Special case for an empty binomial heap.
		if (head == null)
			return 0;

		// Find the root x with the minimum key in the root list.
		Node x = head; // node with minimum key
		Node y = x.sibling; // current node being examined
		Node pred = x; // y's predecessor
		Node xPred = null; // predecessor of x

		while (y != null) {
			if (y.key < x.key) {
				x = y;
				xPred = pred;
			}
			pred = y;
			y = y.sibling;
		}

		removeFromRootList(x, xPred);

		return x.key;

	}

	/**
	 * returns a pointer to the node whose key is minimum
	 * 
	 * @return
	 */
	public Node findMin() {
		if (head == null)
			return null; // empty heap, hence no minimum
		else {
			Node min = head; // min takes the role of both min and y
			Node x = min.sibling;
			if (min != null && min.sibling == null && min.child == null)
				return min;
			while (x != null) {
				if (x.key < min.key)
					min = x;
				x = x.sibling;
			}

			return min;
		}
	}

	public double findMinKey() {
		if (findMin() == null)
			return (Double) null;
		return this.findMin().key;
	}

	private BinomialHeap union(BinomialHeap heap) {
		BinomialHeap h = new BinomialHeap();
		h.head = Merge(this, (BinomialHeap) heap);
		head = null; // no longer using the...
		((BinomialHeap) heap).head = null; // ...two input lists

		if (h.head == null)
			return h;

		Node prevX = null;
		Node x = h.head;
		Node nextX = x.sibling;

		while (nextX != null) {
			if (x.degree != nextX.degree
					|| (nextX.sibling != null && nextX.sibling.degree == x.degree)) {
				// Cases 1 and 2.
				prevX = x;
				x = nextX;
			} else {
				if (x.key < nextX.key) {
					// Case 3.
					x.sibling = nextX.sibling;
					Link(nextX, x);
				} else {
					// Case 4.
					if (prevX == null)
						h.head = nextX;
					else
						prevX.sibling = nextX;

					Link(x, nextX);
					x = nextX;
				}
			}

			nextX = x.sibling;
		}

		return h;
	}

	private void Link(Node nextX, Node x) {
		nextX.parent = x;
		nextX.sibling = x.child;
		x.child = nextX;
		x.degree++;
	}

	private Node Merge(BinomialHeap heap1, BinomialHeap heap2) {
		// If either root list is empty, just return the other.
		if (heap1.head == null)
			return heap2.head;
		else if (heap2.head == null)
			return heap1.head;
		else {
			// Neither root list is empty. Scan through both, always
			// using the node whose degree is smallest of those not
			// yet taken.
			Node head; // head of merged list
			Node tail; // last node added to merged list
			Node h1Next = heap1.head, h2Next = heap2.head; // next nodes to be
			// examined in h1
			// and h2

			if (heap1.head.degree <= heap2.head.degree) {
				head = heap1.head;
				h1Next = h1Next.sibling;
			} else {
				head = heap2.head;
				h2Next = h2Next.sibling;
			}

			tail = head;

			// Go through both root lists until one of them is
			// exhausted.
			while (h1Next != null && h2Next != null) {
				if (h1Next.degree <= h2Next.degree) {
					tail.sibling = h1Next;
					h1Next = h1Next.sibling;
				} else {
					tail.sibling = h2Next;
					h2Next = h2Next.sibling;
				}

				tail = tail.sibling;
			}

			// The above loop ended because exactly one of the root
			// lists was exhuasted. Splice the remainder of whichever
			// root list was not exhausted onto the list we're
			// constructing.
			if (h1Next != null)
				tail.sibling = h1Next;
			else
				tail.sibling = h2Next;

			return head; // all done!
		}
	}

	/**
	 * Helper method to remove a node from the root list.
	 * 
	 * @param x
	 *            The node to remove from the root list.
	 * @param pred
	 *            The predecessor of x in the root list, or null if x is the
	 *            first node in the root list.
	 */
	private void removeFromRootList(Node x, Node pred) {
		// Splice out x.
		if (x == head)
			head = x.sibling;
		else
			pred.sibling = x.sibling;

		BinomialHeap h = new BinomialHeap();

		// Reverse the order of x's children, setting h.head to
		// point to the head of the resulting list.
		Node z = x.child;
		while (z != null) {
			Node next = z.sibling;
			z.parent = null;
			z.sibling = h.head;
			h.head = z;
			z = next;
		}

		BinomialHeap newHeap = (BinomialHeap) this.union(h);
		head = newHeap.head;
		Node j=head;
		while(j!=null){
			if(j.parent == x){
				j.parent= null;
			}
			if(j.sibling == x){
				j.sibling = null;
			}
			j=j.sibling;
		}
	}

	/**
	 * decreases the key of the node
	 * 
	 * @param key
	 */
	void decrease(double key1, double key2) {

		Node x = findNode(head, key1);
		if (x == null) {
			if (head == null)
				System.out.println("The Priority Queue is Empty");
			else
				System.out.println("Key Not found");
			return;
		}
		x.key = key2; // update x's key
		fetchUp(x, false); // Move it up until it's in the right place

	}

	void increase(double key1, double key2) {
		Node x = findNode(head, key1);
		if (x == null) {
			if (head == null)
				System.out.println("The Priority Queue is Empty");
			else
				System.out.println("Key Not found");
			return;
		}
		x.key = key2;// update x's key
		pushDown(x, false);// Move it down until it's in the right place
	}

	private Node pushDown(Node x, boolean r) {
		Node a = x;
		Node b = a.child;

		while (b != null && (r || a.key > b.key)) {
			// Exchange the key values of two nodes
			double temp = a.key;
			a.key = b.key;
			b.key = temp;

			a.handle.node = b;
			b.handle.node = a;

			Handle aHandle = a.handle;
			a.handle = b.handle;
			b.handle = aHandle;

			// Go down by one more level.
			a = b;
			b = a.child;

		}
		return a;
	}

	public Node findNode(Node h, double key) {

		if (h == null) {
			return null;
		}
		Node n = h;

		if (n.key == key) {
			System.out.println("The node visited is " + n.key);
			return n;
		}
		n = findNode(h.sibling, key);
		if (n != null)
			return n;
		n = findNode(h.child, key);
		if (n != null)
			return n;

		/*
		 * while (n.sibling != null) { if ((n = findNode(n.sibling, key)) !=
		 * null) return n; }
		 * 
		 * while (n.child != null) { if ((n = findNode(n.child, key)) != null)
		 * return n; }
		 */

		return null;
	}

	/**
	 * Tree structure of the priority queue is stored in the fileName in dot
	 * language format
	 * 
	 * @param fileName
	 */
	void displayHeap(String fileName) {
		if (this.head == null) {
			System.out.println("The Priority Queue is Empty");
			return;
		}
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName,
					false)));
			out.println("digraph BinomialHeap {");
			System.out.println("digraph BinomialHeap {");
			dfs(this.head, out);
			printRank(this.head, out, true);
			out.println("}");
			System.out.println("}");
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (out != null) {
				/*
				 * Closes the stream and releases any system resources
				 * associated with it.
				 */
				out.close();
			}
		}
	}

	private void printRank(Node head2, PrintWriter out, boolean flag) {

		String p = "";
		Node n = head2;
		while (n != null && flag) {
			p = p + n.key + " ";
			n = n.sibling;
		}
		n = head2;
		if (flag)
			out.println("{ rank=same; " + p + " }");

		if (n.child != null) {
			printRank(n.child, out, true);
		}
		if (n.sibling != null) {
			printRank(n.sibling, out, false);
		}
	}

	private void dfs(Node head2, PrintWriter out) {
		Node n = head2;
		if (head2 == null)
			return;
		dfs_visit(head2, out);
		dfs(n.child, out);
		dfs(n.sibling, out);
	}

	private void dfs_visit(Node vSibling, PrintWriter out) {

		if (vSibling.child != null) {
			out.println(vSibling.key + "  ->  " + vSibling.child.key
					+ " [color=blue label=child] ;");
			System.out.println(vSibling.key + "  ->  " + vSibling.child.key
					+ " [color=blue label=child] ;");
		}
		if (vSibling.sibling != null) {
			out.println(vSibling.key + "  ->  " + vSibling.sibling.key
					+ " [color=green style=dashed label=sibling] ;");
			System.out.println(vSibling.key + "  ->  " + vSibling.sibling.key
					+ " [color=green style=dashed  label=sibling] ;");
		}
		if (vSibling.parent != null) {
			out.println(vSibling.key + "  ->  " + vSibling.parent.key
					+ " [color=black  label=parent] ;");
			System.out.println(vSibling.key + "  ->  " + vSibling.parent.key
					+ " [color=black style=dotted label=parent] ;");
		}
		if(vSibling.child == null && vSibling.sibling == null && vSibling.parent == null){
			out.println(vSibling.key+"");
			System.out.println(vSibling.key+"");
		}

	}

	/** Inner class for a node within a binomial heap. */
	private static class Node {
		/** The Key stored in this Node */
		double key;
		/** The number of children that this node has. */
		int degree;
		/**
		 * This node's parent, or null if this node is a root.
		 */
		Node parent;
		/**
		 * This node's leftmost child, or null if this node has no children.
		 */
		Node child;
		/**
		 * This node's right sibling, or <code>null</code> if this node has no
		 * right sibling.
		 */
		Node sibling;
		/** A handle to this node. */
		public Handle handle;

		/**
		 * Creates a new node.
		 * 
		 * @param e
		 *            The key to store in the node.
		 */
		public Node(Double d) {
			key = d;
			parent = null;
			child = null;
			sibling = null;
			degree = 0;
			handle = new Handle(this);
		}

	}

	private static class Handle {
		/**
		 * The Node referenced by this Handle.
		 */
		public Node node;

		/** Saves the node in this Handle. */
		public Handle(Node n) {
			node = n;
		}
	}

	/*
	 * private static class Stack { double n; int rank;
	 * 
	 * Stack(double a, int b) { n = a; rank = b; } }
	 */

	public void enterFilename(String inputFileName) {

		try {
			FileReader fr = new FileReader(inputFileName);
			if (fr == null) {
				System.out.println("File Error");
				return;
			}
			BufferedReader in = new BufferedReader(fr);
			String str = in.readLine();
			String[] token;
			token = str.split(" ");
			int i = 0;
			while (i < token.length) {
				double d = Double.parseDouble(token[i]);
				this.insert(d);
				i++;
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public boolean isEmpty() {
		if (head == null)
			return true;
		else
			return false;
	}

	public void findN(double key) {
		Node n = null;
		n = findNode(head, key);
		if (n != null)
			System.out.println("key=" + n.key);
		else {
			System.out.println("Not Found");
		}
	}

	public void updateKey(double key1, double key2) {
		if (key1 > key2)
			decrease(key1, key2);
		else
			increase(key1, key2);
		
		
	}

}