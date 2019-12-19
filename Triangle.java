package model;

/**
 * Entity class for Triangle.
 * @author Mattias Westerberg
 */
public class Triangle extends Figure {
	
    private double lengthBase;
    private double lengthHeight;
    
    public Triangle(double lengthBase, double lengthHeight, 
            int positionX, int positionY) {
        this.lengthBase = lengthBase;
        this.lengthHeight = lengthHeight;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Calculates the area of a triangle and returns it.
     * @return Area
     */
    @Override
    public double calculateArea() {
        return this.lengthBase * this.lengthHeight / 2;
    }
}
