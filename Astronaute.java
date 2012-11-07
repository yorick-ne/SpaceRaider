import greenfoot.*;

/**
 * Die Astronaut Klasse. Objekte dieser Klasse schweben ziellos durchs Weltall.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 04-11-2012
 */
public class Astronaute extends Scrollable
{
    /**
     * Definiert die Geschwindigkeit des Astronautes.
     * > 0 : der Astronaut bewegt sich nach rechts
     * < 0 : der Astronaut bewegt sich nach links
     * ==0 : der Astronaut bewegt sicht nicht
     */
    private int speed = 1;

    /**
     * Konstruktor ohne Argumente. Die Geschwindigkeit der Astronaut wird auf 1 gestellt.
     */
    public Astronaute()
    {
        speed = 1;
    }
    
    /**
     * Konstruktor, das ein  Astronaut Objekt herstellt mit einer gewünschtete Geschwindigkeit.
     */
    public Astronaute(int init_speed)
    {
        speed = init_speed;
    }

    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     */
    public void act() 
    {
        super.act();
        move();
        checkCollision();
    }
    
    /**
     * Diese Methode überprüft, ob das Astronaut-Objekt eine Kollision mit eine Rakete-Objekt hat.
     */
    private void checkCollision()
    {
        if( getOneIntersectingObject(Rocket.class) != null)
        {
            Rocket rocket = (Rocket) getOneIntersectingObject(Rocket.class);
            // die Nummer des gerettetes Astronautes wird mit 1 addiert.
            rocket.incrementSavedPeople();
            /* der Astronaut ist gerettet : Er befindet sich jetzt in die Rackette; deshalb muss
             * man den löschen um den unsichtbar zu machen. */
            getWorld().removeObject((Scrollable)this);
        }
    }
    
    /**
     * Diese Methode erlaubt, die Richtung des Astronautes zu ändern wenn er sich
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
    
    /**
     * Diese Methode bewegt den Astronaut mit der Gescwindigkeit von dem Attribut 
     * "speed" im Weltall.
     */
    private void move()
    {
        move(speed);
          
        if (checkWorld())
        {
            turn(13);
        }
     }
}