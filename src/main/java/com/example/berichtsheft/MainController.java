package com.example.berichtsheft;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class MainController {
    public Label lbl_file_reader;
    public Menu men_report;
    public ListView<Report> lsv_reportList;

    File loadedFile;

    ArrayList<Report> reports = new ArrayList<>();

    public void openFileReader(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JSON");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
        loadedFile = fileChooser.showOpenDialog(new Stage());

        reports = XMLReader.getReportsFromXML(loadedFile);
        displayData();
        men_report.setDisable(false);
    }

    public void displayData(){
        StringBuilder tempText = new StringBuilder();
        for (int i = 0; i < reports.size(); i++){
            tempText.append(reports.get(i).debug());
        }

        lsv_reportList.getItems().addAll(reports);

        System.out.println(tempText);
        System.out.println("Done");
    }

}