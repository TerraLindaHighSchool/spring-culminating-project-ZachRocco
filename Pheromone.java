import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pheromone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pheromone extends Actor
{
    private GreenfootImage image;
    private final static int MAX_INTENSITY = 180;
    private int intensity;
    
    public Pheromone()
    {
        intensity = MAX_INTENSITY;
        updateImage();
    }
    /**
     * Act - do whatever the Pheromone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        intensity--;
        if(intensity <= 0)
        {
            getWorld().removeObject(this);
        }
        else
        {
             if ((intensity % 6) == 0)  // prevents updating too often
             { 
                 updateImage();
             }
        }
    }   
    
    public void updateImage()
    {
        int size = intensity / 3 + 5;
        image = new GreenfootImage(size + 1, size + 1);
        image.setColor(Color.WHITE);
        image.setTransparency(intensity/3);
        image.fillOval(0, 0, size, size);
        image.setColor(Color.DARK_GRAY);
        image.fillRect(size / 2, size / 2, 2, 2);
        setImage(image);
    }
}
