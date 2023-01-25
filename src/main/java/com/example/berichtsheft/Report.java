package com.example.berichtsheft;

import java.util.ArrayList;

public class Report {

    int id;
    String startDate;
    String endDate;
    String department;
    ArrayList<Entry> entries;

    public Report(int id){
        this.id = id;
        entries = new ArrayList<>();
    }

    public Report(int id, String startDate, String endDate, String department){
        this.id = id;
        this.startDate  = startDate;
        this.endDate    = endDate;
        this.department = department;
        entries = new ArrayList<>();
    }

    public Report(int id, String startDate, String endDate, String department, ArrayList<Entry> entries){
        this.id = id;
        this.startDate  = startDate;
        this.endDate    = endDate;
        this.department = department;
        this.entries = entries;
    }

    public int getId(){
        return id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void addEntry(Entry newEntry){
        entries.add(newEntry);
    }

    public void deleteEntry(Entry entryToDelete){
        entries.remove(entryToDelete);
    }

    public void deleteEntryAtIndex(int index){
        entries.remove(index);
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public Entry getEntryAtIndex(int index){
        return entries.get(index);
    }

    public String getDateString(){
        return startDate + " - " + endDate;
    }
}
