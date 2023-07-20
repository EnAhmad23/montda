package com.example.test_javafx;

import java.util.ArrayList;

public class DataBus {
   public static ArrayList<String> data =new ArrayList<>();
    public DataBus(ArrayList<String> data){
        this.data = data;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
