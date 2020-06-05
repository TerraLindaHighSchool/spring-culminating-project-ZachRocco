import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class finalScoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FinalScoreboard extends Actor
{
    private String text;
    private int value;
    
    /**
     * Create a counter with a given text prefix, initialized to zero.
     */
    public FinalScoreboard(String prefix, int ants)
    {
        text = prefix;
        value = ants;
        int imageWidth= (text.length() + 2) * 50;
        setImage(new GreenfootImage(imageWidth, 64));
        updateImage();
    }
    
    /**
     * Act - do whatever the finalScoreboard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    private void updateImage()
    {
        Font font = new Font(40);
        GreenfootImage image = getImage();
        image.clear();
        image.setFont(font);
        image.drawString(text + " Wins! " + value + " ants", 1, 64);
    }
}
