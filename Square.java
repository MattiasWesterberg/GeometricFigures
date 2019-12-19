package model;

/**
 * Entity class for Square.
 * @author Mattias Westerberg
 */
public class Square extends Figure {
    
    private double lengthSides;
	
    public Square(double lengthSides, int positionX, int positionY) {
        this.lengthSides = lengthSides;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Calculates the area of a square and returns it.
     * @return Area
     */
    @Override
    public double calculateArea() {
        return this.lengthSides * this.lengthSides;
    }
}
