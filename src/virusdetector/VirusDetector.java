/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virusdetector;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Eranga
 */
public class VirusDetector {

    public static void start(File file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String fileChecksum = VirusDetector.getCheckSUm(md,file.getAbsolutePath());
            Reader fh = new Reader();
            ArrayList<String> definition = Reader.readVirusDefinition();
            if(definition==null){
                JOptionPane.showMessageDialog(null,"No definitions in the definitions file!","Error",JOptionPane.INFORMATION_MESSAGE);
            }else{
                boolean possibleVirus = VirusDetector.detect(fileChecksum, definition);

                if(possibleVirus){
                   //If the file is a virus
                    JOptionPane.showMessageDialog(null, file.getName()+" file is a virus.\n MD5 = "+fileChecksum, "Virus!!",JOptionPane.WARNING_MESSAGE);
                }else{
                     //If the file is a virus
                    JOptionPane.showMessageDialog(null, file.getName()+" is not a virus according to definitions", "Not a Virus !!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
         
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Please select a valid file !","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static String getCheckSUm(MessageDigest md,String file_path){
        String checkSum=null;
        try{
            //getting the MD5 hash value
            md.update(Files.readAllBytes(Paths.get(file_path)));
            byte[] binary = md.digest();
            checkSum = DatatypeConverter.printHexBinary(binary).toLowerCase();
        }catch (IOException e){
            System.out.println(e);
        }       
        return checkSum;
    }
    public static boolean detect(String checkSum, ArrayList<String> virusDefinitions){     
        boolean possibleVirus = false;
        //checking the generated MD5 with hash values from the file
        for(int i=0;i<virusDefinitions.size();i++){
            if(checkSum.equals(virusDefinitions.get(i))){
                possibleVirus = true;
                return possibleVirus;
            }
        }       
        return possibleVirus;
    }
}
