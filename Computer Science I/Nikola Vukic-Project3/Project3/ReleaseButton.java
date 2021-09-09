import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ReleaseButton here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class ReleaseButton extends MyButton
{
    private RideLines line; //RideLines variable which hold value of RideLines object
    
     /**
     * Constructor for objects of class ReleaseButton.
     * 
     * @param connectedLine
     */
    public ReleaseButton(RideLines connectedLine)
    {
        //writen text on the button 
        super("Release");
        
        line= connectedLine;//relate two elements     
    }
    
    /** 
     *   called whenever this button is clicked.
     */
    public void performAction()
    {
        //if is clecked it will delete Person from the line  
        line.ReleasePerson();
    } 
}
