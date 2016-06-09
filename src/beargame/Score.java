/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import com.sun.javaws.Main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Anto
 */
public class Score {
    
    private static final String SCORES = "score.xml";
    private static ArrayList<String> listScores = new ArrayList<>();
    private static ArrayList<String> listAuthor = new ArrayList<>();
    
    public static void saveToXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("ListScores");
            document.appendChild(rootElement);
            int size;
            if (listScores.size() < 5) size = listScores.size();
            else size = 5;
            for(byte i = 0; i < size; i++){
                //score
                Element score = document.createElement("score");
                rootElement.appendChild(score);
                //name
                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(listAuthor.get(i)));
                score.appendChild(name);
                //points
                Element points = document.createElement("points");
                points.appendChild(document.createTextNode(listScores.get(i)));
                score.appendChild(points);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(SCORES));
            transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void loadFromXML() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        Document configXML = null;
        File f = new File(SCORES);
        if (f.exists()) {
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                configXML = documentBuilder.parse(SCORES);
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            NodeList highScores = configXML.getElementsByTagName("points");
            NodeList recordAuthorNodeList = configXML.getElementsByTagName("name");

            listScores = new ArrayList<>();
            listAuthor = new ArrayList<>();
            for(byte i = 0; i < highScores.getLength(); i++){
                listAuthor.add(i, recordAuthorNodeList.item(i).getTextContent());
                listScores.add(i, highScores.item(i).getTextContent());
            }
        }
    }
    
    public static boolean verifyMaxScore(double score) {
        boolean bigger = false;
        if (listScores.isEmpty() || listScores.size() < 5) bigger = true;
        else {
            for(byte i = 0; i < listScores.size(); i++){
                if (score > Double.parseDouble(listScores.get(i))) {
                    bigger = true;
                    break;
                }
            }
        }
        return bigger;
    }
    
    
    public static void setMaxScore(double score, String name) {
        String next = null;
        String nameNext = null;
        if (listScores.isEmpty() || listScores.size() < 5) {
            listScores.add(String.valueOf(score));
            listAuthor.add(name);
            for(byte i = 0; i < listScores.size(); i++){
                for (byte j = i; j > 0; j--) {
                    if (Double.parseDouble(listScores.get(j)) > Double.parseDouble(listScores.get(j-1))) {
                        next = listScores.get(j);
                        nameNext = listAuthor.get(j);
                        listScores.set(j, listScores.get(j-1));
                        listAuthor.set(j, listAuthor.get(j-1));
                        listScores.set(j-1, next);
                        listAuthor.set(j-1, nameNext);
                    }
                }
            }
        }
        else {
            if (score > Double.parseDouble(listScores.get(listScores.size()-1))) {
                listScores.set(listScores.size()-1, String.valueOf(score));
                listAuthor.set(listScores.size()-1, name);
                for(byte i = 0; i < listScores.size(); i++){
                    for (byte j = i; j > 0; j--) {
                        if (Double.parseDouble(listScores.get(j)) > Double.parseDouble(listScores.get(j-1))) {
                            next = listScores.get(j);
                            nameNext = listAuthor.get(j);
                            listScores.set(j, listScores.get(j-1));
                            listAuthor.set(j, listAuthor.get(j-1));
                            listScores.set(j-1, next);
                            listAuthor.set(j-1, nameNext);
                        }
                    }
                }
            }
        }
    }
    
    public static ArrayList<String> getListScores() {
        return listScores;
    }

    public static void setListScores(ArrayList<String> listScores) {
        Score.listScores = listScores;
    }

    public static ArrayList<String> getListAuthor() {
        return listAuthor;
    }

    public static void setListAuthor(ArrayList<String> listAuthor) {
        Score.listAuthor = listAuthor;
    }
    
    
}
