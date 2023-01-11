package com.example.berichtsheft;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import javafx.scene.control.Alert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLReader {

    public static ArrayList<Report> getReportsFromXML(File file){

        ArrayList<Report> reportList = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("report");

            for(int i=0; i < list.getLength(); i++){
                Report tempReport = new Report();
                Node reportNode = list.item(i);
                if(reportNode.getNodeType() == Node.ELEMENT_NODE){
                    Element reportElement = (Element) reportNode;

                    // Dates
                    NodeList dateList = reportElement.getElementsByTagName("date");
                    for(int n=0; n < dateList.getLength(); n++){
                        Node dateNode = dateList.item(n);
                        if(dateNode.getNodeType() == Node.ELEMENT_NODE){
                            Element dateElement = (Element) dateNode;
                            tempReport.setStartDate(dateElement.getElementsByTagName("from").item(0).getTextContent());
                            tempReport.setEndDate(dateElement.getElementsByTagName("to").item(0).getTextContent());
                        }
                    }

                    // Department
                    tempReport.setDepartment(reportElement.getElementsByTagName("department").item(0).getTextContent());

                    // Entries
                    ArrayList<Entry> tempEntries = new ArrayList<>();
                    NodeList entryListList = reportElement.getElementsByTagName("entrylist");
                    for (int n=0; n < entryListList.getLength(); n++){
                        Node entrylistNode = entryListList.item(n);
                        if(entrylistNode.getNodeType() == Node.ELEMENT_NODE){
                            Element entryListElement = (Element) entrylistNode;
                            Entry tempEntry = new Entry();

                            NodeList entryList = entryListElement.getElementsByTagName("entry");
                            for (int y=0; y < entryList.getLength(); y++){
                                Node entryNode = entryList.item(y);
                                if(entryNode.getNodeType() == Node.ELEMENT_NODE){
                                    Element entryElement = (Element) entryNode;
                                    tempEntry.setTitle(entryElement.getAttribute("title"));
                                    for(int idx=0; idx < entryElement.getElementsByTagName("item").getLength(); idx++){
                                        tempEntry.addItem(entryElement.getElementsByTagName("item").item(idx).getTextContent());
                                    }
                                }
                            }

                            tempReport.addEntry(tempEntry);
                        }
                    }

                }
                tempReport.debug();
            }


        } catch (Exception e){
            showAlertMessage();
        }

        return reportList;
    }

    private static NodeList loopThroughNodes(NodeList nodeList){
        return new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };
    }

    private static void showAlertMessage(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Datei wird nicht unterstützt");
        errorAlert.setContentText("Die Datei muss XML sein und der richtigen Struktur entsprechen!");
        errorAlert.showAndWait();
    }

}
