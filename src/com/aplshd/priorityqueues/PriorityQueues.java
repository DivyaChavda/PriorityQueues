package com.aplshd.priorityqueues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;

public class PriorityQueues {

	public static final String BINARY = "-binary";
	public static final String BINOMIAL = "-binomial";
	public static final String FIBONACCI = "-fibonacci";

	static BinaryHeap binaryHeap = null;
	static BinomialHeap binomialHeap = null;
	static FibonacciHeap fibonacciHeap = null;
	Object heap;

	public static void main(String args[]) {
		switch (args[0]) {
		case BINARY:
			binaryHeap = new BinaryHeap();
			binaryHeap.makeHeap();
			break;

		case BINOMIAL:
			binomialHeap = new BinomialHeap();
			binomialHeap.makeHeap();
			break;

		case FIBONACCI:
			fibonacciHeap = new FibonacciHeap();
			fibonacciHeap.makeHeap();
			break;

		default:
			System.out.println("Wrong choice, Program terminated...");
			System.exit(1);
		}

		while (true) {
			System.out.println("1.Insert\n2.Delete\n3.ExtractMin"
					+ "\n4.FindMin\n5.UpdateKey" + "\n6.DisplayHeap\n7.Exit"
					+ "\n8.Enterkeybyfilename\n9.find\nEnter your choice:");
			try {
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String s;
				do {
					s = br.readLine();
				} while (s.trim() == "");
				DecimalFormat df = new DecimalFormat();
				Number n = df.parse(s);
				int menuInput = n.intValue();
				double key;
				switch (menuInput) {
				case 1:
					System.out.println("Enter the key");
					isr = new InputStreamReader(System.in);
					br = new BufferedReader(isr);
					s = br.readLine();
					df = new DecimalFormat();
					n = df.parse(s);
					key = n.intValue();
					switch (args[0]) {
					case BINARY:
						binaryHeap.insert(key);
						break;
					case BINOMIAL:
						binomialHeap.insert(key);
						break;
					case FIBONACCI:
						// fibonacciHeap.insert(key);
						break;
					}

					break;

				case 2:
					System.out.println("Enter the value of key to be Deleted");
					isr = new InputStreamReader(System.in);
					br = new BufferedReader(isr);
					s = br.readLine();
					df = new DecimalFormat();
					n = df.parse(s);
					key = n.intValue();
					switch (args[0]) {
					case BINARY:
						binaryHeap.delete(key);
						System.out.println(" Key " + key
								+ " deleted successfully.");
						break;
					case BINOMIAL:
						binomialHeap.delete(key);
						
						break;
					case FIBONACCI:
						// fibonacciHeap.delete(key);
						break;
					}
					break;

				case 3:

					switch (args[0]) {

					case BINARY:
						if (binaryHeap.isEmpty()) {
							System.out.println("The Priority Queue is Empty");
							break;
						}
						System.out.println("The Minimum Value(extracted) is "
								+ binaryHeap.extractMin());
						break;

					case BINOMIAL:
						if (binomialHeap.isEmpty()) {
							System.out.println("The Priority Queue is Empty");
							break;
						}

						System.out.println("The Minimum Value(extracted) is "
								+ binomialHeap.extractMin());
						break;
					case FIBONACCI:
						break;
					}
					break;
				case 4:
					switch (args[0]) {

					case BINARY:
						if (binaryHeap.isEmpty()) {
							System.out.println("The Priority Queue is Empty");
							break;
						}

						System.out.println("The Minimum Value is "
								+ binaryHeap.findMinKey() + "");

						break;
					case BINOMIAL:
						if (binomialHeap.isEmpty()) {
							System.out.println("The Priority Queue is Empty");
							break;
						}

						System.out.println("The Minimum Value is "
								+ binomialHeap.findMinKey() + "");
						break;
					case FIBONACCI:
						break;
					}
					break;
					
					
				case 5:
					double key1,
					key2;
					System.out
							.println("Enter the value of key to be updated and then its value to be updated");
					isr = new InputStreamReader(System.in);
					br = new BufferedReader(isr);
					System.out.println("Enter the key:");
					s = br.readLine();
					df = new DecimalFormat();
					n = df.parse(s);
					key1 = n.doubleValue();
					System.out.println("Enter the value to update:");
					s = br.readLine();
					df = new DecimalFormat();
					n = df.parse(s);
					key2 = n.doubleValue();
					switch (args[0]) {

					case BINARY:

						binaryHeap.updateKey(key1, key2);
						System.out.println("Values Updated");
						break;
					case BINOMIAL:
						binomialHeap.updateKey(key1, key2);
						break;

					/*
					 * case 6:
					 * System.out.println("Enter the value of key to be Incresed"
					 * ); isr = new InputStreamReader(System.in); br = new
					 * BufferedReader(isr); s = br.readLine(); df = new
					 * DecimalFormat(); n = df.parse(s); key = n.intValue();
					 * binomialHeap.increase(key); break;
					 */

					case FIBONACCI:
						break;
					}
					break;
				case 6:
					System.out.println("Enter the filename");
					isr = new InputStreamReader(System.in);
					br = new BufferedReader(isr);
					s = br.readLine();
					String fileName = s.trim();
					switch (args[0]) {

					case BINARY:
						if (fileName != null)
							binaryHeap.displayHeap(fileName);
						else
							System.out.println("Error Opening File");
						break;
						
					case BINOMIAL:

						if (fileName != null)
							binomialHeap.displayHeap(fileName);
						else
							System.out.println("Error Opening File");
						break;
					case FIBONACCI:
						break;
					}
					break;

				case 7:

					br.close();
					System.out.println("Exited Successfully");
					System.exit(0);
				case 8:
					switch (args[0]) {

					case BINARY:
						s = "in.txt";
						binaryHeap.enterFileName(s);
						break;
					case BINOMIAL:

						/*
						 * System.out.println("To Enter PQ from file give filename:"
						 * ); isr = new InputStreamReader(System.in); br = new
						 * BufferedReader(isr); s = br.readLine();
						 */
						// String inputFileName=null;
						s = "in.txt";
						binomialHeap.enterFilename(s);
						break;
					case FIBONACCI:
						break;
					}
					break;
				case 9:
					switch (args[0]) {

					case BINARY:
						break;
					case BINOMIAL:

						System.out.println("Enter the key to be find");
						s = br.readLine();
						df = new DecimalFormat();
						n = df.parse(s);
						key = n.intValue();
						binomialHeap.findN(key);
						break;
					case FIBONACCI:
						break;
					}
					break;

				default:
					System.out.println("Wrong input...Please try again...");
				}

			} catch (ParseException ex) {
				System.out.println("Wrong input...");
			} catch (IOException ex) {
				System.out.println("IOException" + ex);
			}

		}

	}
}
