import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Person class. a A person can:
 * 
 *  1) move left or right based on user keypresses
 *  2) lay a trap to kill spiders
 *  3) have their own health reduced by touching a spider
 *  4) Have their own score increased by not touching a spider
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Actor
{   
    private int reloadTime;  // The minimum delay between make next trap.
    private Scoreboard score;  //Scoreboard variable which hold value of Scoreboard object
            
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Person()
    {
        reloadTime = 1000; // time to reload is now 1000
        
    }
    
    public void act() 
    { 
       //Call My world class to can access to Scoreboard created there
       MyWorld world = (MyWorld)getWorld();
       
       //Call Scoreboard method with holding scoreborad value
       Scoreboard score = world.getScoreboard(); 
       
       //Check if user press left narrow on keyboard, person will move left by 1 pixel
       if(Greenfoot.isKeyDown("left"))
       {
           move(-1);
       }
       
       //Check if user press right narrow on keyboard, person will move right by 1 pixel
       if(Greenfoot.isKeyDown("right"))
       {
           move(1);
       }
       
       //Check if user press "space" and trap left is more than "0" and reload time more or equal to 1000
       //it will create new trap at current Person location and reset reload time to "0"
       if(Greenfoot.isKeyDown("space") && score.getTrapsLeft()>0 && reloadTime>=1000)
       {
           //create trap at the current person's location 
           getWorld().addObject(new Trap(), getX(), getY());
           
           //decrease traps left in Scoreboard by 1
           score.decTrapsLeft(); 
           
           //reset reload time to "O"
           reloadTime=0; 
        
       }
       
       reloadTime++; //increae relaod time by 1
       
       //Check if new score higher than high score
       if (score.getScore()>score.getHighScore())
       {
           //if new score is higher than high score it will score new high score
           score.setHighScore(score.getScore());
       }
       
       //Check if person touching spider
       if(isTouching(Spider.class))
       {
          //when they are touching it decrease Person's health by 1 
          score.decHealth();
          
          //Check if Person's health is less then 1
          if(score.getHealth()<1)
           {
             //when is helth less that 1 it will show in the middle of screen "Gave Over!"
             ((World)getWorld()).showText("Game Over!",getWorld().getWidth()/2,getWorld().getHeight()/2);
             
             //it will also remove person 
             getWorld().removeObject(this);
            }
            
       }//if person do not touch spider it will increase score by one, and increasing until touch 
       else if (!isTouching(Spider.class))
       {
           //Increase score on Scoreboard
           score.incScore();
       }
          
    } 
    
 
}
