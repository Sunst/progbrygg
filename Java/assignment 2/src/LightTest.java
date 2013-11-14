// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 2
// Student: Knut Lorenzen 810326-T296
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
