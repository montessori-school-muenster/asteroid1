import greenfoot.*;  // (World, Actor, GreenfootImage und Greenfoot)

/**
 * Eine Rakete, die durch die Pfeiltasten Auf, Links und Rechts gesteuert wird.
 * Die Rakete feuert durch Drücken der Leertaste.
 * 
 * @author Poul Henriksen
 * @author Michael Kolling
 */
public class Rocket extends Mover
{
    private int gunReloadTime;                  // Die minimale Verz�gerung zwischen den Sch�ssen.
    private int reloadDelayCount;               // Zeitspanne seit dem letzten Schuss.
    private Vector acceleration;                // Geschwindigkeit der Rakete.
    private int shotsFired;                     // Anzahl der abgefeuerten Sch�sse.
    
    private GreenfootImage rocket = new GreenfootImage("rocket.png");    
    private GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");

    /**
     * Initialisiert diese Rakete.
     */
    public Rocket()
    {
        gunReloadTime = 30;
        reloadDelayCount = 0;
        acceleration = new Vector(0, 0.3);
        increaseSpeed(new Vector(13, 0.3)); // anfangs langsam dahintreibend
        shotsFired = 0;
    }

    /**
     * Tut, was eine Rakete so macht. (Das heißt: meistens Herumfliegen und Wenden,
     * Beschleunigen und Schießen, wenn die entsprechenden Tasten gedrückt werden.)
     */
    public void act()
    {
        move();
        checkCollision();
        checkKeys();
        reloadDelayCount++;
    }
    
    /**
     * Liefert die Anzahl der von dieser Rakete abgegebenen Schüsse zurück.
     */
    public int getShotsFired()
    {
        return shotsFired;
    }
    
    /**
     * Liefert die ungefähre momentane Fluggeschwindigkeit dieser Rakete zurück.
     */
    public int getSpeed()
    {
        return (int) (getMovement().getLength() * 10);
    }
    
    /**
     * Setzt die Zeit, die benötigt wird, um die Waffe der Rakete neu zu laden. Je kürzer diese Zeit
     * ist, umso schneller kann die Rakete feuern. Die Standardzeit (am Anfang) beträgt 20.
     */
    public void setGunReloadTime(int reloadTime)
    {
        gunReloadTime = reloadTime;
    }
    
    /**
     * Prüft, ob wir mit einem Asteroiden kollidieren.
     */
    private void checkCollision() 
    {
        Asteroid a = (Asteroid) getOneIntersectingObject(Asteroid.class);
        if(a != null) {
            getWorld().addObject(new Explosion(), getX(), getY());
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Prüft, ob irgendeine Taste gedrückt wurde, und reagiert darauf.
     */
    private void checkKeys() 
    {
        ignite(Greenfoot.isKeyDown("up"));
        
        if(Greenfoot.isKeyDown("left")) {
            setRotation(getRotation() - 5);
        }        
        if(Greenfoot.isKeyDown("right")) {
            setRotation(getRotation() + 5);
        }
        if(Greenfoot.isKeyDown("space")) {
            fire();
        }        
    }
    
    /**
     * Soll die Rakete gezündet werden?
     */
    private void ignite(boolean boosterOn) 
    {
        if(boosterOn) {
            setImage(rocketWithThrust);
            acceleration.setDirection(getRotation());
            increaseSpeed(acceleration);
        }
        else {
            setImage(rocket);        
        }
    }
    
    /**
     * Feuert, wenn die Waffe bereit ist.
     */
    private void fire() 
    {
        if(reloadDelayCount >= gunReloadTime) {
            Bullet b = new Bullet(getMovement().copy(), getRotation());
            getWorld().addObject(b, getX(), getY());
            b.move();
            shotsFired++;
            reloadDelayCount = 0;
        }
    }
}