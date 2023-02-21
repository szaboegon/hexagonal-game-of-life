package gameoflife;

import java.io.Serializable;

public class Coord implements Serializable {
	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private int posZ;
	
	Coord(int x,int y,int z){
		posX=x;
		posY=y;
		posZ=z;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getPosZ() {
		return posZ;
	}
	
	public void setPosX(int x) {
		posX=x;
	}
	
	public void setPosY(int y) {
		posY=y;
	}
	
	public void setPosZ(int z) {
		posZ=z;
	}
	
	public boolean areNeighbours(Coord c) {
		if(posX==c.posX) 
			if((posY-c.posY==1 &&posZ-c.posZ==-1) || (posY-c.posY==-1 &&posZ-c.posZ==1) ) 
				return true;
			
		
		if(posY==c.posY) 
			if((posX-c.posX==1 && posZ-c.posZ==-1) || (posX-c.posX==-1 && posZ-c.posZ==1) ) 
				return true;
			
		
		if(posZ==c.posZ) 
			if((posY-c.posY==1 &&posX-c.posX==-1) || (posY-c.posY==-1 &&posX-c.posX==1) ) 
				return true;
			
		return false;
	}
	
	public Coord[] getNeighbours() {
		Coord result[]= {
		new Coord(posX,posY+1,posZ-1),
		new Coord(posX,posY-1,posZ+1),
		new Coord(posX+1,posY,posZ-1),
		new Coord(posX-1,posY,posZ+1),
		new Coord(posX-1,posY+1,posZ),
		new Coord(posX+1,posY-1,posZ)
		};
		
		return result;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Coord c = (Coord) o;
        return this.getPosX() == c.getPosX() 
          && (getPosY()==c.getPosY()) 
          && (getPosZ()==c.getPosZ());
    }
	
	public int hashCode() {
	    return getPosX() * 421 + getPosY()*419+getPosZ();
	}
}
