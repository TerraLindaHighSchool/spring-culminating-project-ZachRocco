import greenfoot.*;
 
/**
 * A hill full of ants.
 * 
 * @author Michael Kölling
 * @version 1.1
 */
public class AntHill extends Actor
{
    /** Number of ants that have come out so far. */
    private int ants = 0;
    private int antWarriors = 0;
    
    /** Total number of ants in this hill. */
    private int maxAnts = 40;
    private int maxAntWarriors = 10;
    
    /** Counter to show how much food have been collected so far. */
    public Counter foodCounter;
    public Counter antCounter;
    
    private String antHillName;
    
    /**
     * Constructor for ant hill with default number of ants (40).
     */
    public AntHill()
    {
        antHillName = "no name given";
    }
    
    /**
     * Constructor for ant hill with default number of ants (40).
     */
    public AntHill(String name)
    {
        antHillName = name;
    }

    /**
     * Act: If there are still ants left inside, see whether one should come out.
     */
    public void act()
    {
        if(ants < maxAnts) 
        {
            if(Greenfoot.getRandomNumber(100) < 10) 
            {
                getWorld().addObject(new Ant(this), getX(), getY());
                ants++;
                countAnts();
            }
        }
        else maxAnts = 0;
        if(antWarriors < maxAntWarriors) 
        {
            if(Greenfoot.getRandomNumber(100) < 10) 
            {
                getWorld().addObject(new AntWarrior(this), getX(), getY());
                antWarriors++;
                countAnts();
            }
        }
        else maxAntWarriors = 0;
        createNewAnts();
    }

    /**
     * Record that we have collected another bit of food.
     */
    public void countFood()
    {
        // if we have no food counter yet (first time) -- create it
        if(foodCounter == null) 
        {
            foodCounter = new Counter("Food: ");
            int x = getX();
            int y = getY() + getImage().getWidth()/2 + 8;

            getWorld().addObject(foodCounter, x, y);
        }        
        foodCounter.increment();
    }
    
    private void createNewAnts()
    {
        if (foodCounter != null)
        {
            if (foodCounter.getValue() >= 4)
            {
                if(Greenfoot.getRandomNumber(100) < 75) 
                {
                    getWorld().addObject(new Ant(this), getX(), getY());
                    ants++;
                    countAnts();
                }
                else
                {
                    getWorld().addObject(new AntWarrior(this), getX(), getY());
                    antWarriors++;
                    countAnts();
                }
                foodCounter.decrement(5);
            }
        }
    }
    
    private void countAnts()
    {
        if(antCounter == null)
        {
            antCounter = new Counter("Ants: ");
            int x = getX();
            int y = getY() + getImage().getWidth()/2 + 8;
    
            getWorld().addObject(antCounter, x, y + 10);
        }
        antCounter.setValue(ants);
    }
    
    public int getAnts()
    {
        return ants;
    }
    
    public String getName()
    {
        return antHillName;
    }
    
    public void removeAnt()
    {
        ants--;
        countAnts();
    }
    
    public void removeAntWarrior()
    {
        antWarriors--;
        countAnts();
    }
}