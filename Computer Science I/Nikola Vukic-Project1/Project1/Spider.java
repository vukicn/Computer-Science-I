import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * A Spider simply moves up and down in the world.
 * 
 * After being placed into the world, the spider should
 * not move for a random anount of time between 25 and
 * 375 calls to it's act method. 
 * 
 * When this spider comes back to top of the world, add a 
 * new spider
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spider extends Actor
{
    private int waitTime; //  time to wait 
    
    /**
     * Constructor for objects of class Spider.
     * 
     * By default, spider will be turn faced down by 90 degree from original position. 
     * And when is created to wait random number between 25 and 375 before start
     */
    public Spider()
    {
        //turn face down by 90 degree
        turn(90); 
        
        //Give random number to wait between 25 ad 375
        waitTime = Greenfoot.getRandomNumber(350)+25; // 
    }
    
    /**
     * Act - do whatever the Spider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       //Execute until a given random number above become "0"
       if (waitTime>0)
        {
           waitTime--;
           return;
        }
       
       //Move spider by 1 pixel
       move(1);
       
       //Check if Spider touch edge of world and if touch it will turn 180 degree
       if(isAtEdge())
       {
           turn(180);
       }
       
       //Sheck if Spider touch Scoreboard and if touch it will turn 180 degree 
       //and create new Spider at random x location below Scoreboard
       if(isTouching(Scoreboard.class))
       {
           turn(180);
           Spider spider = new Spider();
           getWorld().addObject(spider,Greenfoot.getRandomNumber(788)+1,41+1);
                   
       } 
       
       
       
    }    
}
