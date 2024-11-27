package org.example;

import java.util.ArrayList;
import java.util.List;

public class Ordner{
    private String name;
    private List<Notiz> notizen;


    public Ordner(String name){
        notizen = new ArrayList<Notiz>();
        this.name = name;
    }

    public void addNotiz(Notiz notiz){
        notizen.add(notiz);
    }

    public List<Notiz> getNotizen(){
        return notizen;
    }

    public String getName(){
        return name;
    }


}
