package gameoflife;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileHandlerTest {
	Cell c1;
	Cell c2;
	Cell c3;
	EvolutionRule r;
	Grid g;
	
	@Before
	public void setup() throws IOException {
		r=new EvolutionRule("","");
		g=new Grid(r);
		c1=new Cell(0,0,0);
		c2=new Cell(0,3,-3);
		c3=new Cell(1,0,-1);
		
		g.getStartState().put(c1, 0);
		g.getStartState().put(c2, 0);
		g.getStartState().put(c3, 0);
		
		g.getAliveCells().put(c1, 0);
		g.getAliveCells().put(c2, 0);
		g.getAliveCells().put(c3, 0);
		
		FileHandler.saveGrid("test.txt",",",g);
		
	}
	
	@Test
	public void loadGridTest1() throws ClassNotFoundException, IOException {
		Grid g2=new Grid(r);
		FileHandler.loadGrid("test.txt",",", g2);
		
		Assert.assertArrayEquals(g.getAliveCells().keySet().toArray(), g2.getAliveCells().keySet().toArray());
		
		Assert.assertArrayEquals(g.getStartState().keySet().toArray(), g2.getStartState().keySet().toArray());
	}
	
	@Test(expected=IOException.class)
	public void loadGridTest2() throws ClassNotFoundException, IOException {
		Grid g2=new Grid(r);
		FileHandler.loadGrid("nincsilyen.txt",",", g2);
		
	}
	
}
