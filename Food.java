import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    private GreenfootImage image;
    private int crumbs;
    private int size;
    Random random = new Random();
    private Color color;
    
    public Food()
    {
        crumbs = 100;
        size = 30;
        color = new Color(170, 110, 70);
        image = new GreenfootImage(size, size);
        updateImage();
    }
    
    public Food(int crumbsValue, int sizeValue, int colorValue1, int colorValue2,int colorValue3)
    {
        crumbs = crumbsValue;
        size = sizeValue;
        color = new Color(colorValue1, colorValue2, colorValue3);
        image = new GreenfootImage(size, size);
        updateImage();
    }
    
    /**
     * Act - do whatever the  wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    private void updateImage()
    {
        for(int i = 0; i < crumbs; i++){
            int stDev = size / 6;
            int x = (int) (stDev * random.nextGaussian( ) + 3 * stDev);
            int y = (int) (stDev * random.nextGaussian( ) + 3 * stDev);
                
            // keep crumbs in image
            if(x < 0) 
                x = 0;
            if(x >= size) 
                x = size - 1;
            if(y < 0) 
                y = 0;
            if(y >= size) 
                y = size - 1;
            
            image.setColorAt(x, y, color);
        }
        setImage(image);
    }
    
    public void removeCrumb()
    {
        crumbs--;
        image.clear();
        updateImage();
        if(crumbs <= 0)
        {
            getWorld().removeObject(this);
        }
    }
}
