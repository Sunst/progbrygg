import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class LightTest {

	@Test
	public void test() {
		
		Light l = new Light( 3, 2 );
		Assert.assertTrue( l.isGreen() );
		l.step();
		Assert.assertTrue( l.isGreen() );
		l.step();
		Assert.assertFalse( l.isGreen() );
		l.step();
		Assert.assertTrue( l.isGreen() );
		l.step();
		Assert.assertTrue( l.isGreen() );
		l.step();
		Assert.assertFalse( l.isGreen() );		
	}

}
