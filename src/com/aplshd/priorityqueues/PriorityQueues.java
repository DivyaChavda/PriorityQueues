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
	
	public static void main(String args[]) {
		switch(args[0]) {
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
		
		while(true) {
			System.out.println("1.Insert\n2.Delete\n3.ExtractMin\n4.FindMin\n5.Decrease"
					+ "\n6.Increase\n7.DisplayHeap\n8.Exit\nEnter your choice:");
			try {
				InputStreamReader isr = new InputStreamReader(System.in);
		        BufferedReader br = new BufferedReader(isr);
		        String s = br.readLine();
		        DecimalFormat df = new DecimalFormat();
		        Number n = df.parse(s);
		        int menuInput = n.intValue();
		        
		        switch(menuInput) {
		        	case 1:
		        		break;
		        		
		        	case 2:
		        		break;
		        		
		        	case 3:
		        		break;
		        		
		        	case 4:
		        		break;
		        		
		        	case 5:
		        		break;
		        		
		        	case 6:
		        		break;
		        		
		        	case 7:
		        		break;
		        		
		        	case 8:
		        		System.exit(0);
		        		
		        	default:
		        		System.out.println("Wrong input...Please try again...");
		        }
				
			} catch (ParseException ex) {
		    	System.out.println("Wrong input...");
		    } catch (IOException ex) {
		    	System.out.println("IOException");
		    }
	        
		}
		
		
	}
}
