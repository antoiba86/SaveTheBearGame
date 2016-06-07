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
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Anto
 */
public class Score {
    
    private static final String SCORES = "highestScore.xml";
    private static ArrayList<String> listScores = new ArrayList<>();
    private static ArrayList<String> listAuthor = new ArrayList<>();
    
    public static void saveToXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(SCORES);
            document.setXmlVersion("1.0");
            for(byte i = 0; i < listScores.size(); i++){
                document.getElementsByTagName("points").item(i).setTextContent(listScores.get(i));
                document.getElementsByTagName("player").item(i).setTextContent(listAuthor.get(i));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(SCORES));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
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
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            configXML = documentBuilder.parse(SCORES);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList highScores = configXML.getElementsByTagName("points");
        NodeList recordAuthorNodeList = configXML.getElementsByTagName("player");
        
        listScores = new ArrayList<>();
        listAuthor = new ArrayList<>();
        for(byte i = 0; i < highScores.getLength(); i++){
            listScores.add(i, highScores.item(i).getTextContent());
            listAuthor.add(i, recordAuthorNodeList.item(i).getTextContent());
        }
    }
    
    public static boolean verifyMaxScore(double score) {
        boolean bigger = false;
        if (listScores.isEmpty() || listScores.size() < 6) bigger = true;
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
    
    
    public static void setMaxScore(double score) {
        String next = null;
        if (listScores.isEmpty() || listScores.size() < 6) listScores.add(String.valueOf(score));
        else {
            for(byte i = 0; i < listScores.size(); i++){
                if (score > Double.parseDouble(listScores.get(i))) {
                    next = listScores.get(i);
                    listScores.set(i, String.valueOf(score));
                    score = Double.parseDouble(next);
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
