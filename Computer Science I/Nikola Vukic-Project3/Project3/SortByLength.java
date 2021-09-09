import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SortByLength here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class SortByLength extends MyButton
{
     /**
     * Constructor for objects of class SortByLength.
     * 
     */
    public SortByLength()
    {
        //writen text on the button
        super("Sort By Queue Length");
    }
    
    /** 
     *   called whenever this button is clicked.
     */
     public void performAction()
    {
        //sort ride lines by who many people is in line
        ( (MyWorld) getWorld()).sortByLength();
    }      
}
