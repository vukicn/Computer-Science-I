import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MergeButton here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class MergeButton extends MyButton
{
     /**
     * Constructor for objects of class MergeButton.
     * 
     */
   public MergeButton()
    {
        //writen text on the button
        super("Merge");
    }
   
    /** 
     *   called whenever this button is clicked.
     */
   public void performAction()
   {
       //add more ride lines in ride park 
       ( (MyWorld) getWorld()).loadFile();
   }    
}
