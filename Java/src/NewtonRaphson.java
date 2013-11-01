// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 1
// Student: Knut Lorenzen 810326-T296

public abstract class NewtonRaphson {

	public abstract Complex f( Complex x );
	public abstract Complex f_prim( Complex x );
	
	public Complex run( double epsilon, Complex x0 ){
		
		Complex xi1 = x0.copy(),
				xi = null;
		
		do {
			xi = xi1.copy();
			xi1 = xi.subract( f( xi ).divide( f_prim( xi ) ) );
			
		} while ( Math.abs( xi1.abs() - xi.abs() ) > epsilon );
		
		return xi1;
	}
}
