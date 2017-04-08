/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarte_cliamte;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author imed
 */
public class get_date {
    
    
    // cree pour mieux manipuler les dates pour le techargement et tout les outils autres 
    public String get_annee_encour(){
    
        String txtDate = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
        String values[] = txtDate.split("/");
        String Annee = values[2];
        return Annee;
}
  public String get_mois_encour(){
    
        String txtDate = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
        String values[] = txtDate.split("/");
        String mois= values[1];
        return mois;
}

    public String get_jour_encour(){
    
        String txtDate = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
        String values[] = txtDate.split("/");
        String jour = values[0];
        return jour;
}
}
