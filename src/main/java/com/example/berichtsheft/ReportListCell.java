package com.example.berichtsheft;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportListCell extends ListCell<Report> {

    @FXML
    public Label lbl_date;
    @FXML
    public Label lbl_department;
    @FXML
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

    @FXML
    public void openReportWindow(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("./com/example/berichtsheft/report-view.fxml"));

        Scene reportView = new Scene(fxmlLoader.load());
        Stage reportViewWindow = new Stage();
        reportViewWindow.setTitle("Report Viewer");
        reportViewWindow.setScene(reportView);
        reportViewWindow.show();
    }

}
