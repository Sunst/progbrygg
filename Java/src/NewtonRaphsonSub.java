// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 1
// Student: Knut Lorenzen 810326-T296

public class NewtonRaphsonSub extends NewtonRaphson {

	@Override
	public Complex f( Complex x ) {
 
		return x.multiply( x ).add( x.multiply( 2 ) ).add( 2 );
	}

	@Override
	public Complex f_prim( Complex x ) {

		return x.multiply( 2 ).add( 2 );
	}

}
