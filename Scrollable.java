import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scrollable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scrollable extends Actor
{
    GreenfootImage img=null,img_trans=null;
    private int atWorldEdge_grenze=20, real_x, real_y;
    protected ScrollableWorld space=null;
    private boolean canleave=true;
    
    public Scrollable()
    {
        
    }
    
    public Scrollable (boolean canleaveWorld)
    {
        canleave=canleaveWorld;
    }
    /**
     * Act - do whatever the Scrollable wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(real_x+space.getShiftX(),real_y+space.getShiftY());
        checkvsbl();
    }   
    /**
     * Bild übergeben, dass genutzt wird, wenn Objekt auf Bildschirm sichtbar.
     */
    public void setvisibleImg(GreenfootImage img_tmp)
    {
        img=img_tmp;
        img_trans=new GreenfootImage("void.png");
    }
    /**
     * Sichtbarkeit auf Bildschirm einstellen
     */
    public void setVisible(boolean vsbl)
    {
        if(vsbl)
        {
            setImage(img);
        }
        else
        {
            setImage(img_trans);
        }
    }
    /**
     * wenn Objekt am Rand des Feldes, wird es unsichtbar, siehe setVisible()
     */
    public void checkvsbl()
    {
        setVisible(!atWorldEdge());
    }
    /**
     * returns true, wenn weniger als atWorldEdge_grenze von Spielfeldrand entfernt
     */
    public boolean atWorldEdge()
    {
        if(getX()<atWorldEdge_grenze || getX() > getWorld().getWidth()-atWorldEdge_grenze || getY()<atWorldEdge_grenze || getY() > getWorld().getHeight()-atWorldEdge_grenze )
        {
            return true;
        }
        else
            return false;
    }
   
    /**
     * einmal kurz Startwerte für real_x und real_y übergeben , da sie nicht von Beginn an auf z.B. getX() zugreifen können
     */
    public void init(int x_start, int y_start,ScrollableWorld space_tmp)
    {
        real_x=x_start;
        real_y=y_start;
        space=space_tmp;
        
        
    }
    public ScrollableWorld getScrWorld()
    {
        return space;
    }
    
    public int getRealX()
    {
        return real_x;
    }
    public int getRealY()
    {
        return real_y;
    }
    /**
     * Move von Actor überschrieben bzw erweitert, damit man die echte position ( nicht nur die auf dem Bildschirm) bekommt
     * 
     */
    public void move(int distance)
    {
        int movex=0, movey=0,dx, dy;
        dx= getX();
        dy= getY();
        super.move(distance);
        dx= getX()-dx;
        dy= getY()-dy;
        real_x+=dx;
        real_y+=dy;
            
        if(canleave)
        {
            //real_x+=dx;
            //real_y+=dy;
            //System.out.println("canleave");
        }
        else
        {
            
            while(real_x+space.getShiftX()+movex<0)
            {
                movex++;
                //System.out.println("movex vergröernt");
            }
            while(real_x+space.getShiftX()+movex>getWorld().getWidth())
            {
                movex--;
                //System.out.println("movex verkleinert");
            }
            while(real_y+space.getShiftY()+movey<0)
            {
                movey++;
                //System.out.println("movey vergröernt");
            }
            while(real_y+space.getShiftY()+movey>getWorld().getHeight())
            {
                movey--;
                //System.out.println("movey verkleinert");
            }
            real_x=real_x+movex;
            real_y=real_y+movey;
            
            
        }
        setLocation(real_x+space.getShiftX()+movex,real_y+space.getShiftY()+movey);
        
        //System.out.println(toString()+"   x:" +getX()+":"+(real_x+space.getShiftX())+":"+real_x+"   y:"+getY()+":"+real_y);
        
        
    }
}
