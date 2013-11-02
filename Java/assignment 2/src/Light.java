/**                                                              Light
 * Represents a traffic signal
 */
public class Light {
    private int period;
    private int green;
    private int time;

    public Light(int p, int g) {}

    /**
     * Steps the clock of the signal
     */
    public void step() {}

    /**
     * @return true if the signal is green otherwise false
     */
    public boolean isGreen() {return true;}

    /**
     * @return A String-representation of the signal that shows its color
     */
    public String  toString()  {return null;}	
}
