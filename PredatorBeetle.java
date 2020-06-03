import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PredatorBeetle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PredatorBeetle extends Creature
{
    private int antsEaten;
    private int health = 5;
    private int cooldown;
    
    public PredatorBeetle(BeetleHome home)
    {
        setHomeHill(home);
        Speed = 1;
    }
    
    /**
     * Act - do whatever the PredatorBeetle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(cooldown <= 0)
        {
            status();
        }
        else
        cooldown--;
    }    
    
    private void status()
    {
        if (isFull())
        {
            walkTowardsHome();
            if(atHome())
            {
                cooldown = 200;
                antsEaten = 0;
            }
        }
        else
        {
            searchForFood();
        }
        if (health <= 0)
        {
            FoodBeetle foodBeetle = new FoodBeetle();
            getWorld().addObject(foodBeetle, getX(), getY());
            getWorld().removeObject(this);
        }
    }
    
    private boolean isFull()
    {
        if (antsEaten >= 10)
        {
            return true;
        }
        else 
        return false;
    }
    
    private void searchForFood()
    {
        randomWalk();
        if (antInRange())
        {
            Ant ant = getObjectsInRange(100, Ant.class).get(0);
            headTowards(ant);
        }
        if (getIntersectingObjects(Ant.class).size() != 0)
        {
            Ant ant = getIntersectingObjects(Ant.class).get(0);
            getWorld().removeObject(ant);
            antsEaten++;
        }
    }
    
    private boolean antInRange()
    {
        if (getObjectsInRange(100, Ant.class).size() != 0)
        {
            return true;
        }
        else 
        return false;
    }
    
    private boolean atHome()
    {
        if (getHomeHill() != null) 
        {
            return (Math.abs(getX() - getHomeHill().getX()) < 4) && 
                   (Math.abs(getY() - getHomeHill().getY()) < 4);
        }
        else {
            return false;
        }
    }
    
    public void decrementHealth()
    {
        antsEaten++;
        health--;
    }
}

