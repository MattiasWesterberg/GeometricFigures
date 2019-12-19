package logic;

import java.util.ArrayList;
import model.Figure;

/**
 *
 * @author Mattias Westerberg
 */
public class GeometricFigureLogic {

    /**
     * Removes figures from ArrayList when distance between them is less than 1
     * or equals 1.
     *
     * @param figureListReference Reference to ArrayList with geometric figures
     * of type Figure.
     * @return removedFigures Amount of removed figures.
     */
    public int removeWhenNear(ArrayList<Figure> figureListReference) {

        ArrayList<Figure> figureListCompare = new ArrayList<>(figureListReference);

        int removedFigures = 0;

        for (int i = 0; i < figureListCompare.size(); i++) {
            Figure figure1 = figureListCompare.get(i);
            for (int i2 = 0; i2 < figureListReference.size(); i2++) {
                Figure figure2 = figureListReference.get(i2);
                if (!figure1.equals(figure2) && 
                        this.distance(figure1, figure2) <= 1) {

                    if(figureListReference.remove(figure1)) {
                        removedFigures++;
                    }
                }
            }
        }
        return removedFigures;
    }

    /**
     * Calculates the distance between two geometric figures.
     *
     * @param figure1
     * @param figure2
     * @return Distance between two geometric figures.
     */
    private double distance(Figure figure1, Figure figure2) {
        double x1 = figure1.getXCoordinate();
        double y1 = figure1.getYCoordinate();
        double x2 = figure2.getXCoordinate();
        double y2 = figure2.getYCoordinate();
        double d = this.distance(x1, y1, x2, y2);
        return d;
    }

    /**
     * Calculate distance between two spots.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return Distance between two spots.
     */
    private double distance(double x1, double y1, double x2, double y2) {
        double x = Math.pow(x1 - x2, 2);
        double y = Math.pow(y1 - y2, 2);
        return Math.sqrt(x + y);
    }
}
