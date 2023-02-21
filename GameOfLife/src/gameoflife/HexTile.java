package gameoflife;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;


public class HexTile extends Polygon  {
	
	private static final long serialVersionUID = 1L;

    private static final int SIDES = 6;
    private static final int rotation = 90;

    private Point[] points = new Point[SIDES];
    private Point center = new Point(0, 0);
    private int radius;
    

    public HexTile(int x, int y, int radius) {
    	 npoints = SIDES;
         xpoints = new int[SIDES];
         ypoints = new int[SIDES];

         this.center=new Point(x,y);
         this.radius=radius;
         updatePoints();
    }
    
    public Point[] getPoints() {
    	return points;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        updatePoints();
    }

    public void setCenter(int x, int y) {
    	this.center=new Point(x,y);
    	updatePoints();
    }

    protected Point findPoint(double angle) {
        int x = (int) Math.round(center.x + Math.cos(angle) * GridGraphics.getZoom()*radius);
        int y = (int) Math.round(center.y + Math.sin(angle) * GridGraphics.getZoom()*radius);

        return new Point(x, y);
    }

    protected void updatePoints() {
    	double angle=Math.toRadians(rotation);
        for (int p = 0; p < SIDES; p++) {
        	angle=angle+Math.toRadians(360/SIDES);
            Point point = findPoint(angle);
            xpoints[p] = point.x;
            ypoints[p] = point.y;
            points[p] = point;
        }
    }

    public void draw(Graphics2D g, int x, int y, int lineThickness, int colorValue, boolean filled) {
        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

        if (filled)
            g.fillPolygon(xpoints, ypoints, npoints);
        else
            g.drawPolygon(xpoints, ypoints, npoints);        
    }
}
