import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList ;
import java.awt.Point ;
/**
 * Das WEltall. Jetzt mit SCrollen . TODO muss auf kollisionen prüfen und dann dodamge o.Ä.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space extends ScrollableWorld
{
    //private ArrayList<greenfoot.Actor> objects = new ArrayList<greenfoot.Actor>();
    private ArrayList<Scrollable> objects = new ArrayList<Scrollable>();
    //private ArrayList<Point> realPos = new ArrayList<Point>();
    private int grenze = 100,width=2400,height=1600,shiftX=0,shiftY=0;
    
    /**
     * Constructor for objects of class Space.
     * 
     */
    public Space()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1,2400,1600); 

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        Rocket rocket = new Rocket();
        initObj(rocket, getWidth()/2, getHeight()/2);
        Asteroid asteroid2 = new Asteroid();
        initObj(asteroid2, 708, 640);
        Asteroid asteroid3 = new Asteroid();
        initObj(asteroid3, 1055, 636);
        Asteroid asteroid4 = new Asteroid();
        initObj(asteroid4, 904, 493);
        Asteroid asteroid5 = new Asteroid();
        initObj(asteroid5, 1019, 423);
        initObj(new Asteroid(),1600, 900);
        Astronaute astronaute = new Astronaute();
        initObj(astronaute, 650, 238);
        Astronaute astronaute2 = new Astronaute();
        initObj(astronaute2, 192, 449);
        Astronaute astronaute3 = new Astronaute();
        initObj(astronaute3, 898, 720);
    }
}
