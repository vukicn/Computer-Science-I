import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.io.*;
import java.util.Scanner;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.Color;
/**
 * Write a description of class LoadButton here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class LoadButton extends MyButton
{
    private boolean reLoad; //check is clicked load button, if yes it will remove ride lines
    
     /**
     * Constructor for objects of class LoadButton.
     * 
     */
    public LoadButton()
    {
        //writen text on the button
        super("Load");     
    }
    
    /** 
     *   called whenever this button is clicked.
     */
     public void performAction()
    {
        //because load button clicked it should be true to activate to delete all lines
        reLoad = true;  
        //add lines in ride park
        ( (MyWorld) getWorld()).loadFile();
        //when is done with load file it should turn at false
        reLoad = false; 
    }
    
    /**
     * returns the true of false should delete all ridlines
     * 
     * @return should delete all ride lines in the world. 
     */
    public boolean getFound()
    {
        return reLoad; 
    }
          
        
    
}