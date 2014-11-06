/**
 * Ein 2D-Vektor.
 * 
 * @author Poul Henriksen
 */
public class Vector
{
    double dx = 0;
    double dy = 0;
    int direction = 0;
    double length;
    
    public Vector()
    {
    }

    public Vector(int direction, double length)
    {
       this.length = length;
       this.direction = direction;
       dx = length * Math.cos(Math.toRadians(direction));
       dy = length * Math.sin(Math.toRadians(direction));    
    }

    /**
     * Setzt die Richtung dieses Vektors.
     */
    public void setDirection(int direction) {
        this.direction = direction;
        dx = length * Math.cos(Math.toRadians(direction));
        dy = length * Math.sin(Math.toRadians(direction));   
    }
   
    /**
     * Addiert einen weiteren Vektor zu diesem Vektor.
     */
    public void add(Vector other) {
        dx += other.dx;
        dy += other.dy;    
        this.direction = (int) Math.toDegrees(Math.atan2(dy, dx));
        this.length = Math.sqrt(dx*dx+dy*dy);
    }   
    
    /**
     * Liefert den X-Wert dieses Vektors zurück.
     */
    public double getX() {
        return dx;
    }
     
    /**
     * Liefert den Y-Wert dieses Vektors zurück.
     */
    public double getY() {
        return  dy;
    }
    
    /**
     * Liefert die aktuelle Richtung (in Grad) zurück.
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * Liefert die aktuelle Länge des Vektors zurück.
     */
    public double getLength() {
        return length;
    }
    
    /**
     * Erzeugt eine Kopie dieses Vektors.
     */
    public Vector copy() {
        Vector copy = new Vector();
        copy.dx = dx;
        copy.dy = dy;
        copy.direction = direction;
        copy.length = length;
        return copy;
    }    
}