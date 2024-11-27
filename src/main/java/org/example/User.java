package org.example;

import java.util.ArrayList;
import java.util.List;

public class User{
    private String name;
    private List<Ordner> ordner;

    public User(String name){
        ordner = new ArrayList<Ordner>();
        this.name = name;
    }


    public List<Ordner> getAllOrdner(){
        return ordner;
    }


}
