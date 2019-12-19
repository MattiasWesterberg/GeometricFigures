package model;

/**
 * Entity class for Rectangle
 * @author Mattias Westerberg
 */
public class Rectangle extends Figure {
    
    private double lengthX;
    private double lengthY;
	
    public Rectangle(double lengthX, double lengthY, 
            int positionX, int positionY) {
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Calculates the area of a rectangle and returns it.
     * @return Area
     */
    @Override
    public double calculateArea() {
        return this.lengthX * this.lengthY;
    }
}
