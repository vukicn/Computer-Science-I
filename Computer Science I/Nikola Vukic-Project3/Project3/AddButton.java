import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner;
/**
 * Write a description of class AddButton here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class AddButton extends MyButton
{
    private RideLines line;//RideLines variable which hold value of RideLines object
    private Person peer;//Person variable which hold value of Person object
    
     /**
     * Constructor for objects of class AddButton.
     * 
     * @param connectedLine - connect AddButton with RideLines
     */
     public AddButton(RideLines connectedLine)
    {
        //writen text on the button 
        super("Add");
        
        line = connectedLine;//relate two elements
    }
    
    /** 
     *   called whenever this button is clicked.
     */
    public void performAction()
    {
        //if is clecked it will create new Person in the line  
        line.addPerson();
    }  
    
    //move Person at new location
    public void movePerson(int newY)
    {
        peer.setLocation(peer.getX(), newY);
    }
}
