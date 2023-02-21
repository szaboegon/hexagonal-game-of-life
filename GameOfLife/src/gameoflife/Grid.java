package gameoflife;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Grid {
	
	private ConcurrentHashMap<Cell, Integer> aliveCells;
	private ConcurrentHashMap<Coord, Integer> neighbours;
	private EvolutionRule rule;
	private ConcurrentHashMap<Cell, Integer> startState;
	 
	Grid(EvolutionRule r){
		rule=r;
		aliveCells=new ConcurrentHashMap<Cell, Integer>();
		neighbours=new ConcurrentHashMap<Coord, Integer>();
		startState=new ConcurrentHashMap<Cell, Integer>();
	}
	
	public EvolutionRule getRule(){
		return rule;
	}
	
	public void setRule(EvolutionRule r) {
		rule=r;
	}
	
	public void updateNeighbours() {
		neighbours=new ConcurrentHashMap<Coord, Integer>();
		
		Iterator<Map.Entry<Cell,Integer>> it1 = aliveCells.entrySet().iterator();
		while(it1.hasNext()) {
			Cell cell=it1.next().getKey();
			Coord tmp[]= cell.getCoord().getNeighbours();
			aliveCells.replace(cell, getAliveNeighbours(cell.getCoord()));
				for(int i=0; i<tmp.length;i++) {
						neighbours.put(tmp[i], getAliveNeighbours(tmp[i]));
					}		
		}
		
	}
	
	public int getAliveNeighbours(Coord c) {
		int count=0;
		Iterator<Map.Entry<Cell,Integer>> it = aliveCells.entrySet().iterator();
		while(it.hasNext()) {
			if(c.areNeighbours( it.next().getKey().getCoord())) {
				count++;
			}
		}
		return count;
	}
	
	public void nextGeneration()throws NullPointerException {	
		
		ConcurrentHashMap<Cell, Integer> tmp=new ConcurrentHashMap<Cell, Integer>();

		Iterator<Map.Entry<Cell,Integer>> it1 = aliveCells.entrySet().iterator();
			while(it1.hasNext()) {
			Cell cell=it1.next().getKey();
			if(rule.canStayAlive(aliveCells.get(cell))==true) {
				tmp.put(cell,0);
			}
			
		}
		
		Iterator<Map.Entry<Coord,Integer>> it2 = neighbours.entrySet().iterator();
		while(it2.hasNext()) {
		Coord c= it2.next().getKey();
		Cell cell=new Cell(c);
		if(rule.canBeBorn(neighbours.get(c)) && aliveCells.get(cell)==null) {
					tmp.put(cell,0);
				}
		}
			
		aliveCells=tmp;
		
	}
	
	
	public ConcurrentHashMap<Cell, Integer> getAliveCells() {
		return aliveCells;
	}
	
	public ConcurrentHashMap<Cell, Integer> getStartState() {
		return startState;
	}
	
	public ConcurrentHashMap<Coord, Integer> getNeighbours() {
		return neighbours;
	}
	
	public void setStartState() {
		aliveCells.clear();
		neighbours.clear();
		aliveCells.putAll(startState);
	}
	
	
}
