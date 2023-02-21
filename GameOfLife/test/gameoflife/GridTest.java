package gameoflife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridTest {
	Grid g;
	Cell c1;
	Cell c2;
	Cell c3;
	
	@Before
	public void setup() {
	EvolutionRule rule=new EvolutionRule("4","123");
	g=new Grid(rule);
	c1=new Cell(0,0,0);
	c2=new Cell(1,0,-1);
	c3=new Cell(4,1,-5);
	g.getAliveCells().put(c1, 0);
	g.getAliveCells().put(c2, 0);
	g.getAliveCells().put(c3, 0);
	}
	
	@Test
	public void updateNeighboursTest() {
		g.updateNeighbours();
		
		Coord[] res= {
				new Coord(0, 1, -1),
				new Coord(0, -1, 1),
				new Coord(1, 0, -1),
				new Coord(-1, 0, 1),
				new Coord(-1, 1, 0),
				new Coord(1, -1, 0),
				new Coord(1, 1, -2),
				new Coord(1, -1, 0),
				new Coord(2, 0, -2),
				new Coord(0, 0, 0),
				new Coord(0, 1, -1),
				new Coord(2, -1, -1),
				new Coord(4, 2, -6),
				new Coord(4, 0, -4),
				new Coord(5, 1, -6),
				new Coord(3, 1, -4),
				new Coord(3, 2, -5),
				new Coord(5, 0, -5)
		};

		for(int i=0; i<res.length;i++) {
			Assert.assertTrue(g.getNeighbours().containsKey(res[i]));
		}
			
		
		int i=g.getAliveCells().get(c1);
		Assert.assertEquals(1,i);
		
		i=g.getAliveCells().get(c2);
		Assert.assertEquals(1,i);
		
		i=g.getAliveCells().get(c3);
		Assert.assertEquals(0,i);
	}
	
	@Test
	public void nextGenerationTest() {
		g.updateNeighbours();
		g.nextGeneration();
		
		Cell[] alive= {c1,c2};
		
		Assert.assertArrayEquals(alive, g.getAliveCells().keySet().toArray());
				
	}
	
	@Test
	public void setStartStateTest() {
		g.getStartState().put(c1, 0);
		g.getStartState().put(c3, 0);
		g.setStartState();
		
		Cell[] res= {c1,c3};
		
		Assert.assertArrayEquals(res, g.getAliveCells().keySet().toArray());
				
	}
	
}
