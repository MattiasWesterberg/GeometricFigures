package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.GeometricFigureLogic;
import model.Circle;
import model.Figure;
import model.Rectangle;
import model.Square;
import model.Triangle;
import persistance.GeometricFigurePersistance;
import persistance.PersistanceWriteException;

/**
 * Main class with entry point(main method).
 * @author Mattias Westerberg
 */
public class EntryPoint {

    public static void main(String[] args) {

        GeometricFigurePersistance geometricFigurePersistance
                = new GeometricFigurePersistance();

        GeometricFigureLogic geometricFigureLogic
                = new GeometricFigureLogic();

        ArrayList<Figure> figureList = new ArrayList<>();
        int count = 0;

        // Create data
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            double width = r.nextDouble() * 20; // Bredd
            double height = r.nextDouble() * 20; // Höjd
            int positionX = r.nextInt(20); // X position
            int positionY = r.nextInt(20); // Y Position

            if (count == 0) {
                figureList.add(new Circle(width / 2, positionX, positionY));
            } else if (count == 1) {
                figureList.add(new Rectangle(width, height, positionX, positionY));
            } else if (count == 2) {
                figureList.add(new Square(width, positionX, positionY));
            } else if (count == 3) {
                figureList.add(new Triangle(width, height, positionX, positionY));
            }

            if (count == 3) {
                count = 0;
            } else {
                count++;
            }
        }
 
        // Removes figures with a distance less than or equals to 1 from each other.
        geometricFigureLogic.removeWhenNear(figureList);
        
        // Sort by area.
        Collections.sort(figureList, Figure.Comparators.AREA);

        // Write result to xml file.
        try {
            geometricFigurePersistance.writeResult(figureList);
        } catch (PersistanceWriteException ex) {
            Logger.getLogger(EntryPoint.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
    }
}
