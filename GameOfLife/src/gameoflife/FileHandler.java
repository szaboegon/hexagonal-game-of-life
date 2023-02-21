package gameoflife;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileHandler {
	
	private FileHandler(){
		
	}
	
	public static void saveGrid(String filename, String delim, Grid g) throws IOException{
		FileOutputStream f = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(f);

		Iterator<Map.Entry<Cell,Integer>> it = g.getAliveCells().entrySet().iterator();
		while(it.hasNext()) {
			Coord coord=it.next().getKey().getCoord();
			out.writeObject(coord);
	}
		out.close();
}

	public static void loadGrid(String filename, String delim, Grid g) throws IOException, ClassNotFoundException {
		FileInputStream f =new FileInputStream(filename);
		ObjectInputStream in =new ObjectInputStream(f);
		Coord coord;
	
		g.getAliveCells().clear();
		g.getStartState().clear();
		try {
		while (true) {
			coord = (Coord)in.readObject();
			if(coord==null)
				break;
			g.getAliveCells().put(new Cell(coord),0);
			g.getStartState().put(new Cell(coord),0);
			
		}
		}catch(EOFException e) {}
			
		in.close();
}
	
	public static ArrayList<String> getSaveNames(){
		File dir = new File(".");
		List<String> list = Arrays.asList(dir.list(
		   new FilenameFilter() {
		      @Override public boolean accept(File dir, String name) {
		         return (name.endsWith(".txt"));
		      }
		   }
		));
		ArrayList<String> trimmed=new ArrayList<String>();
		for(int i=0; i<list.size(); i++) {
			trimmed.add(i,list.get(i).substring(0, list.get(i).length()-4));
		}
		
	return trimmed;
		
	}
}
