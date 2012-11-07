import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList ;
/**
 * Write a description of class ScrollablerWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollableWorld extends World
{
//private ArrayList<greenfoot.Actor> objects = new ArrayList<greenfoot.Actor>();
    private ArrayList<Scrollable> objects = new ArrayList<Scrollable>();
    //private ArrayList<Point> realPos = new ArrayList<Point>();
    private int grenzeX = 599,grenzeY=399,width=2400,height=1600,shiftX=0,shiftY=0;
    
    /**
     * Constructor for objects of class Space.
     * 
     */
    public ScrollableWorld(int screenWidth, int screenHeight, int cellsize, int width, int height)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(screenWidth, screenHeight, cellsize,false); 
        this.width=width;
        this.height=height;
           
    }
    /**
     * @param scrble Objekt einer Subklasse von Scrollable, das hinzugefügt wird.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public void initObj(Scrollable scrble, int x,int y)
    {
        addObject(scrble, x, y);
        objects.add(scrble);
        scrble.init(x-shiftX,y-shiftY,this);
        //realPos.add(new Point(x,y));
    }
    /**
     * Act-Methode der World, achtet auf die Position der Rakete bzw des Ersten Object in Objects ( objects.get(0)) und verschieb alle Objekte dementsprechend.
     * 
     */
    public void act()
    {
        //System.out.println(objects.get(objects.size()-1).toString());
        int dx=0,dy=0;
        if(objects.get(0).getX()<grenzeX && objects.get(0).getX()<= objects.get(0).getRealX())
        {
            dx= grenzeX - objects.get(0).getX() ;
            //System.out.println("am linken rand und srollbar");
            if(shiftX>0)// das hier sollte niemals kommen :D
            {
                System.out.println(shiftX+"  bei If 1");
            }
        }
        if( objects.get(0).getX()>getWidth()-grenzeX && getWidth()-objects.get(0).getX()<= width-objects.get(0).getRealX())
        {
            dx= getWidth()-(grenzeX+objects.get(0).getX());
        }
        if(objects.get(0).getY()<grenzeY && objects.get(0).getY()<= objects.get(0).getRealY())
        {
            dy= grenzeY - objects.get(0).getY()  ;
        }
        if(objects.get(0).getY()>getHeight()-grenzeY && getHeight()-objects.get(0).getY()<= height-objects.get(0).getRealY())
        {
            dy= getHeight()-(grenzeY+objects.get(0).getY());
        }
        //System.out.println("dx: "+dx+"    dy: "+dy);
        shiftX+=dx;
        shiftY+=dy;
        for(int i =0; i< objects.size();i++)
        {
            //realPos.get(i).setX();
            
            Actor tmp=objects.get(i);
            tmp.setLocation(tmp.getX()+dx,tmp.getY()+dy);
            //tmp.setRealLocation(tmp.getRealX()+dx,tmp.getRealY()+dy);
        }
        
    }
    /**
     * Beim entfernen zu nutzen, amsonsten gibs Exception, weil beim Scrollen auf den Actor zugegriffen wird obwohl er nicht mehr in der Welt ist.
     * 
     */
    public void removeObject(Scrollable scrble)
    {
        super.removeObject(scrble);
        objects.remove(scrble);
    }
    /**
     * 
     */
    public int getRealWidth()
    {
        return width;
    }
    public int getRealHeight()
    {
        return height;
    }
    public int getShiftX()
    {
        return shiftX;
    }
    public int getShiftY()
    {
        return shiftY;
    }
}

