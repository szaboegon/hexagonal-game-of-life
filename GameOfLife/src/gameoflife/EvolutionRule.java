package gameoflife;

public class EvolutionRule{
	
	private boolean[][] rules;
	
	EvolutionRule(String born, String survive){
		rules=new boolean[2][7];
		
		for (int i=0; i<rules[0].length; i++) {
			
			for(int j=0; j<born.length(); j++)
				if(i==Character.getNumericValue(born.charAt(j)))
					rules[0][i]=true;
			
			for(int k=0; k<survive.length(); k++)
				if(i==Character.getNumericValue(survive.charAt(k)))
					rules[1][i]=true;
		}
	}
	
	public boolean canBeBorn(int neighbours)  {
		return rules[0][neighbours];
	}
	
	public boolean canStayAlive(int neighbours) {
		return rules[1][neighbours];
	}
}
