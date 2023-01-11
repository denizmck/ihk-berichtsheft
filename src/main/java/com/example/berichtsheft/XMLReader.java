package com.example.berichtsheft;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
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

            NodeList nodeList = document.getElementsByTagName("report");

            Report tempReport = new Report();
            ArrayList<Entry> tempEntries = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){

                }
            }

        } catch (Exception e){

        }
        return new ArrayList<>();
    }

}
