package com.example.berichtsheft;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class HelloController {
    public Label lbl_file_reader;
    public TextArea txa_json;
    public Menu men_report;

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

        txa_json.setText(tempText.toString());
        System.out.println(tempText);
        System.out.println("Done");
    }

}