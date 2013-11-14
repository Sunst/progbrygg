// Uppsala University
// Department Of IT
// Programming bridging course Autumn 2013
// Java assignment 2
// Student: Knut Lorenzen 810326-T296
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
		Assert.assertEquals( "[  W]", lane.toString() );
		
		lane.step();
		Assert.assertEquals( "[ W ]", lane.toString() );		
		lane.step();
		Assert.assertEquals( "[W  ]", lane.toString() );		
		Assert.assertTrue( lane.lastFree() );
		Assert.assertSame( v1, lane.getFirst() );
		
		Vehicle v2 = new Vehicle( 2, 'S' );
		lane.putLast(v2);
		Assert.assertEquals( "[W S]", lane.toString() );
		lane.step();
		Assert.assertTrue( lane.lastFree() );
		Assert.assertEquals( "[WS ]", lane.toString() );		
		Assert.assertSame( v1, lane.getFirst() );
		lane.step();
		Assert.assertTrue( lane.lastFree() );
		Assert.assertEquals( "[WS ]", lane.toString() );		
		Assert.assertSame( v1, lane.getFirst() );
		
		Assert.assertSame( v1, lane.removeFirst() );
		Assert.assertNull( lane.getFirst() );
		
		lane.step();
		Assert.assertEquals( "[S  ]", lane.toString() );
		Assert.assertSame( v2, lane.getFirst() );
		
	}

}
