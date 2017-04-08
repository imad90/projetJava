/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarte_cliamte;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author imed
 */
public class outil_recherchefichier {
    
    public static void  cherche(){
  File f = new File("C:/Users/imed/Desktop/telechagement_Csv");
File[] matchingFiles = f.listFiles(new FilenameFilter() {
    public boolean accept(File dir, String name) {
        return  name.endsWith("csv");
        
        
    }
}
        
);
    }
    
    
    
}
