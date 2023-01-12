package com.example.berichtsheft;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import javafx.scene.control.Alert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLReader {

    /***
     * Opens a documentBuilder instance to read given XML
     * Following in constructing a list of Reports from the given XML-File
     * @param file XML File that needs to be in the format for this programm
     * @return A list of all Reports in a XML file
     */
    public static ArrayList<Report> getReportsFromXML(File file){
        ArrayList<Report> reportList = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();

            getReports(document, reportList);

        } catch (Exception e){
            showAlertMessage();
        }

        return reportList;
    }

    /***
     * Generic Method to loop through the nodes of an XML-File and calls a callback method
     * that injects logic handling the Element resulting from the loop
     * @param nodeList List of Nodes to be looped through
     * @param callback an Method that will be called on every loop handling the resulting Element
     */
    private static void loopThroughNodes(NodeList nodeList, Consumer<Element> callback){
        for(int i=0; i < nodeList.getLength(); i++){
            Node reportNode = nodeList.item(i);
            if(reportNode.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) reportNode;
                callback.accept(element);
            }
        }
    }

    /***
     * First layer of XML-File
     * Getting all Reports and calling all methods to gather the data of the lower layers
     * @param document document from documentBuilder containing the XML-File
     * @param reportList reference to the ArrayList containing the Reports
     */
    private static void getReports(Document document, ArrayList<Report> reportList){
        NodeList list = document.getElementsByTagName("report");

        loopThroughNodes(list, new Consumer<Element>() {
            @Override
            public void accept(Element element) {
                Report tempReport = new Report();

                getDateOfReport(element, tempReport);
                getDepartmentOfReport(element, tempReport);
                getEntries(element, tempReport);

                tempReport.debug();
                reportList.add(tempReport);
            }
        });
    }

    /***
     * Lower Layer of XML-File
     * Getting all entries of a Report and attaching it to the given Report-Object
     * @param reportElement element of the Report-Layer
     * @param tempReport reference to the Report-Object
     */
    private static void getEntries(Element reportElement, Report tempReport){
        Element entryListElement = (Element) reportElement.getElementsByTagName("entrylist").item(0);
        NodeList entryList = entryListElement.getElementsByTagName("entry");

        loopThroughNodes(entryList, new Consumer<Element>() {
            @Override
            public void accept(Element element) {
                Entry tempEntry = new Entry();
                tempEntry.setTitle( element.getAttribute("title") );

                int itemLength = element.getElementsByTagName("item").getLength();
                for(int i=0; i < itemLength; i++){
                    tempEntry.addItem(getTextContent(element, "item", i));
                }

                tempReport.addEntry(tempEntry);
            }
        });
    }

    /***
     * Lower Layer of XML-File
     * Getting the from and to Date as a String and attaching it to the given Report-Object
     * @param reportElement element of the Report-Layer
     * @param report reference to the Report-Object
     */
    private static void getDateOfReport(Element reportElement, Report report){
        Element dateElement = (Element) reportElement.getElementsByTagName("date").item(0);
        report.setStartDate( getTextContent(dateElement, "from") );
        report.setEndDate( getTextContent(dateElement, "to") );
    }

    /***
     * Lower Layer of XML-File
     * Getting the department info and attaching it to the given Report-Object
     * @param reportElement element of the Report-Layer
     * @param report reference to the Report-Object
     */
    private static void getDepartmentOfReport(Element reportElement, Report report){
        report.setDepartment( getTextContent(reportElement, "department") );
    }

    /***
     * Shorthand for getting the String content of the first Element in an Element list
     * @param element element list containing the element
     * @param tag tag of the element
     * @return Text content of the element
     */
    private static String getTextContent(Element element, String tag){
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    /***
     * Shorthand for getting the String content of the Element with the given Index
     * @param element element list containing the element
     * @param tag tag of the element
     * @param index index of the element in the list
     * @return Text content of the element
     */
    private static String getTextContent(Element element, String tag, int index){
        return element.getElementsByTagName(tag).item( index ).getTextContent();
    }

    /***
     * JavaFX Alert Box showing up when loading the XML-File fails
     */
    private static void showAlertMessage(){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Datei wird nicht unterstützt");
        errorAlert.setContentText("Die Datei muss XML sein und der richtigen Struktur entsprechen!");
        errorAlert.showAndWait();
    }

}
