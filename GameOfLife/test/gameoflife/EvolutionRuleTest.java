package gameoflife;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EvolutionRuleTest {
	EvolutionRule rule;
	int born;
	int survive;
	
	public EvolutionRuleTest(int a, int b) {
		born=a;
		survive=b;
	}
	
	@Before
	public void setup() {
		rule=new EvolutionRule("234","156");
	}
	
	@Test
	public void canBeBornTest1() {
		Assert.assertTrue(rule.canBeBorn(born));
		
	}
	
	@Test
	public void canBeBornTest2() {
		Assert.assertFalse(rule.canBeBorn(6));
		
	}
	
	@Test 
	public void canStayAliveTest1() {
		Assert.assertTrue(rule.canStayAlive(survive));
		
	}
	
	@Test 
	public void canStayAliveTest2() {
		Assert.assertFalse(rule.canStayAlive(2));
		
	}
	
	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] {2, 5});
		params.add(new Object[] {3, 6});
		params.add(new Object[] {4, 1});
		
		return params;
		}
}
