/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarte_cliamte;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author imed
 */
public class info_climat {
    
    String station;
    String datee;
    String temperature_C;
    String temperature_k;
    String humidite;
    String nebolisite;

    info_climat(String station1,
            String datee1,
            String temperature_C1,
            String temperature_k1,
            String humidite1,
            String nebolisite1) {
        station = station1;
        datee = datee1;
        temperature_C = temperature_C1;
        temperature_k = temperature_k1;
        humidite = humidite1;
        nebolisite = nebolisite1;
    }


    public static class information {

    }
    
}
