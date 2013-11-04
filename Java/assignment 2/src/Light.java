/**                                                              Light
 * Represents a traffic signal
 */
public class Light {
    private int period;
    private int green;
    private int time;

    public Light(int p, int g) {
    	
    	period = p;
    	green = g;
    	time = 0;
    }

    /**
     * Steps the clock of the signal
     */
    public void step() {
    	    	
    	if ( time + 1 < period )
    		time++;
    	else
    		time = 0;
    }

    /**
     * @return true if the signal is green otherwise false
     */
    public boolean isGreen() {
    	
    	return time < green;
    }

    /**
     * @return A String-representation of the signal that shows its color
     */
    public String  toString()  {return this.isGreen() ? "G" : "R" ;}	
}
