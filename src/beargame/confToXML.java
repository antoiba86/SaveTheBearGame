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
public class confToXML {
    private static final String CONF = "config.xml";
    private static String confLanguage = "Castellano";
    private static String confSound = "On";
    
    public static void saveToXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element rootElement = document.createElement("Configuration");
            document.appendChild(rootElement);
            //language
            Element lang = document.createElement("language");
            lang.appendChild(document.createTextNode(confLanguage));
            rootElement.appendChild(lang);
            //sound
            Element sound = document.createElement("sound");
            sound.appendChild(document.createTextNode(confSound));
            rootElement.appendChild(sound);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(CONF));
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
        File f = new File(CONF);
        if (f.exists()) {
            try {
                documentBuilder = documentBuilderFactory.newDocumentBuilder();
                configXML = documentBuilder.parse(CONF);
            } catch (ParserConfigurationException | SAXException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            NodeList lang = configXML.getElementsByTagName("language");
            NodeList sound = configXML.getElementsByTagName("sound");
            
            confLanguage =  lang.item(0).getTextContent();
            confSound = sound.item(0).getTextContent();
        }
    }

    public static String getConfLanguage() {
        return confLanguage;
    }

    public static void setConfLanguage(String confLanguage) {
        confToXML.confLanguage = confLanguage;
    }

    public static String getConfSound() {
        return confSound;
    }

    public static void setConfSound(String confSound) {
        confToXML.confSound = confSound;
    }
    
    
}
