package smarte_cliamte;
//by imed
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;
//import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. by imed
 */

/**
 *
 * @author imed
 */
public class outil_telechargement {
 
    // comme sa je peux deziper le fichier rar 
    static public void gunzipIt(String s) throws IOException{
          //String s=reglage_date();
          String INPUT_GZIP_FILE = "C:/Users/imed/Desktop/telechagement_Csv/" + s + ".gz";
          String OUTPUT_FILE = "C:/Users/imed/Desktop/telechagement_Csv/" + s + ".csv";
     byte[] buffer = new byte[1024];
     FileOutputStream out;
        try (GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(INPUT_GZIP_FILE))) {
            out = new FileOutputStream(OUTPUT_FILE);
            int len;
            while ((len = gzis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        }
    	out.close();

    
     }
    
    public void telechargement_csv(String s) throws MalformedURLException, IOException { 
        System.out.println(s);
        //https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/synop.2017032615.csv
      URL url = new URL ("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/synop."+ s +".csv");
      File file = new File("C:/Users/imed/Desktop/telechagement_Csv/" +s+".csv");
      //FileUtils.copyURLToFile(url, file);
    }
        public void telechargement_gz(String s) throws MalformedURLException, IOException { 
            System.out.println(s);
      URL url = new URL("https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop." + s + ".csv.gz");
    //https://donneespubliques.meteofrance.fr/donnees_libres/Txt/Synop/Archive/synop.201703.csv.gz
       File file = new File("C:/Users/imed/Desktop/telechagement_Csv/" +s+".gz");
       //FileUtils.copyURLToFile(url, file);
            System.out.println(s);
       gunzipIt(s); 
       }
    //   gunzipIt();
        
    }


