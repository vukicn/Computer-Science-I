import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SortByName here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class SortByName extends MyButton
{
     /**
     * Constructor for objects of class SortByName.
     * 
     */
    public SortByName()
    {
        //writen text on the button
        super("Sort By Name");
    }
    
    /** 
     *   called whenever this button is clicked.
     */
     public void performAction()
    {
        //sort ride lines by names
        ( (MyWorld) getWorld()).sortByName();
    }     
}
