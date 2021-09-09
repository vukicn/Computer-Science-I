import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/** 
 * The Bubble class should move,
 * and if hit another Bubble,
 * and if there are same color,
 * it should delete that interesecting bubble,
 * and interesecting bubble bubbles and self 
 * 
 * @author Nikola Vukic 
 * @version 4/2018
 */
public class Bubble extends Actor
{
    private Color col; // bubble color (make sure to initialize this!)
   
    //  a constant indicating the intended radius of a bubble
    public static final int RADIUS = 10;
    
    //AngleMeter variable which hold value of AngleMeter object
    private AngleMeter am;
           
    private GreenfootImage img; //hold image value
    
    private String color; //hold color name given from reader 
    
    private boolean hit;  // check did hit or not
    
    private double speedX;  // speed in the x direction
    private double speedY;  // speed in the y direction
      
    private double betterX; // more accurate x location 
    private double betterY; // more accurate y location 
    
    private boolean stuck; //check is stuck or not
    
    
    public Bubble(double speedX, double speedY )
    {
        stuck = false;  //set value that now is not stuck or move 
        hit = false; // set that didnt hit 
        redrawImage();  //redraw the image
        
        this.speedX = speedX; //update value of speed in x direction
        this.speedY = speedY; //update value of speed in y direction
       
    }
    
     /**
     * Constructor for objects of class Bubble.
     * 
     * By default, a bubble has a that is stuck and given color.  
     * 
     */
    public Bubble()
    {
        stuck = true; //no move
        redrawImage();  //redraw the image
    }
   
    /**
     * Constructor for objects of class Bubble.
     * 
     * By default, a bubble has a that is stuck and get color from reader,
     * and compare with sting which hold some value.  
     * 
     */
    public Bubble(String color)
    {
        stuck = true; //no move
        //build a image 
        img = new GreenfootImage(2*RADIUS, 2*RADIUS); 
        this.color = color; // take value form reader and stor in variable color
        String green = "green";  
        String blue = "blue";
        String red =  "red";
        String yellow = "yellow";       
        
        //compare sting from reader and of given sting right above and
        //give specific Color if they are equal
        if(color.equalsIgnoreCase(blue))
        {
               col=Color.BLUE;
        }
        if(color.equalsIgnoreCase(green))
        {
               col=Color.GREEN;
        }
        if(color.equalsIgnoreCase(red))
        {
               col=Color.RED;
        }
        if(color.equalsIgnoreCase(yellow))
        {
               col=Color.YELLOW;
        }
        
        //take color and fill oval with that color
        img.setColor(col);
        img.fillOval(0,0, 2*RADIUS, 2*RADIUS);
        
        // use the image just build to represent this object
        setImage(img);
    }
   
   
    /**
     * after a Bubble is added to world, this method is
     * automatically called. In this case, update x direction and y direction;
     */
    protected void addedToWorld(World w)
    {
        betterX = getX(); //new x 
        betterY = getY(); //new y 
    }
   
    /**
     * redraw is a private method that will rebuild the image for a Bubble  
     * 
     */
    private void redrawImage()
    {
        //create new image
        img = new GreenfootImage(2*RADIUS, 2*RADIUS);
        
        //get random number from 0 till 3
        int num = Greenfoot.getRandomNumber(4);
        
        //check random number and then take color from that number
        if(num == 0)
        {
            col=Color.BLUE;       
        }else if (num==1)
        {
            col=Color.GREEN;
        }else if (num==2)
        {
            col=Color.RED;
        }else if (num==3)
        {
            col=Color.YELLOW;
        }
        //draw image in color given above
        img.setColor(col);
        img.fillOval(0,0, 2*RADIUS, 2*RADIUS);
        
        //build image
        setImage(img);     
    }
     
    /**
     * removes all bubbles that are touching "this" ball that match
     * the specified color. Also removes "this" ball.
     * 
     * Although you may modify this if you wish to, doing so
     * is HIGHLY inadvisable. 
     * 
     * @param targetColor the color to look for when removing bubbles
     */
    public void removeAdjacent(Color targetColor)
    {
        // make sure we haven't aready been removed ...
        if (getWorld()==null)
            return;
            
        // get ALL bubbles we are actually touching
        List<Bubble> touching = getObjectsInRange(2*Bubble.RADIUS,Bubble.class);
        
        // remove this bubble ...
        getWorld().removeObject(this);
        
        // look at each bubble we were touching ...
        for(Bubble b: touching)
        {
            // if the current bubble matches our color ...
            if (b.col.equals(targetColor))
                // ... repeat this process on the matching colored bubble
                b.removeAdjacent(targetColor);
        }
    }
   
    
    
    /**
     * Act - do whatever the Bubble wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    { 
      
        //if bubble moving, go into this or (is not stuck)
        if(!stuck)
        {
            
            //Call My world class to can access to AngleMeter created there
            MyWorld w = (MyWorld)this.getWorld();
            
            //Call AngleMeter method with holding Angle value
            am = w.getAngleMeter();
             
            //Call a Bubble method
            Bubble bub = new Bubble(0,0);
             
            //check is pressed "space" and wait until hit another to allow to press "space" again
            if(Greenfoot.isKeyDown("space") && !hit)
            {
                speedX = Math.cos(am.getAngle()); //calcualte the initial speed of the bubble in x direction
                speedY =-Math.sin(am.getAngle()); //calcualte the initial speed of the bubble in y direction
                hit=true; //change when hit
            }
            
            //Check is touching top of the world and remove that object
            if (getY() == 0)
            {
                //Create a Bubble centeered at the bottom of the screen
                w.addObject(bub,  getWorld().getWidth()/2, getWorld().getHeight()-5);
                getWorld().removeObject(this); //remove this object
                return; //immediately quit from here
            }
            
            //check is touching left or right side of world and bounce
            if (getX() == 0 || getX() == getWorld().getWidth()-1)
            {
                 this.speedX= -speedX; // negated x direction
            }
                
          
            betterX += speedX; //update x speed direction
            betterY += speedY; //update y speed direction
           
             
            setLocation((int)betterX, (int)betterY);//update at new location 
            
            //get list with all objects in bubble class in some specific range      
            List<Bubble> bumpedInto = getObjectsInRange(2*RADIUS, Bubble.class);
       
            int countBubble=0; //count Boubble in range
            
            boolean hited = false; //chech is hited for crating new bubble
            
            //go through all object in range and do same for every, depends what is in loop
            for(Bubble oneBubble : bumpedInto)
            { 
               stuck=true; //now is stuck
               hited=true; //it will only do this if has object in range, so that means that hit
               
               //check is any bubble from that list has same color
               if(oneBubble.col.equals(col))
               {
                   countBubble++; //increase how many bubble are with same color
                   
                   //now create list of that interesecting bubble with same color  and do same as first one
                   List<Bubble> bumped = oneBubble.getObjectsInRange(2*RADIUS, Bubble.class);
                   
                   for(Bubble one : bumped)
                   {
                       //check is any of new bubble same color
                       if(one.col.equals(col))
                       {
                           countBubble++; //increase how many bubble are with same color
                   
                            
                       }
                   }
                   
               }
                   
                  
            }
            
            //check if hit bubble then create new bubble centered at the bottom
            if (hited)
             {
                 w.addObject(bub,  getWorld().getWidth()/2, getWorld().getHeight()-5);
             }
            
            //check is total 3 bubble touching 
            if(countBubble>=3)
             {
                  //removes all bubbles that are touching "this" ball that match
                  //the specified color. Also removes "this" ball.
                  removeAdjacent(col);
             }
               
            
        }
       
    }
       
        
    
}
    
