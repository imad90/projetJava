/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. by imed
 */
package smarte_cliamte;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 *
 * @author imed
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ComboBox T_annee, T_mois, T_jour, T_heur,c_annee1,c_annee2,c_mois1,c_mois2,c_jour1,c_jour2;
    
    @FXML
    private Button telechargement,importer;

    @FXML
    private TableView table_donne;
    

    @FXML
    private ListView liste;
 
    
    public List<info_climat> i_climat=new ArrayList<>();
    public List<Station> station=new ArrayList<>();

    
    
    
    
    
        public static class information {

        private final SimpleStringProperty code_ville;

        public SimpleStringProperty getCode_ville() {
            return code_ville;
        }
        private final SimpleStringProperty temp_k;
        private final SimpleStringProperty temp_c;
        private final SimpleStringProperty humidite;
        private final SimpleStringProperty nebulosite;

        private information(String code_ville, String temp_k, String temp_c, String humidite, String nebulosite) {
            this.code_ville = new SimpleStringProperty(code_ville);
            this.temp_c = new SimpleStringProperty(temp_c);
            this.temp_k = new SimpleStringProperty(temp_k);
            this.humidite = new SimpleStringProperty(humidite);
            this.nebulosite = new SimpleStringProperty(nebulosite);
        }

        public String getTemp_k() {
            return temp_k.get();
        }

        public String getTemp_c() {
            return temp_c.get();
        }

        public String getHumidite() {
            return humidite.get();
        }

        public String getNebulosite() {
            return nebulosite.get();
        }

        public void setTemp_k(String Tk) {
            temp_k.set(Tk);
        }

        public void setTemp_c(String Tc) {
            temp_c.set(Tc);
        }

        public void setCode_ville(String Tc) {
            code_ville.set(Tc);
        }

        public void setHumidite(String h) {
            humidite.set(h);
        }

        public void setNebulosite(String nb) {
            nebulosite.set(nb);
        }

    }


    
    
    @FXML
    private void importer_fichier(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Ouvrir un fichier");
        File file = filechooser.showOpenDialog(null);
        if (file != null) {
            String[] extensions = {"txt", "csv"};
            for (String extension : extensions) {
                if (file.getName().toLowerCase().endsWith("." + extension)) {
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        final String fileName = file.toURI().toString();
                        String fileNamelast = null;

                        for (String retval : fileName.split("/")) {
                            fileNamelast = retval;
                        }
                        liste.getItems().add(fileNamelast);

                        ObservableList<information> data = FXCollections.observableArrayList();
                        List<String> row = new ArrayList<>();
                        String line;
//                         donnes = new ArrayList<>();
                        boolean line_one = false;
                        while ((line = br.readLine()) != null) {
                            if (line_one) {
                                row.clear();
                                //   donnes.add(line);
                                int i = 0;
                                for (String retval : line.split(";")) {

                                    if (i == 0 || i == 2 || i == 7 || i == 9 || i == 12 || i == 13) {
                                        row.add(retval);
                                    }

                                    i++;
                                }

                                //Conversion Kelvin to celsius
                                Float kelvin = Float.parseFloat(row.get(0));
                                Float celsius = kelvin - 273.15F;

                                data.add(new information(row.get(0), row.get(3), Float.toString(celsius), row.get(1), row.get(2)));
                            }
                            line_one = true;
                        }
                        TableColumn c0 = new TableColumn("code ville");
                        c0.setMinWidth(100);
                        c0.setCellValueFactory(new PropertyValueFactory<information, String>("code_ville"));

                        TableColumn c = new TableColumn("TempÃ©rature K");
                        c.setMinWidth(100);
                        c.setCellValueFactory(new PropertyValueFactory<information, String>("temp_k"));

                        TableColumn c1 = new TableColumn("TempÃ©rature C");
                        c1.setMinWidth(100);
                        c1.setCellValueFactory(new PropertyValueFactory<information, String>("temp_c"));

                        TableColumn c2 = new TableColumn("HumiditÃ© %");
                        c2.setMinWidth(100);
                        c2.setCellValueFactory(new PropertyValueFactory<information, String>("humidite"));

                        TableColumn c3 = new TableColumn("NebulositÃ© %");
                        c3.setMinWidth(100);
                        c3.setCellValueFactory(new PropertyValueFactory<information, String>("nebulosite"));

                        table_donne.setItems(data);
                        table_donne.getColumns().addAll(c0, c, c1, c2, c3);

                        //table_donne2.setItems(data);
                       // table_donne2.getColumns().addAll(c0, c, c1, c2, c3);

                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
        }
    }

    
    @FXML
    private void reglage_Annee() {
        get_date d = new get_date();
        String Annee = d.get_annee_encour();
        for (int i = Integer.parseInt(Annee); i > 1995; i--) {
            ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
            T_annee.getItems().addAll(t);
            c_annee1.getItems().addAll(t);
            c_annee2.getItems().addAll(t);
        }

    }

    public void reglage_mois(String An) {
        get_date d = new get_date();
        String Mois = d.get_mois_encour();
        String Jour = d.get_jour_encour();
        if (d.get_annee_encour().equals(An)) {
            for (int i = 1; i < Integer.parseInt(Mois) + 1; i++) {
                ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                T_mois.getItems().addAll(t);
                
            }

        } else {
            for (int i = 1; i < 13; i++) {
                ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                T_mois.getItems().addAll(t);
                  c_mois1.getItems().addAll(t);

                    c_mois2.getItems().addAll(t);
            }
        }

    }

    public void reglage_jour_heure(String mo) {

        get_date d = new get_date();
        String Jour = d.get_jour_encour();
        if (d.get_mois_encour().equals(mo)) {
            for (int i = 1; i < Integer.parseInt(Jour) + 1; i++) {
                ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                T_jour.getItems().addAll(t);
            }

        } else {
            if (Integer.parseInt(mo) == 1 || Integer.parseInt(mo) == 3
                    || Integer.parseInt(mo) == 5 || Integer.parseInt(mo) == 10 || Integer.parseInt(mo) == 12
                    || Integer.parseInt(mo) == 7 || Integer.parseInt(mo) == 8) {
                for (int i = 1; i < 32; i++) {
                    ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                    T_jour.getItems().addAll(t);
                }
            } else {
                for (int i = 1; i < 31; i++) {
                    ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(i));
                    T_jour.getItems().addAll(t);
                }
            }
        }
        for (int j = 0; j < 22; j = j + 3) {
            ObservableList<String> t = FXCollections.observableArrayList(Integer.toString(j));
            T_heur.getItems().addAll(t);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        Station stationX = null;
//        
//
//        System.out.println(stationX.nom_villes().length + " " + stationX.id_villes().length);
//        for (int i = 0; i < stationX.id_villes().length; i++) {
//             station.add(new Station(stationX.id_villes()[i], stationX.nom_villes()[i]));
//        }
        

        
        
        reglage_Annee();
        T_mois.setDisable(true);
        T_jour.setDisable(true);
        T_heur.setDisable(true);
        telechargement.setDisable(true);

        
        telechargement.setOnAction(event ->{
        outil_telechargement telecharger=new outil_telechargement();
        get_date d = new get_date();
        String Annee = d.get_annee_encour();
        String moiss =T_mois.getSelectionModel().getSelectedItem().toString();
                if (Integer.parseInt(moiss)<10) moiss="0"+moiss;
                
        String jourr =T_jour.getSelectionModel().getSelectedItem().toString();
        if (Integer.parseInt(jourr)<10) jourr="0"+jourr;
        if (!T_annee.getSelectionModel().getSelectedItem().toString().equals(Annee)){
            try {
                telecharger.telechargement_gz(T_annee.getSelectionModel().getSelectedItem().toString()+
                        moiss);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            try {
               String heure= T_heur.getSelectionModel().getSelectedItem().toString();
               if (Integer.parseInt(heure)<10) heure="0"+heure;
                telecharger.telechargement_csv(
                        T_annee.getSelectionModel().getSelectedItem().toString()+
                                moiss+
                                jourr+
                                heure);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
        
        });
        // pour le jour
        T_mois.setOnAction((event) -> {
            reglage_jour_heure(T_mois.getSelectionModel().getSelectedItem().toString());

            T_jour.setDisable(false);
            T_heur.setDisable(false);
            telechargement.setDisable(false);
        });

        // pour le mois 
        T_annee.setOnAction((event) -> {

            reglage_mois(T_annee.getSelectionModel().getSelectedItem().toString());
            T_mois.setDisable(false);
        });
    }

}
