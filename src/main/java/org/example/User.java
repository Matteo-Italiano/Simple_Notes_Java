package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private List<Ordner> ordner;

    public User(String name) {
        ordner = new ArrayList<Ordner>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Ordner> getAllOrdner() {
        return ordner;
    }

    public void checkinstanz(String username, Scanner scanner, List<Ordner> alleOrdner, List<User> alleUser){
        int ordnerZahl;
        int auswahl;


        String options = String.format("Was möchtest du machen %s? %s %s %s", username,
                "\n 1: Benutzer wechseln",
                "\n 2: Ordner erstellen",
                "\n 3: Notiz erstellen");
        System.out.println(options);
        auswahl = scanner.nextInt();
        scanner.nextLine();

        if (auswahl == 1) {
            int count = 1;
            System.out.println("Auf welchen Benutzer möchtest du wechseln?");

            for (User i : alleUser) {
                System.out.println(count + ": " + i.getName());
                count ++;
            }

            System.out.println(" \n N: Neuen Benutzer erstellen");

            String benutzeroption = scanner.nextLine();


            if (benutzeroption.toString().toUpperCase().equals("N")){
                System.out.println("Gib einen Namen ein: ");
                username = scanner.nextLine();

                User user = new User(username);
                alleUser.add(user);

                user.checkinstanz(username, scanner, alleOrdner, alleUser);
            } else {
                int benutzerindex = Integer.parseInt(benutzeroption) - 1;
                User user = alleUser.get(benutzerindex);
                username  = alleUser.get(benutzerindex).getName();
                user.checkinstanz(username, scanner, alleOrdner, alleUser);
            }


        } else if (auswahl == 2) {
            String ordnername;
            System.out.println("Gib einen Namen für deinen Ordner ein: ");
            ordnername = scanner.nextLine();

            Ordner ordner = new Ordner(ordnername);

            alleOrdner.add(ordner);
            checkinstanz(username, scanner, alleOrdner, alleUser);


        } else if (auswahl == 3) {

            if (alleOrdner.isEmpty()) {
                System.out.print("Es gibt keine Ordner, bitte erstelle zuerst ein Ordner! ");
                checkinstanz(username, scanner, alleOrdner, alleUser);
            } else {

                int counter = 1;

                for (Ordner ordner : alleOrdner) {
                    System.out.println(counter + ":" + " " + ordner.getName());
                    counter = counter + 1;
                }

                System.out.println("Welchen Ordner möchtest du auswählen?");

                ordnerZahl = scanner.nextInt();

                int ausgewaehlterOrdnerIndex = ordnerZahl - 1;

                Ordner ausgewaehlterOrdner = alleOrdner.get(ausgewaehlterOrdnerIndex);
                String garnichts = scanner.nextLine();

                System.out.print("Titel: ");
                String titel = scanner.nextLine();

                System.out.print("Text: ");
                String text = scanner.nextLine();

                Notiz notiz = new Notiz(titel, text);

                ausgewaehlterOrdner.addNotiz(notiz);

                List<Notiz> alleNotizen = new ArrayList<>();

                alleNotizen = ausgewaehlterOrdner.getNotizen();

                int count = 1;

                for (Notiz i : alleNotizen) {
                    System.out.println("Notiz Nr. " + count);
                    count++;

                    String fullNote = "Titel: " + i.getTitel() + "\n" +
                                      "Text: " + i.getText();

                    System.out.println(fullNote);
                }

                checkinstanz(username, scanner, alleOrdner, alleUser);
            }
        }
    }


}
