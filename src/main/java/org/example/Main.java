package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Gebe deinen Namen ein: ");
        username = scanner.nextLine();

        User user = new User(username);

        checkinstanz(username, scanner);
    }

    static void checkinstanz(String username, Scanner scanner) {
        int auswahl;
        List<String> alleOrdner = new ArrayList<>();

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
            checkinstanz(username, scanner);

        } else if (auswahl == 2) {
            String ordnername;
            System.out.println("Gib einen Namen für deinen Ordner ein: ");
            ordnername = scanner.nextLine();

            Ordner ordner = new Ordner(ordnername);

            alleOrdner.add(ordnername);
            checkinstanz(username, scanner);


        } else if (auswahl == 3) {
            System.out.println(alleOrdner);
            // check if there are any ordner Objects

            







            //if (alleOrdner.isEmpty()) {
            //  System.out.println("Keine Ordner, bitte zuerst erstellen!");
            // } else {
            //    System.out.println("Welchen Ordner wählst du aus?");

        }

            // Choose which ordner you want to create Notes

            // Create Note






        }
    }