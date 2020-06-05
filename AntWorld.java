import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The world where ants live.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        for(int i = 1; i <= 2; i++)
        {
            AntHill anthill = new AntHill("AntHill" + i);
            addObject(anthill,Greenfoot.getRandomNumber(SIZE),Greenfoot.getRandomNumber(SIZE));
        }
        for(int i = 1; i <= 2; i++)
        {
            Food food = new Food();
            addObject(food,Greenfoot.getRandomNumber(SIZE),Greenfoot.getRandomNumber(SIZE));
        }
        for(int i = 1; i <= 1; i++)
        {
            Food foodFruit = new Food(50, 20, 130, 60, 130);
            addObject(foodFruit,Greenfoot.getRandomNumber(SIZE),Greenfoot.getRandomNumber(SIZE));
        }
        for(int i = 1; i <= 1; i++)
        {
            BeetleHome beetleHome = new BeetleHome();
            addObject(beetleHome,Greenfoot.getRandomNumber(SIZE),Greenfoot.getRandomNumber(SIZE));
        }
    }
    
    public void act()
    {
        if (getObjects(Food.class).size() == 0 && getObjects(Pheromone.class).size() == 0 && getObjects(Ant.class).size() == 0)
        {
            int highestNum = 0;
            String winner = null;
            for(AntHill antHill : getObjects(AntHill.class))
            {
                if(antHill.getAnts() > highestNum)
                {
                    highestNum = antHill.getAnts();
                    winner = antHill.getName();
                }
            }
            addObject(new FinalScoreboard(winner, highestNum), SIZE/2, SIZE/2);
            Greenfoot.stop();
        }
    }
}
