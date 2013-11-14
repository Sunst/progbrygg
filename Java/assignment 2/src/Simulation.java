// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 2
// Student: Knut Lorenzen 810326-T296
/**                                                              Simulation
 * Run a  simulation
 */
public class Simulation {

    /**
     * Create a TrafficSystem, steps it and calls the print methods
     */
    public static void main(String [] args) {
	TrafficSystem tf = new TrafficSystem();
	//...
        while (true) {
        	
        	try { // If the printouts are done each timestep, a pause is needed
        		
        		Thread.sleep(100);
        		
        	} catch (InterruptedException e) { }
        	
        	tf.step();
        	tf.print();
        	tf.printStatistics();
        }
	///...
    }
}
