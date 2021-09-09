import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Scanner; 
/**
 * Write a description of class RideLines here.
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class RideLines extends Actor
{
   public static final int MAX_PEOPLE=20; //maximum number of people possible
   
   private String name; //People's name
   private AddButton addBtn;//AddButton variable which hold value of AddButton object
   private Person pr;//Person variable which hold value of Person object
   private ReleaseButton relBtn;//ReleaseButton variable which hold value of ReleaseButton object
   
   private Person[] peer;//the collection of persons
   private int currentPeople; //actual number of peopel in the ride line
    
   private int x; //x location 

   /**
     * constructor to build a RideLines using specified label
     * 
     * @param ime - the string to place as the RideNames. 
     */
   public RideLines(String ime)
   {
       this.name= ime; //initalize instance variable
       
       // could have as many People as capacity dictates
       peer = new Person[MAX_PEOPLE];
       
       // initially, we have 0 people
       currentPeople = 0 ;
       
       //draw  image with string with Ride lines names
       drawString(name);
       
       x=1;//initially, because of mutiplication is 1
       
   }
   
   /**
     * accessor for person name
     * @return The person's name
     */
   public String getName()
   {
       return name;
   }
   
   //method to draw  image with string with Ride lines names
   private void drawString(String ime)
   {
       // Blue foreground, white background
       GreenfootImage img = new GreenfootImage(ime, 20, Color.BLUE, Color.WHITE);
       
       // use the image we have built
       setImage(img);
   }
   
   /**
     * after a RideLines is added to world, this method is
     * automatically called. In this case, we add AddButton and ReleaseButton
     */
   protected void addedToWorld(World w)
   {
       //Create a AddButton
       addBtn = new AddButton(this);
       getWorld().addObject(addBtn,getX() - 120, getY()); 
       
       //Create a ReleaseButton 
       relBtn = new ReleaseButton(this);
       getWorld().addObject(relBtn,getX() - 165, getY());
   }
   
   //method move addbutton and release button when we change location of ridelines
   //all of them will have same y location as ride line
   public void moveTogether(int newX, int newY)
   {
       //set new location of ride line
       setLocation(newX, newY);
       //set new location of add button according to ride line
       addBtn.setLocation(addBtn.getX(), newY);
       //set new location of release button according to ride line
       relBtn.setLocation(relBtn.getX(), newY);
       
       //set new location of people according to ride line 
       for(int i =0; i<currentPeople;i++)
       {
            peer[i].movePerson(newY);//move Person at new location            
       }
            
   }
   
   //method adds another Person to ride line
   public void addPerson()
   {
       // make sure we have enough room to add the person.
       if(currentPeople<peer.length)
       {
           //   add the Person to the end of the collection, and
           //   increment the actual number of person (after 
           //   adding the requested person)
           Person pr =new Person(this);
           peer[currentPeople++]= pr ;
           
           // add the new People to the ride line. 
           getWorld().addObject(pr,getX() - 185-22*x, getY()); 
           x++;//move x location at left
       }
   }
   
   //method removes the Person at the  front of the line
   //and other move up one position 
   public void ReleasePerson()
   {
       //to make sure that have people
       if(currentPeople>0)
       {
        //remove person at the front  
        getWorld().removeObject(peer[0]);
        
        //one less people now
        currentPeople--; 
        //look at all people 
        for(int i =1; i<=currentPeople;i++)
        {
               // move all "lower" pieces up by one position
               peer[i-1] = peer[i];
 
        }
        x--;//move x location at right
        
        //it will rebuild the image for a Person
        for(int i=0; i< currentPeople;i++)
        {
            //change location of Persons
            peer[i].movePersonX();
        }
       }
   }
   
   /**
     * returns the number of people found in the RideLines.
     * 
     * @return number of people found. 
     */
   public int getCurrentPeople()
   {
       //get list of all people in the rideline and return cound found
       return currentPeople; 
   }
    
  
}
