import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWarrior extends Creature
{
    private boolean carryingFood;
    private GreenfootImage image1;
    private GreenfootImage image2;
    private static final int MAX_PH_AVAILBLE = 50;
    private static final int TIME_FOLLOWING_TRAIL = 30;
    private int phAvailable = MAX_PH_AVAILBLE;
    private int followTrailTimeRemaining = 0;
    private Ant nearbyAnt;
    private AntWarrior nearbyAntWarrior;
    private int antsKilled;
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public AntWarrior(AntHill home)
    {
        setHomeHill(home);
    }

    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
        if (getWorld() != null && getWorld().getObjects(Food.class).size() == 0 && getWorld().getObjects(Pheromone.class).size() == 0)
        {
            walkTowardsHome();
            if(atHome())
            {
                getWorld().removeObject(this);
            }
        }
    }
    
    private void checkForFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) 
        {
            food.removeCrumb();
            carryingFood = true;
            setImage(image2);
        }
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
    
    private void searchForPredators()
    {
        if(followTrailTimeRemaining == 0)
        {
            if (predatorNearby())
            {
                PredatorBeetle predatorBeetle = getObjectsInRange(100, PredatorBeetle.class).get(0);
                headTowards(predatorBeetle);
                handlePheromoneDrop();
                if (getIntersectingObjects(PredatorBeetle.class).size() != 0)
                {
                    predatorBeetle.decrementHealth();
                    destroyAnt();
                }
            }
            else if (enemyAntNearby())
            {
                if (nearbyAntWarrior != null)
                {
                    headTowards(nearbyAntWarrior);
                    if(this.getX() == nearbyAntWarrior.getX() && this.getY() == nearbyAntWarrior.getY())
                    {
                        nearbyAntWarrior.destroyAnt();
                        antsKilled++;
                    }
                }
                else if (nearbyAnt != null)
                {
                    headTowards(nearbyAnt);
                    if(this.getX() == nearbyAnt.getX() && this.getY() == nearbyAnt.getY())
                    {
                        nearbyAnt.destroyAnt();
                        antsKilled++;
                    }
                }
            }
            else if (smellsPheromone())
            {
                walkTowardsPheromoneCenter();
            }
            else
            {
                randomWalk();
            }
        }
        else
        {
            followTrailTimeRemaining--;
            walkAwayFromHome();
        }
    }
    
    private boolean predatorNearby()
    {
        if(getObjectsInRange(50, PredatorBeetle.class).size() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private boolean enemyAntNearby()
    {
        //java.util.List ants = getObjectsInRange(50, PredatorBeetle.class);
        boolean isEnemyNearby = false;
        for(Ant ant : getObjectsInRange(50, Ant.class))
        {
            if (ant.getHomeHill() != this.getHomeHill())
            {
                isEnemyNearby = true;
                nearbyAnt = ant;
                nearbyAntWarrior = null;
            }
        }
        for(AntWarrior antWarrior : getObjectsInRange(50, AntWarrior.class))
        {
            if (antWarrior.getHomeHill() != this.getHomeHill())
            {
                isEnemyNearby = true;
                nearbyAntWarrior = antWarrior;
                nearbyAnt = null;
            }
        }
        if (isEnemyNearby)
        {
            return true;
        }
        else return false;
    }
    
    private void status()
    {
        searchForPredators();
        if (antsKilled >= 5)
        {
            destroyAnt();
        }
    }
    
    private void handlePheromoneDrop()
    {
        if (phAvailable == MAX_PH_AVAILBLE)
        {
            PheromoneWarrior pheromoneWarrior = new PheromoneWarrior();
            getWorld().addObject(pheromoneWarrior,getX(),getY());
            phAvailable = 0;
        }
        else
        {
            phAvailable++;
        }
    }
    
    private boolean smellsPheromone()
    {
        if(isTouching(PheromoneWarrior.class))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private void walkTowardsPheromoneCenter()
    {
        Actor pheromone = getIntersectingObjects(PheromoneWarrior.class).get(0);
        if (pheromone != null)
        {
            headTowards(pheromone);
            if (pheromone.getX() == this.getX() && pheromone.getY() == this.getY())
            {
                followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
            }
        }
    }
    
    public void destroyAnt()
    {
        getHomeHill().removeAntWarrior();
        getWorld().removeObject(this);
    }
}
