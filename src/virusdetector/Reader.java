/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusdetector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Eranga
 */
public class Reader {
     public static ArrayList readVirusDefinition(){
     
        ArrayList<String> definitions = new ArrayList<>();  
        try {
            BufferedReader br = new BufferedReader(new FileReader("definitions.txt"));           
            String line = br.readLine();
            while (line != null) {
                definitions.add(line);
                line = br.readLine();
            }  
            //close the Buffered reader
            br.close();     
        } catch(FileNotFoundException e) {
            return null;
        } catch(IOException e){
            return null;
        }
        
        return definitions;
    }
}
