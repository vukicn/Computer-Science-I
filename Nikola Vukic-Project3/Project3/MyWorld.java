
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.io.*;
import java.util.Scanner;

/**
 * Write a description of class MyWorld here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class MyWorld extends World
{
    public static final int MAX_RIDES=14;  //maximum number of rides possible
    LoadButton load;//LoadButton variable which hold value of LoadButton object
    private int currentRide; //actual number of ride lines in the world
    RideLines rides[]; //the collection of ride lines

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        //could have as many RideLines as capasity dictates
        rides = new RideLines[MAX_RIDES];

        currentRide=0; //initially, we have 0 prisoners
        
        //Create a Load button at the top of the screen
        load = new LoadButton();
        addObject(load, 250, 20);
        
        //Create a Merge button at the top of the screen
        MergeButton merge = new MergeButton();
        addObject(merge, 350, 20);
        
        //Create a Sort By Name button at the top of the screen
        SortByName sortName = new SortByName();
        addObject(sortName, 450, 20);
        
        //Create a Sort by queue length at the top of the screen
        SortByLength sortLength =  new SortByLength();
        addObject(sortLength, 600, 20);
        

    }
    
    /**
     * read from file reader and create sequence of ride lines
     */
    public void loadFile()
    {

        FileDialog fd = null; //no value
        fd = new FileDialog(fd , "Pick up a bubble file: " , FileDialog.LOAD); //create popup menu 
        fd.setVisible(true); // make visible manu
        String directory = fd.getDirectory(); // give the location of file
        String name = fd.getFile(); // give us what file is it
        String fullName = directory + name; //put together in one sting 
            
        File rideNameFile = new File(fullName); //open new file with above directory and name
            
        Scanner nameReader = null;
        //when I try to pick up it read as scanner what I choose
        try
        {
             nameReader = new Scanner(rideNameFile);
        }
        catch (FileNotFoundException fnfe)//if dont find return this message below
        {
             return; // immedaitely exit from here
        }
        
        //if load button pressed, it remove all ride lines in the world
        if(load.getFound()){
             removeAllLines();
        } 
        
        //read until is no more stings inside of file and until fullfill max rides
        while(nameReader.hasNextLine()&&currentRide<MAX_RIDES)
        {
               
             String rd= nameReader.nextLine();//hold and read string with name of ride
             RideLines nova =new RideLines(rd);
             rides[currentRide]=nova;
             
             //Create a RideLine with string given from file
             addObject(rides[currentRide++], 650, 20 + 40*currentRide);
        }
        
        //when is no more strings inside it close reader
        nameReader.close();
    }

    /** 
     * removes all RideLines objects from the world. 
     */
    public void removeAllLines()
    {
        // get list of all ride lines and remove each
        removeObjects(getObjects(RideLines.class)); //remove RideLines
        removeObjects(getObjects(AddButton.class));//remove AddButton
        removeObjects(getObjects(ReleaseButton.class));//remove ReleaseButton
        removeObjects(getObjects(Person.class));//remove Person
        currentRide=0; // reset actual rides at zero
    }

    /**
     *re-arranges the RideLines by name. 
     */
    public void sortByName()
    {
        boolean swapMade;//has a swap been made in the most recent pass?
        
        //repeat looking for swaps
        do
        {
            swapMade=false;//just starting this pass, so no swap yet
            
            //for each RideLines's index
            for(int i = 0; i<currentRide; i++)
            {
                //assume thet the smallest name is the one we start with 
                int minIndex= i;
                
                //finding the index of the(alphabetically) lowest RideLines name, 
                //k: the index to start searching for the lowest name
                for(int k= minIndex+1; k<currentRide; k++)
                {
                    //if the other RideLines has a lower name, they are the low name
                    if(rides[k].getName().compareTo(rides[minIndex].getName())<0)
                       {                         
                           // standard swap, using a temporary. swap the smallest name
                           RideLines temp = rides[k];
                           rides[k]=rides[i];
                           rides[i]=temp;
                           
                           swapMade=true; //remember this pass made at least one swap
                      }  

                }

            } 
        }while(swapMade);//until no swaps were found in the most recent past
        
        redrawLines();//redraw the image
       
    }
    
    /**
     *re-arranges the RideLines by queue length.  
     */
    public void sortByLength()
    {
        boolean swapMade;//has a swap been made in the most recent pass?
        
        //repeat looking for swaps
        do
        {
            swapMade=false;//just starting this pass, so no swap yet
            
            //go through entire array. looking for swaps that need to be done
            for(int i = 0; i<currentRide-1; i++)
            {
                //if the other RideLines has less people
                if(rides[i].getCurrentPeople()<rides[i+1].getCurrentPeople())
                {
                    // standard swap, using a temporary. swap with less people
                    RideLines temp = rides[i];
                    rides[i]=rides[i+1];
                    rides[i+1]=temp;
                    
                    swapMade=true;//remember this pass made at least one swap
                }
                
            } 
        }while(swapMade);//until no swaps were found in the most recent past
        
        redrawLines();//redraw the image
        
    }
    
    /**
     * redraw is a private method that will rebuild the image for a RideLines  
     * 
     */
     private void redrawLines()
    {
        for(int i =0; i<currentRide;i++)
        {
            rides[i].moveTogether(650,60 + 40*i);//move all elemetnts of ride lines
            
        }
    }

}      
        
