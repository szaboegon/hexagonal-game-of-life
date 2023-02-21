package gameoflife;

public class GameOfLife  {

	public static void main(String[] args) {
		EvolutionRule rule=new EvolutionRule("3","23");
		
		Grid grid=new Grid(rule);
		GridGraphics g = new GridGraphics(grid);	
		
		MenuFrame f=new MenuFrame(g);
		f.setVisible(true);	 
	}
}
