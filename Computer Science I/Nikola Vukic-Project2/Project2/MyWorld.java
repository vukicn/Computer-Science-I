import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.io.*;
import java.util.Scanner;
/**
 * The World for CSC14400 Project#2 (Spring 2018)
 * 
 * @author Nikola Vukic
 * @version 4/2018
 */
public class MyWorld extends World
{
     private AngleMeter am; //AngleMeter variable which hold value of AngleMeter object
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
      
        // Create a new world with 400x650 cells with a cell size of 1x1 pixels.
        super(400, 650, 1); 
        
        //Create a AngleMeter centeered at the bottom of the screen
        am = new AngleMeter(50);
        this.addObject(am, getWidth()/2, getHeight()-25);
        
        
        int x = 20; //stating x cordinate of first bubble
        int y = 20; //stating y cordinate of first bubble
      //Create a alligned bubbles by 6 row,  containing 19 boubbles in one row 
        for(int j = 0; j<6; j++)
        {
             for(int i=0; i<19; i++)
             {
                //Each time when is excuted it add new bubble and update x cordinate by 20
                Bubble nextBubble = new Bubble();
                addObject(nextBubble, x , y);
                x+=20; 
             }
             // reset x cordinate to start in same line, but this time also update y cordinate by 20 to step in next row
             x=20; 
             y+=20;
        }
        
       //Create a Bubble centeered at the bottom of the screen
        Bubble lopta = new Bubble(0,0);
        this.addObject(lopta, getWidth()/2, getHeight()-5);
      
    }
    
    /** 
     * removes all Bubbles objects from the world. 
     */
    public void removeAllBubbles()
    {
        // get list of all bubbles and remove each
        // bubble inside of that list
        removeObjects(getObjects(Bubble.class));
    }
    
    /**
     * return the AngleMeter object.
     * 
     * @return AngleMeter.
     */
    public AngleMeter getAngleMeter()
    {
        return am;
    }
    
    public void act()
    {
        /**
        * If user pressed "L" , pop up menu will created.
        */
        if(Greenfoot.isKeyDown("L"))
        {
            //Removing all Bubbles objects from the world.
            removeAllBubbles();
            
            
            
            FileDialog fd = null; //no value
            fd = new FileDialog(fd , "Pick up a bubble file: " , FileDialog.LOAD); //create popup menu 
            fd.setVisible(true); // make visible manu
            String directory = fd.getDirectory(); // give the location of file
            String name = fd.getFile(); // give us what file is it
            String fullName = directory + name; //put together in one sting 
        
            File bubbleFile = new File(fullName); //open new file with above directory and name
        
            Scanner bubbleReader = null;
            //when I try to pick up it read as scanner what I choose
            try
            {
                bubbleReader = new Scanner(bubbleFile);
            }
            catch (FileNotFoundException fnfe)//if dont find return this message below
            {
                System.out.println("Could not open file! " + fullName);
                return; // immedaitely exit from here
            }   
            
            //read until is no more stings inside of file
            while(bubbleReader.hasNext())
            {
                String col;  //hold string with name of color
                int xPos, yPos; // hold x and y coordinates
            
            
                col = bubbleReader.next(); //read color from file
                xPos = bubbleReader.nextInt(); //read x position from file
                yPos = bubbleReader.nextInt(); //read y position from file
            
            
                //createa Bubble with color given from file and x and y codinated also from file
                Bubble nextBubble = new Bubble(col);
                addObject(nextBubble, xPos, yPos);
            }
            //when is no more strings inside it close reader
            bubbleReader.close();
            
            //Create a Bubble centeered at the bottom of the screen
            Bubble lopta = new Bubble(0,0);
            this.addObject(lopta, getWidth()/2, getHeight()-5);
       }
        
    
    
    // YOU WILL LIKELY WANT TO ADD MORE METHODS HERE. 
    // DON'T FORGET THAT A World CAN HAVE AN act() METHOD. 
    
    }
}