// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 1
// Student: Knut Lorenzen 810326-T296

public  class Complex {
	
	private double real, im;
			
	public Complex( double real, double im ){
		
		this.real = real;
		this.im   = im;
	}
	
	public Complex() {
		
		real = 0.0;
		im = 0.0;
	}
	
	public Complex copy(){
		
		return new Complex( real, im );
	}
	
	public Complex add( Complex x ) {
		
		return new Complex( real + x.real, im + x.im );
	}
	
	public Complex add( double real ) {
		
		return new Complex( this.real + real, im );
	}
	
	public Complex multiply( Complex x ) {
		double a = real,
			   b = im,
			   c = x.real,
			   d = x.im;
		return new Complex( a*c - b*d, 
							b*c + a*d );
	}
	
	public Complex multiply( double x ) {
		
		return new Complex( real * x, 
							im * x );
	}
	
	public Complex subract( Complex x ){
		
		return new Complex( real - x.real, im - x.im );
	}
	
	
	public Complex divide( Complex x ){
		
		double a = real,
			   b = im,
			   c = x.real,
			   d = x.im;		
		return new Complex( (a*c + b*d)/(c*c + d*d),
							(b*c - a*d)/(c*c + d*d) );
	}
	
	
	public double abs(){
		
		return Math.sqrt(real*real + im*im);
	}
	
	public String toString(){
		
		return real + " + " + im + "i"; 
	}
	
}

