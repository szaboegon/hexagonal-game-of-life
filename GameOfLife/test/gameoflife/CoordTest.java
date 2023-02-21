package gameoflife;
import org.junit.Assert;
import org.junit.Test;

public class CoordTest {
	
	@Test 
	public void equalsTest1(){
		Coord c1=new Coord(1,0,5);
		Coord c2=new Coord(1,0,5);
		
		Assert.assertEquals(c1,c2);
	}
	
	@Test 
	public void equalsTest2(){
		Coord c1=new Coord(0,0,0);
		Coord c2=new Coord(0,0,1);
		
		Assert.assertNotEquals(c1,c2);
	}
	
	@Test
	public void areNeighboursTest() {
		Coord c1=new Coord(1,2,-3);
		Coord c2=new Coord(1,3,-4);
		
		Assert.assertTrue(c1.areNeighbours(c2));
	}
	
	@Test
	public void getNeighboursTest() {
		Coord c1=new Coord(1,2,3);
		Coord result[]= {
				new Coord(1,3,2),
				new Coord(1,1,4),
				new Coord(2,2,2),
				new Coord(0,2,4),
				new Coord(0,3,3),
				new Coord(2,1,3)
				};
		
		Assert.assertArrayEquals(result, c1.getNeighbours());
	}
	
}
