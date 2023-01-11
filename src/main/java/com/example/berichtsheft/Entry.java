package com.example.berichtsheft;

import java.util.ArrayList;

public class Entry {

    String title;
    ArrayList<String> items;

    public Entry(){
        items = new ArrayList<>();
    }

    public Entry(String title){
        this.title = title;
        items = new ArrayList<>();
    }

    public Entry(String title, ArrayList<String> items){
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addItem(String item){
        items.add(item);
    }

    public void deleteItem(String item){
        items.remove(item);
    }

    public void deleteItemAtIndex(int index){
        items.remove(index);
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public String getItemAtIndex(int index){
        return items.get(index);
    }
}
