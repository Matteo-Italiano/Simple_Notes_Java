package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Tostirng, Notizen ändern odner löschen usw.

public class Main {
    public static void main(String[] args) {
        String username;
        List<Ordner> alleOrdner = new ArrayList<>();
        List<User> alleUser = new ArrayList<>();
        List<User> currentUser = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Gebe deinen Namen ein: ");
        username = scanner.nextLine();

        User user = new User(username);
        alleUser.add(user);
        currentUser.add(user);

        checkinstanz(username, scanner, alleUser, currentUser);
    }

    static void checkinstanz(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        User user = currentUser.getLast();
        user.auswahlOption(username, scanner, alleUser, currentUser);

    }

    static void userSwitch(String username, String benutzeroption, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        if (benutzeroption.equalsIgnoreCase("N")) {
            System.out.println("Gib einen Namen ein: ");
            username = scanner.nextLine();

            User newUser = new User(username);
            alleUser.add(newUser);
            currentUser.add(newUser);

            checkinstanz(username, scanner, alleUser, currentUser);
        } else {

            int benutzerindex = 0;

            try {
                benutzerindex = Integer.parseInt(benutzeroption) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Gib eine gültige Zahl ein: ");
                currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
            }

            User newUser = alleUser.get(benutzerindex);
            username = alleUser.get(benutzerindex).getName();
            currentUser.add(newUser);
            checkinstanz(username, scanner, alleUser, currentUser);
        }
    }
}