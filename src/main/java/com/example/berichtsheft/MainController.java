package com.example.berichtsheft;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ObservableList<Report> reportObservableList;

    public MainController(){
        reportObservableList = FXCollections.observableArrayList();
    }

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
        reportObservableList.clear();
        reportObservableList.addAll(reports);

        lsv_reportList.setItems(reportObservableList);
        lsv_reportList.setCellFactory(reportListView -> new ReportListCell());
    }

}