package gameoflife;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import java.awt.Point;

public class HexTileTest {
	HexTile hex;
	
	@Before
	public void setup() {
		hex=new HexTile(0,0,10);
	}
	
	@Test
	 public void findPointTest() {
		Point res=new Point(10,0);
		double angle=0;
		Assert.assertEquals(res,hex.findPoint(angle));
		
		res=new Point(-10,0);
		angle=Math.PI;
		Assert.assertEquals(res,hex.findPoint(angle));
		
	}
	
	@Test
	 public void updatePointsTest() {
		hex.updatePoints();
		Point[] res= {
				new Point(-9,5),
				new Point(-9,-5),
				new Point(0,-10),
				new Point(9,-5),
				new Point(9,5),
				new Point(0,10)
		};
		
		Assert.assertArrayEquals(res, hex.getPoints());
	}
}
