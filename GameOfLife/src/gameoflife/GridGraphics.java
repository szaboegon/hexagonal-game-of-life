package gameoflife;

import java.awt.*;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.*;

public class GridGraphics extends JPanel {

	  private static final long serialVersionUID = 1L;
	  private final int WIDTH = 1200;
	  private final int HEIGHT = 720;
	  private Grid grid;
	  
	  private static double zoom=1;
	  private static int screenOffsetX=0;
	  private static int screenOffsetY=0;

	  public GridGraphics(Grid g) {
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    	grid=g;
	  }
	  
	  public Grid getGrid() {
		  return grid;
	  }
	    
	  @Override
	    public void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;
	        Point origin = new Point(WIDTH / 2, HEIGHT / 2);
	        super.paintComponent(g);
	        g.setColor(new Color(0x175f7a));
	        g.fillRect(0,0,WIDTH,HEIGHT);
	        drawHexGridLoop(g2d, origin, 40, 20,grid.getAliveCells());
	        this.repaint();
	        
	    }

	  public void drawHexGridLoop(Graphics g, Point origin, int size, int radius, ConcurrentHashMap<Cell, Integer> filledhex ) {
		  
		  double OffSet=Math.sqrt(3)*radius;
		
		  int x;
		  int y;
		
		  for(Cell cell:filledhex.keySet()) {

			  x=(int)(origin.x+zoom*(cell.getCoord().getPosY()*OffSet/2+cell.getCoord().getPosX()*OffSet)+screenOffsetX);
			
			  y=(int)(origin.y+zoom*(cell.getCoord().getPosY()*1.5*radius)+screenOffsetY);
	    	
			  drawHex(g, x, y, radius,true);
		  }
		
		  for (int row = -size; row < size; row++) {
			  y=(int)(origin.y+zoom*(row*1.5*radius)+screenOffsetY%(3*radius*zoom));
	    	

			  for (int col = -size; col < size; col++) {
				  if(Math.abs(row)%2==1)
					  x=(int)(origin.x+zoom*(OffSet/2+col*OffSet)+screenOffsetX%(OffSet*zoom));
				  else 
					  x=(int)(origin.x+zoom*(col*OffSet)+screenOffsetX%(OffSet*zoom));
	    	  
				  drawHex(g, x, y, radius,false);
			  }
		  }
	    
	  }

	  private void drawHex(Graphics g,int x, int y, int r, boolean filled) {
		  Graphics2D g2d = (Graphics2D) g;

		  HexTile tile = new HexTile(x, y, r);
	    
		  if(filled) 
			  tile.draw(g2d, x, y, 0, 0x00ff92, filled);
	    
		  else
			  tile.draw(g2d, x, y, (int)(1+2*zoom), 0x747287, filled); 
		  g.setColor(new Color(0xFFFFFF));
	  }
	  
	  public void stepSimulation() {
		  try {
			  grid.updateNeighbours();
			  grid.nextGeneration();  
		  }catch(Exception e) {}
	  }
	  
	  public static double getZoom() {
		  return zoom;
	  }
	  
	  public static void setZoom(double z) {
		  zoom=z;
	  }
	  
	  public int getWidth() {
		  return WIDTH;
	  }
	  
	  public int getHeight() {
		  return HEIGHT;
	  }
	  
	  public static int getScreenOffsetX() {
		  return screenOffsetX;
	  }
	  
	  public static int getScreenOffsetY() {
		  return screenOffsetY;
	  }
	  
	  public static void setScreenOffsetX(int i) {
		  screenOffsetX=i;
	  }
	  
	  public static void setScreenOffsetY(int i) {
		  screenOffsetY=i;
	  }
	  

	}