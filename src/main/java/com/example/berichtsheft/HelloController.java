package com.example.berichtsheft;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class HelloController {
    public Label lbl_file_reader;
    public TextField txt_file_reader;
    public Button btn_file_reader;
    public TextArea txa_json;

    File loadedFile;

    ArrayList<Report> reports = new ArrayList<>();

    public void openFileReader(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JSON");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
        loadedFile = fileChooser.showOpenDialog(new Stage());

        updateFileTextField();
        XMLReader.getReportsFromXML(loadedFile);
    }

    void updateFileTextField(){
        txt_file_reader.setText(loadedFile.getAbsolutePath());
    }
}