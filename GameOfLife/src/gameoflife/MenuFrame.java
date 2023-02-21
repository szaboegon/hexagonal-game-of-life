package gameoflife;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MenuFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GridGraphics graphics;
	private Timer timer;
	private int gameSpeed=300;
	private boolean timerIsRunning;
	
	private JButton startb;
	private JButton stopb;
	private JButton nextb;
	private JButton resetb;
	private JButton clearb;
	private JButton saveb;
	private JButton loadb;
	private JButton applyb;
	private JSlider speeds;
	private JTextField rulef;
	private JComboBox<String> loadbox;
	private JTextField savef;
	private ArrayList<String> saveNames=new ArrayList<String>();
	
	public void initComponents(){
		this.setLayout(new BorderLayout());
		JPanel game=new GridGraphics(graphics.getGrid());
		JPanel menu=new JPanel();
		menu.setLayout(new FlowLayout());
		menu.setPreferredSize(new Dimension(1200,70));
		
		startb=new JButton("Start");
		stopb=new JButton("Stop");
		nextb=new JButton("Next");
		resetb=new JButton("Reset");
		clearb=new JButton("Clear");
		saveb=new JButton("Save");
		loadb=new JButton("Load");
		applyb=new JButton("Apply");
		
		speeds=new JSlider(JSlider.HORIZONTAL,0,100,50);
		
		JLabel speedl=new JLabel("Speed:");
		rulef=new JTextField();
		rulef.setText("3/23");
		rulef.setPreferredSize(new Dimension(120,25));
		JLabel rulel=new JLabel("Change rule (Born/Survive):");
		
		
		loadbox=new JComboBox<String>();
		saveNames=FileHandler.getSaveNames();
		for(int i=0; i<saveNames.size(); i++)
		loadbox.addItem(saveNames.get(i));
		loadbox.setPreferredSize(new Dimension(120,25));
		JLabel loadl=new JLabel("Select save to load:");
		
		savef=new JTextField();
		savef.setPreferredSize(new Dimension(120,25));
		JLabel savel=new JLabel("Save name:");
		
		menu.add(Box.createRigidArea(new Dimension(120,10)));
		menu.add(startb);
		menu.add(stopb);
		menu.add(nextb);
		menu.add(resetb);
		menu.add(clearb);
		menu.add(savel);
		menu.add(savef);
		menu.add(saveb);
		menu.add(loadl);
		menu.add(loadbox);
		menu.add(loadb);
		menu.add(Box.createRigidArea(new Dimension(120,10)));
		menu.add(speedl);
		menu.add(speeds);
		menu.add(rulel);
		menu.add(rulef);
		menu.add(applyb);
		
		startb.addActionListener(this);
		stopb.addActionListener(this);
		nextb.addActionListener(this);
		resetb.addActionListener(this);
		clearb.addActionListener(this);
		saveb.addActionListener(this);
		loadb.addActionListener(this);
		
		speeds.addChangeListener(new SliderListener());
		applyb.addActionListener(this);
		
		ML ml=new ML();
		addMouseListener(ml);
		addMouseMotionListener(ml);
		addMouseWheelListener(ml);
		
		
		add(game,BorderLayout.NORTH);
		add(menu,BorderLayout.SOUTH);
	}
	
	public MenuFrame(GridGraphics g) {
		graphics=g;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Game of Life");
		setSize(graphics.getWidth(),graphics.getHeight()+120);
		setResizable(false);
		initComponents();
	}
	
	
	public class ML extends MouseInputAdapter {
		
		private Point prevPos=new Point(graphics.getWidth()/2,graphics.getHeight()/2);
		private boolean drag=false;
		private boolean draw=false;
		private int lastCoordX=0;
		private int lastCoordY=0;

		@Override
		public void mouseDragged(MouseEvent e) {
			if(drag) {		
				int dx=(int) (e.getX()-prevPos.getX());
				int dy=(int) (e.getY()-prevPos.getY());
			
				prevPos=e.getPoint();
			
				GridGraphics.setScreenOffsetX(dx+GridGraphics.getScreenOffsetX());
				GridGraphics.setScreenOffsetY(dy+GridGraphics.getScreenOffsetY());
				
				graphics.repaint();
			}
			
			if(draw) {
				double OffSet=Math.sqrt(3)*20;
				
				int x;
				int y;
				
				double tmpx;
				double tmpy;
				
				tmpy=(e.getY()-(graphics.getHeight()/2)-GridGraphics.getScreenOffsetY())/(30*GridGraphics.getZoom());    
				
				tmpx=(e.getX()-(graphics.getWidth()/2)-GridGraphics.getScreenOffsetX())/(OffSet*GridGraphics.getZoom())-tmpy/2;
				
				y=(int)Math.round(tmpy-1.06/GridGraphics.getZoom());
				x=(int)Math.round(tmpx+0.34/GridGraphics.getZoom());
				
				if(x!=lastCoordX || y!=lastCoordY) {
		    	Coord c=new Coord(x,y,-x-y);
		    	Cell cell=new Cell(c);
		    	
		    		if(graphics.getGrid().getAliveCells().containsKey(cell)) {
		    			graphics.getGrid().getAliveCells().remove(cell);
		    			if(!timerIsRunning)
		    				graphics.getGrid().getStartState().remove(cell);
		    		}
		    		else {
		    			graphics.getGrid().getAliveCells().put(new Cell(c),0);
		    			if(!timerIsRunning)
		    				graphics.getGrid().getStartState().put(new Cell(c),0);
		    		}
				}
		    	
		    	lastCoordX=x;
				lastCoordY=y;
		    	graphics.repaint();
			}
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton()!=MouseEvent.BUTTON1) {
				if(e.getButton()==MouseEvent.BUTTON3) {
					prevPos=e.getPoint();
					drag=true;
				}
				return;
			}	
			draw=true;	                    
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			double OffSet=Math.sqrt(3)*20;
			
			int x;
			int y;
			
			double tmpx;
			double tmpy;
			
			tmpy=(e.getY()-(graphics.getHeight()/2)-GridGraphics.getScreenOffsetY())/(1.5*20*GridGraphics.getZoom());    
			
			tmpx=(e.getX()-(graphics.getWidth()/2)-GridGraphics.getScreenOffsetX())/(OffSet*GridGraphics.getZoom())-tmpy/2;
			
			y=(int)Math.round(tmpy-1.06/GridGraphics.getZoom());
			x=(int)Math.round(tmpx+0.32/GridGraphics.getZoom());
			
	    	Coord c=new Coord(x,y,-x-y);
	    	Cell cell=new Cell(c);
	    	
	    	if(graphics.getGrid().getAliveCells().containsKey(cell)) {
    			graphics.getGrid().getAliveCells().remove(cell);
    			if(!timerIsRunning)
    				graphics.getGrid().getStartState().remove(cell);
    		}
    		else {
    			graphics.getGrid().getAliveCells().put(new Cell(c),0);
    			if(!timerIsRunning)
    				graphics.getGrid().getStartState().put(new Cell(c),0);
    		}
			
	    	graphics.repaint();	
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			drag=false;
			draw=false;
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getWheelRotation()<0 && GridGraphics.getZoom()<4 ) {
				GridGraphics.setZoom(GridGraphics.getZoom()*2);
				GridGraphics.setScreenOffsetX(GridGraphics.getScreenOffsetX()*2);
				GridGraphics.setScreenOffsetY(GridGraphics.getScreenOffsetY()*2);
			}
					
			
			if(e.getWheelRotation()>0 && GridGraphics.getZoom()>0.5) {
				GridGraphics.setZoom(GridGraphics.getZoom()/2);
				GridGraphics.setScreenOffsetX(GridGraphics.getScreenOffsetX()/2);
				GridGraphics.setScreenOffsetY(GridGraphics.getScreenOffsetY()/2);
			}		
		}	
		
	}
	
	public class SliderListener implements ChangeListener {
		
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	        	setGameSpeed(1200-speeds.getValue()*11);
	        	if(timerIsRunning==true) {
	        		timerStop();
	        		timerStart();
	        	}
	        }    
	    }
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==startb && timerIsRunning==false)
			timerStart();
		else if(e.getSource()==stopb && timerIsRunning==true)
			timerStop();
		else if(e.getSource()==nextb && timerIsRunning==false)
			graphics.stepSimulation();
		else if(e.getSource()==resetb) {
			if(timerIsRunning==true)
				timerStop();
			graphics.getGrid().setStartState();
		}else if(e.getSource()==clearb) {
			graphics.getGrid().getStartState().clear();
			graphics.getGrid().getAliveCells().clear();
			
		}else if(e.getSource()==applyb) {
			try {
				if(rulef.getText().contains("/")){
					String[] tmp=rulef.getText().split("/");
					graphics.getGrid().setRule(new EvolutionRule(tmp[0],tmp[1]));
				}
				else {
					JFrame popup = new JFrame();
					JOptionPane.showMessageDialog(popup, "Rule must contain a '/' character.");
				}
			}catch(Exception e1){
				JFrame popup = new JFrame();
				JOptionPane.showMessageDialog(popup, "Invalid rule.");
			}
		}else if(e.getSource()==saveb) {
			try {
				if(savef.getText().isBlank()) {
					JFrame popup = new JFrame();
					JOptionPane.showMessageDialog(popup, "Invalid name.");
				}
				else {
					loadbox.removeItem(savef.getText());
					FileHandler.saveGrid(savef.getText()+".txt",",",graphics.getGrid());
					loadbox.addItem(savef.getText());
					loadbox.setSelectedItem(savef.getText());
				}
			} catch (IOException e1) {e1.printStackTrace();}
		}else if(e.getSource()==loadb && loadbox.getSelectedItem()!=null) {
			try {
				FileHandler.loadGrid(loadbox.getSelectedItem()+".txt",",",graphics.getGrid());
			}catch (IOException e1) {e1.printStackTrace();} catch (ClassNotFoundException e2) {e2.printStackTrace();}
			
		}
				
	}
	
	public void timerStart() {
		timerIsRunning=true;
		timer=new Timer();
		TimerTask task=new TimerTask(){
    
			public void run() {
				graphics.stepSimulation();	
			}
		};
     
		timer.scheduleAtFixedRate(task,0,gameSpeed);
	}

	public void timerStop() {
		timer.cancel();
		timerIsRunning=false;
		
	}
	
	public void setGameSpeed(int s) {
		gameSpeed=s;
	}
	

}
