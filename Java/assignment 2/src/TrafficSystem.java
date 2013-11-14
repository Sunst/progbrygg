// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 2
// Student: Knut Lorenzen 810326-T296
/**                                                             TrafficSystem
 * <pre>
 * Defines the lanes and signals that is to be studied. Collects statistics
 *
 * Model for traffic simulation
 * ============================
 *
 *  The following classes are used:
 *
 *     Vehicle Represents a vehicle
 *             Time of arrival and destination are set when create.
 *
 *     Light   Represents the light signals
 *             See below
 *  
 *     Lane    Represents a piece of a road
 *             A lane is represented by an array where each element
 *             either is empty or contain a reference to a 
 *             vehicle-object.
 * 
 *     TrafficSystem
 *             Defines the components, ie the lanes  and signals that
 *             build the system. See below
 *
 *     Simulation
 *            main-method the controls the simulation
 *
 *
 *  The situation that is to be simulated looks schematically like
 *
 *          C           X                               E
 *   W    s1<----r1-----<---------r0---------------------
 *   S    s2<----r2-----< 
 *
 *  A lane (a piece of a road) r0 split into two files r1 and r2 at X.
 *  The signal s1 controls the lane r1 and the signal s2 the lane r2.
 * 
 *  Vehicles are create at E. The probability that a vehicle arrives to E
 *  at a certain time is called "the intensity of arrival".
 *
 *  At a time step the vehicles move one step forward  (if possible).
 *  At C, the vehicles are removed from the system if the resp signal is green.
 *  At X, vehicles are move from r0 to either r1 or r2 depending of its
 *  destination (if there are space for them).
 *
 * </pre> 
*/

public class TrafficSystem {
    
    // Attributes that describe the elements of the system
    private Lane  r0;
    private Lane  r1;
    private Lane  r2;
    private Light s1;
    private Light s2;
    
    // Various attributes for simulation parameters (intensity of arrivals
    // destinations...)
    public static final double ProbArrival = 0.4;
    public static final double ProbDestinationSouth = 0.3;
    public static final int    S1Period = 7;
    public static final int    S1Green = 3;
    public static final int    S2Period = 7;
    public static final int    S2Green = 3;
    public static final int    R0Length = 10;
    public static final int    R1R2Length = 5;
    
    // Various attributes for collection  of statistics
    private int maxTimeS1 = 0 ;
    private int vehicleCntS1 = 0;
    private double avgTimeS1 = 0.0;
    
    private int maxTimeS2 = 0 ;
    private int vehicleCntS2 = 0;
    private double avgTimeS2 = 0.0;
    
    private int timeBlocked = 0;
    
    private int time = 0;

    public TrafficSystem() {
    	
    	r0 = new Lane( R0Length );
    	r1 = new Lane( R1R2Length );
    	r2 = new Lane( R1R2Length );
    	
    	s1 = new Light( S1Period, S1Green );
    	s2 = new Light( S2Period, S2Green );
    }

    /**
     * Defines how vehicles should mod in the system.
     * Steps the system one time step using the step methods in the
     * components
     * Creates vehicles, add and remove into the different lanes.
     */
    public void step() {
    	

    	// remove cars from r1/r2 if green, record stats
    	if ( s1.isGreen() ) {
    		    		
    		Vehicle v = r1.removeFirst();
    		if ( v != null ){
        		
        		vehicleCntS1++;		
    			// max travel time
    			int traveralTime = time - v.bornTime() ; 
    			if ( maxTimeS1 < traveralTime )
    				maxTimeS1 = traveralTime;
    			
    			// avg travel time
    			avgTimeS1 = (avgTimeS1*(vehicleCntS1-1) + traveralTime)/vehicleCntS1;    			
    		}    		
    	}
    		
    	if ( s2.isGreen() ) {
    		
    		Vehicle v = r2.removeFirst();
    		if ( v != null ){
        		
        		vehicleCntS2++;		
    			// max travel time
    			int traveralTime = time - v.bornTime() ; 
    			if ( maxTimeS2 < traveralTime )
    				maxTimeS2 = traveralTime;
    			
    			// avg travel time
    			avgTimeS2 = (avgTimeS2*(vehicleCntS2-1) + traveralTime)/vehicleCntS2;    			
    		}     		    		
    	}
    		
		r1.step();
		r2.step();
		    	
    	// move the first car in r0 into r1/r2 if possible
    	Vehicle v0 = r0.getFirst();    	
    	if ( v0 != null ) {
    	
	    	if ( v0.destination() == 'S' ) {
	    		
	    		if ( r2.lastFree() ) {
	    			
	    			r2.putLast( v0 );	
	    			r0.removeFirst();
	    		}
	    		else
	    			timeBlocked++;    		
	    	}
	    	else if ( v0.destination() == 'W' ) {
	    		
	    		if ( r1.lastFree() ) {
	    			
	    			r1.putLast( v0 );
	    			r0.removeFirst();
	    		}
	    		else 
	    			timeBlocked++;
	    	}
    	}
    	
    	r0.step();
    	
    	if ( r0.lastFree() )  {
    		
    		// do we need a new car?	    			    		
    		if ( Math.random() <= ProbArrival ){
	    		// destination
	    		char dest = 'W';    		
	    		if ( Math.random() <= ProbDestinationSouth ) 			
	    			dest = 'S';
	    		r0.putLast( new Vehicle( time, dest ) );		    	
    		}
    	}
    		    	    	    	    
    	// propagate the step to the signals   
    	s1.step();
    	s2.step();
    	time++;
    }

    /**
     * Print the collected statistics so far
     */
    public void printStatistics() {
    	System.out.println( "S1\nCars passed: " + vehicleCntS1 );
    	System.out.println( "avg/max time: " + avgTimeS1 + "/" + maxTimeS1 );
    	
    	System.out.println( "S2\nCars passed: " + vehicleCntS2 );
    	System.out.println( "avg/max time: " + avgTimeS2 + "/" + maxTimeS2 );
    	
    	System.out.println( "Junction blocked: " + 100.0 * timeBlocked/time + "%" );
    	
    }

    /**
     * Prints a graphical representation of the current traffic situation
     * using the toString-methods in the components.
     */
    public void print() {
    	
    	System.out.println( s1.toString() + ":" + r1.toString() + r0.toString() );
    	System.out.println( s2.toString() + ":" + r2.toString() + "\n" );
    	
    }       
}
