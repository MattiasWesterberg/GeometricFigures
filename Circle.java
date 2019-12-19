package model;

/**
 * Entity class for Circle
 * @author Mattias Westerberg
 */
public class Circle extends Figure {
	
    private double radius;

    public Circle(double radius, int positionX, int positionY) {
        this.radius = radius;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    /**
     * Calculates the area of a circle and returns it.
     * @return Area
     */
    @Override
    public double calculateArea() {
        return this.radius * this.radius * 3.14d;
    }
}
