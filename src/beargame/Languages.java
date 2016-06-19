/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beargame;

import com.sun.javaws.Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anto
 */
public class Languages {
    static ArrayList<String> text = null;
    
    public static void readLanguageFile(String path){
        BufferedReader br = null;
        InputStream in = Languages.class.getResourceAsStream(path);
        br = new BufferedReader(new InputStreamReader(in));
        text = Languages.storeFileContentIntoList(br);
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Guarda el contenido de un fichero en un ArrayList. El salto de línea
     * actúa como delimitador, es decir, la primera línea se guarda en la posición
     * 0, la segunda en la 1...
     * @param br un BufferedReader con un FileReader con la ruta especificada.
     * @return los textos en un ArrayList de cadenas.
     */
    private static ArrayList<String> storeFileContentIntoList(BufferedReader br){
        String line;
        ArrayList<String> lines = new ArrayList<>();
        try {
            while((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Languages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }

    public static String getText(int i) {
        return text.get(i);
    }
    
    
}
