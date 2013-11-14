// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 2
// Student: Knut Lorenzen 810326-T296
/**                                                            Vehicle
 * Representerar ett fordon
 */
public class Vehicle {

    private int bornTime;
    private char destination;  

    // Konstruktor och get-metoder
    //
    public Vehicle( int bornTime, char destination ){
    	
    	this.bornTime = bornTime;
    	this.destination = destination;
    }

    public int bornTime() {
    	
    	return bornTime;
    }
    
    public char destination() {
    	
    	return destination;
    }
    
    public String toString() {
    	
    	return "" + destination;
    }	
}
