package org.example;

public class Notiz {
     private String titel;
     private String text;

    public Notiz(String titel, String text){
        this.titel = titel;
        this.text = text;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void changeText(String newText){
        this.text = newText;
    }

    public void changeTitle(String newTitle){
        this.titel = newTitle;
    }
}
