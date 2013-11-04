import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class LaneTest {

	@Test
	public void test() {
		
		Lane lane = new Lane( 3 );
		Assert.assertTrue( lane.lastFree() );
		
		Vehicle v1 = new Vehicle(0, 'W');
		lane.putLast(v1);
		Assert.assertFalse( lane.lastFree() );
		Assert.assertNull( lane.getFirst() );
		Assert.assertNull( lane.removeFirst() );
		
		lane.step();
		lane.step();
		Assert.assertTrue( lane.lastFree() );		
		Assert.assertSame( v1, lane.getFirst() );		
		Assert.assertSame( v1, lane.removeFirst() );
		Assert.assertNull( lane.getFirst() );
	}

}
