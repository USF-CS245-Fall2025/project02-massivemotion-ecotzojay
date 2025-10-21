import java.awt.Color;

/**
 * Represents a single object in the simulation.
 * This class stores the object's state, position, velocity, size, and color
 */
public class CelestialObject {
    public double x, y;
    public double vx, vy;
    public int size;
    public Color color;

    /**
     * Constructor for a new CelestialObject
     * @param x Initial x position of the object
     * @param y Initial y position of the object
     * @param vx Initial x velocity of the object
     * @param vy Initial y velocity of the object
     * @param size Size of the object
     * @param color Color of the object
     */
    public CelestialObject(double x, double y, double vx, double vy, int size, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.size = size;
        this.color = color;
    }
}
