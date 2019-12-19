package persistance;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import org.w3c.dom.DOMException;
import main.EntryPoint;
import model.Figure;

/**
 * Class used to write geometrical figures to xml file and console.
 *
 * @author Mattias Westerberg
 */
public class GeometricFigurePersistance {

    /**
     * Writes all figures to XML file and console.
     *
     * @param figures
     * @return
     * @throws PersistanceWriteException
     */
    public int writeResult(ArrayList<Figure> figures)
            throws PersistanceWriteException {

        int figuresWritten = 0;

        try {
            Document doc;
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(System.out)) {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // Creates instance of DOM
                doc = docBuilder.newDocument();

                Element rootElement = doc.createElement("geometricfigures");
                doc.appendChild(rootElement);
                bufferedOutputStream.write("<geometricfigures>".getBytes());

                for (Figure figure : figures) {

                    doc = writeGeometricInformation(figure.getClass().
                            getSimpleName(), figure.getXCoordinate(),
                            figure.getYCoordinate(), figure.calculateArea(),
                            bufferedOutputStream, doc, rootElement);

                    figuresWritten++;
                }
                bufferedOutputStream.write("</geometricfigures>".getBytes());
                bufferedOutputStream.flush();
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            File f = new File("figures.xml");
            f.createNewFile();

            StreamResult result = new StreamResult(f);

            // Send DOM to file
            transformer.transform(source, result);

        } catch (IOException | IllegalArgumentException | ParserConfigurationException
                | TransformerException | DOMException | PersistanceWriteException ex) {

            Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistanceWriteException("Unable to persist geometric figures: " + ex.getMessage());
        }
        return figuresWritten;
    }

    /**
     * Creates data elements
     *
     * @param figureType
     * @param xPossition
     * @param yPossition
     * @param figureArea
     * @param bufferedOutputStream
     * @param doc
     * @param rootElement
     * @return
     * @throws persistance.PersistanceWriteException
     */
    public Document writeGeometricInformation(String figureType, int xPossition, int yPossition,
            double figureArea, BufferedOutputStream bufferedOutputStream, Document doc, Element rootElement)
            throws PersistanceWriteException {

        try {
            Element figure = doc.createElement("figure");
            rootElement.appendChild(figure);

            bufferedOutputStream.write("<figure>".getBytes());

            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(figureType));
            figure.appendChild(type);

            bufferedOutputStream.write(String.format("<type>%s</type>", figureType).getBytes());

            Element area = doc.createElement("area");
            area.appendChild(doc.createTextNode(Double.toString(figureArea)));
            figure.appendChild(area);

            bufferedOutputStream.write(String.format("<area>%f</area>", figureArea).getBytes());

            Element xcoordinate = doc.createElement("xcoordinate");
            xcoordinate.appendChild(doc.createTextNode(Integer.toString(xPossition)));
            figure.appendChild(xcoordinate);

            bufferedOutputStream.write(String.format("<xcoordinate>%s</xcoordinate>", xPossition).getBytes());

            Element ycoordinate = doc.createElement("ycoordinate");
            ycoordinate.appendChild(doc.createTextNode(Integer.toString(yPossition)));
            figure.appendChild(ycoordinate);

            bufferedOutputStream.write(String.format("<ycoordinate>%s</ycoordinate>", yPossition).getBytes());
            bufferedOutputStream.write("</figure>".getBytes());

        } catch (IOException | DOMException ex) {
            Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistanceWriteException("Unable to write figure data: " + ex.getMessage());
        }

        return doc;
    }
}
