package unittests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.GeometricFigureLogic;
import model.Circle;
import model.Figure;
import model.Rectangle;
import model.Square;
import model.Triangle;
import persistance.GeometricFigurePersistance;
import persistance.PersistanceWriteException;

/**
 * Class for unit testing
 * @author Mattias Westerberg
 */
public class GeometricFigureTest {

    GeometricFigureLogic geometricFigureLogic
            = new GeometricFigureLogic();

    GeometricFigurePersistance geometricFigurePersistance
            = new GeometricFigurePersistance();

    ArrayList<Figure> figureList = new ArrayList<>();

    public GeometricFigureTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        int count = 0;

        // Create data
        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            double width = r.nextDouble() * 20; // Bredd
            double height = r.nextDouble() * 20; // Höjd

            // Set x and y possition to the same value for all figures to test 
            // if it works to remove them if they are too close to each other.
            int positionX = 1; // Xposition
            int positionY = 1; // YPosition

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test to remove figures that are too close to each other.
     */
    @Test
    public void testRemoveWhenNear() {

        int startFigureListSize = figureList.size();
        
        // Removes figures with a distance less than or equals to 1 from each other.
        int removedFigures = geometricFigureLogic.removeWhenNear(figureList);

        int actualFigureListSize = figureList.size();
        int expectedFigureListSize = startFigureListSize - removedFigures;
        
        assertEquals(expectedFigureListSize, actualFigureListSize);
    }

    /**
     * Test to sort geometric figures by area.
     */
    @Test
    public void testSort() {

        // Sort by area.
        Collections.sort(figureList, Figure.Comparators.AREA);
        
        int actualSortedFigures = 0;
        int expectedSortedFigures = figureList.size() - 1;
        int figureListSize = figureList.size();
        
        for(int i = 0; i < figureListSize - 1; i++) {
            if(figureList.get(i).calculateArea() < figureList.get(i + 1).calculateArea()) {
                actualSortedFigures++;
            }
        }
        
        assertEquals(expectedSortedFigures, actualSortedFigures);
    }
    
    /**
     * Test to write figures to xml file.
     */
    @Test
    public void testWriteResult() {

        int actualWrittenFigures = 0;
        int expectedWrittenFigures = figureList.size();
        
        try {
            // Write result to xml file.
            actualWrittenFigures = geometricFigurePersistance.writeResult(figureList);
        } catch (PersistanceWriteException ex) {
            Logger.getLogger(GeometricFigureTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(expectedWrittenFigures, actualWrittenFigures);
    }
}
