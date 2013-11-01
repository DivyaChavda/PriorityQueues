package com.aplshd.priorityqueues;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Implements the Priority Queue using Binary Heap data structure It takes a
 * vector, converts the vector to an array, and then calls buildHeap to turn the
 * array into a heap
 * 
 * @param <Heap>
 */

public class BinaryHeap {

	private double[] A;
	private int heapSize;

	/** Creates an empty binary heap. */

	public BinaryHeap() {
		A = new double[1000];
		heapSize = 0;
	}

	/**
	 * Creates a new heap containing no elements
	 */
	public void makeHeap() {

	}

	/**
	 * inserts the element
	 * 
	 * @param key
	 */

	public void insert(double key) {
		if (heapSize == A.length)
			System.out.println("Heap's underlying storage is overflow");
		else {
			heapSize++;
			A[heapSize - 1] = key;
			bubbleUp(heapSize - 1);
		}
	}

	/**
	 * Deletes a node.
	 * 
	 * @param key
	 */
	public void delete(double key) {
		int p;
		if ((p = contains(key)) == -1) {
			System.out.println("key is not in the priority queue");
			return;
		}
		if (p < 0 || p >= heapSize) {
			System.out.println("Not in heap");
		}
		if (p >= 0 && p < heapSize) {
			exch(p, --heapSize); // Swap with last value
		}

		if (heapSize != 0)
			bubbleDown(p); // push down
		return;
	}

	/**
	 * Checks if the key is present in Heap or not
	 * 
	 * @param key
	 *            Returns boolean
	 */
	private int contains(double key) {
		int j;
		for (j = 0; j < heapSize; j++) {
			if (A[j] == key)
				return j;
		}
		return -1;
	}

	/**
	 * returns the key of the node which is minimum
	 * 
	 */

	double extractMin() {
		double tmp;
		if (this.isEmpty(heapSize)) {
			throw new IllegalStateException();
		} else {
			tmp = A[0];
			A[0] = A[heapSize];
			heapSize--;
			bubbleDown(0);
		}
		return tmp;
	}

	/**
	 * returns a pointer to the node whose key is minimum
	 * 
	 */

	double findMin() {
		if (this.isEmpty(heapSize)) {
			throw new IllegalStateException();
		}

		return A[0];
	}

	/**
	 * updates the key of the node
	 * 
	 * @param key
	 */

	public void updateKey(double i, double j) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			DecimalFormat df = new DecimalFormat();
			Number n = df.parse(s);
			double userInput1 = n.doubleValue();
			double userInput2 = n.doubleValue();
			int x;
			for (x = 0; x <= heapSize;) {
				if (A[x] == userInput1)
					System.out.println("Input Key is same as existing Key");
				if (A[x] < userInput1) {
					A[x] = userInput2;
					bubbleUp(x);
				}
				if (A[x] > userInput1) {
					A[x] = userInput2;
					bubbleDown(x);
				}
			}

		} catch (ParseException ex) {
			System.out.println("Wrong input...");
		} catch (IOException ex) {
			System.out.println("IOException" + ex);
		}

	}

	/**
	 * Tree structure of the priority queue is stored in the fileName in dot
	 * language format
	 * 
	 * @param fileName
	 */
	public void displayHeap(String fileName) {
		if (isEmpty()) {
			System.out.println("Heap is Empty");
			return;
		}
		PrintWriter out = null;
		try {
			int d;
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName,
					false)));
			out.println("digraph BinaryHeap {");
			System.out.println("digraph BinaryHeap {");
			for (int i = 0; i < heapSize-1; i++) {

				if ((d = getLeftChildIndex(i)) != -1) {
					System.out.println(A[i] + " -> " + A[d]);
					out.println(A[i] + " -> " + A[d]);
				}
				if ((d = getRightChildIndex(i)) != -1) {
					System.out.println(A[i] + " -> " + A[d]);
					out.println(A[i] + " -> " + A[d]);
				}
				if (getLeftChildIndex(i) == -1 && getRightChildIndex(i) == -1) {
					System.out.println(A[i] + "");
					out.println(A[i] + "");
				}
			}
			out.println("}");
			System.out.println("}");

		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * checks if heap is empty
	 * 
	 * @return
	 */
	public boolean isEmpty(int heapSize) {
		return (heapSize == 0);
	}

	/**
	 * returns a pointer to the left child of node
	 * 
	 * @return
	 */
	private int getLeftChildIndex(int nodeIndex) {
		if ((2 * nodeIndex + 1) >= heapSize) {
			return -1;
		}
		return 2 * nodeIndex + 1;
	}

	/**
	 * returns a pointer to the right child of node
	 * 
	 * @return
	 */
	private int getRightChildIndex(int nodeIndex) {
		if ((2 * nodeIndex + 2) >= heapSize) {
			return -1;
		}
		return 2 * nodeIndex + 2;
	}

	/**
	 * returns a pointer to the parent of node
	 * 
	 * @return
	 */
	private int getParentIndex(int nodeIndex) {
		return (nodeIndex - 1) / 2;
	}

	/**
	 * Restores heap property by shifting the node up to its correct position
	 * 
	 * @return
	 */
	private void bubbleUp(int nodeIndex) {
		int parentIndex;
		double tmp;
		if (nodeIndex != 0) {
			parentIndex = getParentIndex(nodeIndex);
			if (A[getParentIndex(nodeIndex)] > A[nodeIndex]) {
				tmp = A[parentIndex];
				A[parentIndex] = A[nodeIndex];
				A[nodeIndex] = tmp;
				bubbleUp(parentIndex);
			}
		}
	}

	/**
	 * to swap two elements
	 * 
	 * @return
	 */

	private void exch(int i, int j) {
		double temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	/**
	 * Restores heap property by shifting the node down to its correct position
	 * 
	 * @return
	 */
	private void bubbleDown(int nodeIndex) {
		int leftChildIndex, rightChildIndex, minIndex=-1;
		double tmp;
		leftChildIndex = getLeftChildIndex(nodeIndex);
		rightChildIndex = getRightChildIndex(nodeIndex);
		if(leftChildIndex > -1 && rightChildIndex > -1 && leftChildIndex < heapSize && rightChildIndex < heapSize){
		if(A[leftChildIndex]>A[rightChildIndex]){
			minIndex = rightChildIndex;
		}
		else{
			minIndex = leftChildIndex;
		}
		}else if(leftChildIndex > -1 && leftChildIndex < heapSize){
			minIndex = leftChildIndex;
		}else if(rightChildIndex > -1 && rightChildIndex < heapSize){
			minIndex = leftChildIndex;
		}
		
		if (A[nodeIndex] > A[minIndex]) {
		
			tmp = A[minIndex];
			A[minIndex] = A[nodeIndex];
			A[nodeIndex] = tmp;
			bubbleDown(minIndex);
		}
	}

	public class Node {
		/** The Key stored in this Node */
		double key;
		/** The number of children that this node has. */

		Node parent;
		/**
		 * This node's leftmost child, or null if this node has no children.
		 */
		Node child;

		/**
		 * This node's right sibling, or null if this node has no right sibling.
		 */

		public Node(Double d) {
			key = d;
			parent = null;
			child = null;
		}

	}

	public void enterFileName(String inputFileName) {
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
		if (heapSize == 0) {
			return true;
		}
		return false;
	}

	public double findMinKey() {
		if (this.isEmpty(heapSize)) {
			throw new IllegalStateException();
		}

		return A[0];

	}
}
