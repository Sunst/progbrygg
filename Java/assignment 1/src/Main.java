// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 1
// Student: Knut Lorenzen 810326-T296

import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		double eps, r, i;
		
	    Scanner sc = new Scanner( System.in );
	    System.out.println( "epsilon:" );	    
	    eps = sc.nextDouble();
	    System.out.println( "initial state (real):" );
	    r = sc.nextDouble();	    
	    System.out.println( "initial state (imaginary):" );
	    i = sc.nextDouble();

	    NewtonRaphsonSub nr = new NewtonRaphsonSub();
	    Complex res = nr.run(eps, new Complex( r, i ) );
	    
	    System.out.println( "result: " + res );
	}

}
