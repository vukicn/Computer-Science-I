import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * The World for CSC14400 Project#1 (Spring 2018)
 * 
 * @author Nikola Vukic
 * @version 1/2018
 */
public class MyWorld extends World
{
    private Scoreboard score; ////Scoreboard variable which hold value of Scoreboard object
    /**
     * Constructor for objects of class MyWorld.
     * 
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        
        //Create a Scoreboard centered at the top of the screen
        score = new Scoreboard();
        this.addObject(score, getWidth()/2, 0); 
        
        //Create a Person at the bottom left of the World.
        Person person = new Person(); 
        this.addObject(person, 50, getHeight()-20); 
        
        //Create a Spider at the top of the World at random x location, just below the Scoreboard.
        Spider spider = new Spider(); 
        this.addObject(spider ,Greenfoot.getRandomNumber(788)+1,score.getHeight()+1);
        
        
        
       
    }
    
    /**
     * returns the Scoreboard object.
     * 
     * @return Scoreboard.
     */
    public Scoreboard getScoreboard()
    {
        return score; 
    }
    
    /**
     * returns the number of spiders found in the World.
     * 
     * @return number of spiders found. 
     */
    public int getSpiderCount()
    {
        // get list of all spiders and return count found
        //   inside of that list.
        return getObjects(Spider.class).size();
    }
    
    /** 
     * removes all Spider objects from the world. 
     */
    public void removeAllSpiders()
    {
        // get list of all spiders and remove each
        //   spider inside of that list
        removeObjects(getObjects(Spider.class));
    }
 
    /** 
     * removes all Trap objects from the world. 
     */
    public void removeAllTraps()
    {
        // get list of all spiders and remove each
        //   spider inside of that list
        removeObjects(getObjects(Trap.class));
    }
    
   
    /**
     * yes! a World can have an act() method, too. 
     * It might just check to see if a key has been
     * pressed, etc...
     */
    public void act()
    {
       /**
        * If user pressed "n" and if Person' health "0", new game will be created.
        */
       if(Greenfoot.isKeyDown("n") && score.getHealth()==0)
       {
           
           
          //Removing all of the spiders in the world. 
          removeAllSpiders();
               
          //Removing all of the traps in the world
          removeAllTraps();
               
          //Resetting the Scoreboard’s score to 0, health to 100, and number of traps left to 3
          score.setScore(0);  //score
          score.setHealth(100);  //health
          score.setTrapsLeft(3);  //traps
              
          //Create a Person to the bottom left of the World.
          Person person = new Person();
          this.addObject(person, 150, getHeight()-20); 
              
          //Create a Spider at the top of the World at random x location, just below the Scoreboard.
          Spider spider = new Spider();
          this.addObject(spider,Greenfoot.getRandomNumber(788)+1,score.getHeight()+1);
               
          //Overwrite any "Gave Over!" message with blank spaces
          showText("          " ,getWidth()/2,getHeight()/2);
                             
            
          
       }
       
    }
    
}
