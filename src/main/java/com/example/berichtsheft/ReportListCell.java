package com.example.berichtsheft;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReportListCell extends ListCell<Report> {

    public Label lbl_date;
    public Label lbl_department;
    public AnchorPane anchorPane;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Report report, boolean empty){
        super.updateItem(report, empty);

        if(empty || report == null){
            setText(null);
            setGraphic(null);
        }
        else {
            if(fxmlLoader == null){
                loadFXML();
            }

            lbl_date.setText(report.getDateString());
            lbl_department.setText(report.getDepartment());

            setText(null);
            setGraphic(anchorPane);
        }
    }

    private void loadFXML(){
        fxmlLoader = new FXMLLoader(getClass().getResource("ReportListCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
