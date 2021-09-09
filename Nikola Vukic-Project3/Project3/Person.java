import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class Person extends Actor
{   
    private RideLines line; //RideLines variable which hold value of RideLines object
    private int time; //count calls to act 

    /**
     * Constructor to build a class Person using specified label
     * 
     * @param connected- which connect RideLines with Person
     */
     public Person(RideLines connected)
    {
        time=0;//initially, time start from zero
        line = connected;//connected RideLines and Person  
    }
   
    //move persons to new location
    public void movePerson(int yLoc)
    {        
        setLocation(getX(),yLoc);
    }
    
    //move persons
    public void movePersonX()
    {
        setLocation(getX()+22,getY());
    }
    
    /**
     * returns the number of act.
     * 
     * @return number act happened. 
     */
    private int showTime()
    {
        return time;
    }
    
     /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
        if (Greenfoot.mouseClicked(this))
        {
            //represent the numbers of call since person has been waiting in this line
            ToolTip tt = new ToolTip(""+showTime()/100);
            getWorld().addObject(tt, getX(), getY());
        }
        
        time++; //increase calls by 1
    }
}
