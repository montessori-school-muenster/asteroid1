import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * Ein Gegenstand, der sich mit einer bestimmten Geschwindigkeit bewegen kann.
 * 
 * @author Poul Henriksen
 */
public abstract class Mover extends Actor
{
    private Vector movement = new Vector();
    
    private double x = 0;
    private double y = 0;
    
    public Mover()
    {
    }
    
    /**
     * Erzeugt einen neuen Gegenstand, initialisiert mit der angegebenen Geschwindigkeit.
     */
    public Mover(Vector speed)
    {
        movement = speed;
    }
    
    /**
     * Rückt einen Schritt vorwärts.
     */
    public void move() 
    {
        x = x + movement.getX();
        y = y + movement.getY();
        if(x >= getWorld().getWidth()) {
            x = 0;
        }
        if(x < 0) {
            x = getWorld().getWidth() - 1;
        }
        if(y >= getWorld().getHeight()) {
            y = 0;
        }
        if(y < 0) {
            y = getWorld().getHeight() - 1;
        }
        setLocation(x, y);
    }
    
    public void setLocation(double x, double y) 
    {
        this.x = x;
        this.y = y;
        super.setLocation((int) x, (int) y);
    }
    
    public void setLocation(int x, int y) 
    {
        setLocation((double) x, (double) y);
    }

    /**
     * Erhöht die Geschwindigkeit um den übergebenen Vektor.
     */
    public void increaseSpeed(Vector s) 
    {
        movement.add(s);
    }
    
    /**
     * Liefert die aktuelle Bewegung zurück.
     */
    public Vector getMovement() 
    {
        return movement;
    }
}