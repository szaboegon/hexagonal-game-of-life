package gameoflife;

public class Cell {
	
	private Coord co;
	
	public Cell(Coord c){
		co=c;
	}
	
	public Cell(int x, int y, int z) {
		co=new Coord(x,y,z);
	}
	
	public Coord getCoord() {
		return co;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Cell c = (Cell) o;
        return this.co.equals(c.co); 
          
    }
	
	public int hashCode() {
	    return co.getPosX() * 433 + co.getPosY()*431+co.getPosZ();
	}
	
}
