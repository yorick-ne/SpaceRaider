import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse Asteroid. Objekte dieser Klasse schweben ziellos durchs Weltall und können Rakete
 * beschädigen.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 04-11-2012
 */
public class Asteroid extends Scrollable
{
    /**
     * Definiert, ob die Rackette und die Asteroide gerade in Kollision sind.
     */
    private boolean colliding = false;
    //private boolean playSound=false;
    private GreenfootImage img=null;
    private int real_x,real_y;
    /**
     * Konstruktor. Erzeugt ein Objekt, das sich in ein eigene Richtung bewegt.
     */
    public Asteroid()
    {
        img=getImage();
        setvisibleImg(img);
        turn(Greenfoot.getRandomNumber(360));
    }
    /**
     * Act - do whatever the Asteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        super.act();
        move();
        checkKollision();
    }
    
    /**
     * Überprüft, ob Asteroiden mit irgendeine Rackette, Kugel oder andere Asteroide
     * kollidiert und handelt entsprechend.
     */
    private void checkKollision()
    {
        // Fall Kollision mit Rackette : Rackette bekommt Schaden.
        if(getOneIntersectingObject(Rocket.class) != null)
        {
            Rocket rocket = (Rocket) getOneIntersectingObject(Rocket.class);
            if(!colliding)
            {
                rocket.receiveDamage();
                rocket.collisionCounter();
            }
            colliding = true;
        }
        else
        {
            colliding = false;
            // Fall Kollision mit Kugel : Asteroid wird gelöcht.
            if(getOneIntersectingObject(Bullet.class) != null)
            {
                getWorld().removeObject((Scrollable)getOneIntersectingObject(Bullet.class));
                getWorld().removeObject((Scrollable)this);
            }
            else
            {
                // Fall Kollision mit andere Asteroide : diese Asteroid nimm eine andere Richtung.
                if(getOneIntersectingObject(Asteroid.class) != null)
                {
                   turn(13);
                }
            }
        }
        
        
        
       
    }
    /**
     * Bewegt den Asteroiden und dreht ihn, wenn er am Rand des Spielfeldes ist.
     */
    private void move()
    {
        move(2);
        if (checkWorld())
        {
            turn(10);
        }
     }

    /**
     * Diese Methode erlaubt, die Richtung des Asteroides zu ändern wenn er sich
     * auf eine Ecke befindet (Siehe Methode move()).
     */
    private boolean checkWorld()
    {
        if(getRealX() < 5 || getRealX() > getWorld().getWidth() - 5)
        {
            return true;
        }
        if(getRealY() < 5 || getRealY() > getWorld().getHeight() - 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
