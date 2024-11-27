package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username;
        List<Ordner> alleOrdner = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Gebe deinen Namen ein: ");
        username = scanner.nextLine();

        User user = new User(username);

        checkinstanz(username, scanner, alleOrdner);
    }

    static void checkinstanz(String username, Scanner scanner, List<Ordner> alleOrdner) {
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
            System.out.print("Gebe deinen Namen ein: ");
            username = scanner.nextLine();
            checkinstanz(username, scanner, alleOrdner);

        } else if (auswahl == 2) {
            String ordnername;
            System.out.println("Gib einen Namen für deinen Ordner ein: ");
            ordnername = scanner.nextLine();

            Ordner ordner = new Ordner(ordnername);

            alleOrdner.add(ordner);
            checkinstanz(username, scanner, alleOrdner);


        } else if (auswahl == 3) {

            if (alleOrdner.isEmpty()) {
                System.out.print("Es gibt keine Ordner, bitte erstelle zuerst ein Ordner! ");
                checkinstanz(username, scanner, alleOrdner);
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

                checkinstanz(username, scanner, alleOrdner);
            }
        }
    }
}