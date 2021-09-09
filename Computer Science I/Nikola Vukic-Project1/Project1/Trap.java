import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Trap class should remove the first five spiders that
 * touch it, and then remove itself from the World.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trap extends Actor
{
    private int spiderRemovedCount;//count how many spiders removed
    
    public Trap()
        {
            spiderRemovedCount=0; //set walue that is "0" spiders removed
        }       
    /**
     * Act - do whatever the Trap wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {  
        //Check if Trap touching Spider
        if(isTouching(Spider.class))
        {
            //Check if in the world are more than 1 spider
            if(((MyWorld)getWorld()).getSpiderCount()>1)
            {
              //detect intersecting object
              Actor actor = getOneIntersectingObject(Spider.class);
              
              //remove that intersecting object
              getWorld().removeObject(actor);
              
              //add one spider as removed
              spiderRemovedCount++;
            }
        }
        
        //Chech if is removed more that 5 spider 
        if(spiderRemovedCount>5)
        {
            //if is removed more that 5 it remove trap from the world
            getWorld().removeObject(this);
        }
    }    
}
