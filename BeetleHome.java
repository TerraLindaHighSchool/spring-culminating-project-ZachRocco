import greenfoot.*;
 
/**
 * A hill full of beetles.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class BeetleHome extends AntHill
{
    /** Number of beetles that have come out so far. */
    private int beetles = 0;
    
    /** Total number of beetles in this hill. */
    private int maxBeetles = 2;
    
    /** Counter to show how much food have been collected so far. */
    private Counter foodCounter;
    
    /**
     * Constructor for beetle home with default number of beetles (1).
     */
    public BeetleHome()
    {
    }

    /**
     * Construct an beetle hill with a given number of beetles.
     */
    public BeetleHome(int numberOfBeetles)
    {
        maxBeetles = numberOfBeetles;
    }

    /**
     * Act: If there are still beetles left inside, see whether one should come out.
     */
    public void act()
    {
        if(beetles < maxBeetles) 
        {
            if(Greenfoot.getRandomNumber(100) < 1) 
            {
                getWorld().addObject(new PredatorBeetle(this), getX(), getY());
                beetles++;
            }
        }
    }

    /**
     * Record that we have collected another bit of food.
     */
    public void countFood()
    {
        if(foodCounter == null) 
        {
            foodCounter = new Counter("Food: ");
            int x = getX();
            int y = getY() + getImage().getWidth()/2 + 8;

            getWorld().addObject(foodCounter, x, y);
        }        
        foodCounter.increment();
    }
}