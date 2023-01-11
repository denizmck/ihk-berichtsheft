package com.example.berichtsheft;

import java.util.ArrayList;

public class Report {

    String startDate;
    String endDate;
    String department;
    ArrayList<Entry> entries;

    public Report(){
        entries = new ArrayList<>();
    }

    public Report(String startDate, String endDate, String department){
        this.startDate  = startDate;
        this.endDate    = endDate;
        this.department = department;
        entries = new ArrayList<>();
    }

    public Report(String startDate, String endDate, String department, ArrayList<Entry> entries){
        this.startDate  = startDate;
        this.endDate    = endDate;
        this.department = department;
        this.entries = entries;
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

    public void debug(){
        System.out.println("From       : " + startDate);
        System.out.println("To         : " + endDate);
        System.out.println("Department : " + department);
        for(int i=0; i < entries.size(); i++){
            Entry tempEntry = entries.get(i);
            System.out.println("\n" + tempEntry.getTitle());
            for(int n=0; n < tempEntry.items.size(); n++){
                System.out.println("- " + tempEntry.items.get(n));
            }
        }
        System.out.println("\n-------------------------------------------\n");
    }
}
