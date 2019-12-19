package model;

import java.util.Comparator;

/**
 * Abstract super class for geometric figures
 * @author Mattias Westerberg
 */
public abstract class Figure implements Comparable<Figure> {

    protected int positionX;
    protected int positionY;

    /**
     * Calculates the area of each figure and returns it.
     * @return Area
     */
    public abstract double calculateArea();

    /**
     * Returns the x coordinate for each figure.
     * @return X coordinate
     */
    //@Override
    public int getXCoordinate() {
        return positionX;
    }

    /**
     * Returns the y coordinate for each shape.
     * @return Y coordinate
     */
    //@Override
    public int getYCoordinate() {
        return positionY;
    }
    
    /**
     * Compares the area of objects of type Figure.
     * @param figure
     * @return 
     */
    @Override
    public int compareTo(Figure figure) {
        Double thisArea = this.calculateArea();
        Double figureArea = figure.calculateArea();
        return thisArea.compareTo(figureArea);
    }

    public static class Comparators {
        public static Comparator<Object> AREA = (Object o1, Object o2) -> {
            Figure f1 = (Figure)o1;
            Double a1 = f1.calculateArea();
            Figure f2 = (Figure)o2;
            Double a2 = f2.calculateArea();
            return a1.compareTo(a2);
        };
    }
}
