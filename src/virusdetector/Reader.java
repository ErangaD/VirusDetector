/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusdetector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Eranga
 */
public class Reader {
     public static ArrayList readVirusDefinition(){
     
        ArrayList<String> definitions = new ArrayList<>();  
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("definitions.txt"));           
            String line = bufferedReader.readLine();
            while (line != null) {
                definitions.add(line);
                line = bufferedReader.readLine();
            }  
            //close the Buffered reader
            bufferedReader.close();     
        } catch(Exception e){
            return null;
        }
        
        return definitions;
    }
}
