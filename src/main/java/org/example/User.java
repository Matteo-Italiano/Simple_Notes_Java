package org.example;

import java.util.*;

public class User {
    private String name;
    private List<Ordner> ordnerVonUser;

    public User(String name) {
        ordnerVonUser = new ArrayList<Ordner>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void auswahlOption(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int auswahl = 0;
        int ordnerZahl;

        String options = String.format("Was möchtest du machen %s? %s %s %s %s", username,
                "\n 1: Benutzer wechseln",
                "\n 2: Ordner erstellen",
                "\n 3: Notiz erstellen",
                "\n 4: Programm beenden");

        System.out.println(options);


        try {
            auswahl = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Gib eine gültige Zahl ein");
            scanner.nextLine();
            currentUser.get(currentUser.size() - 1).auswahlOption(username, scanner, alleUser, currentUser);
        }


        switch (auswahl) {
            case 1:
                int count = 1;
                System.out.println("Auf welchen Benutzer möchtest du wechseln?");

                for (User i : alleUser) {
                    System.out.println(count + ": " + i.getName());
                    count++;
                }

                System.out.println(" \n N: Neuen Benutzer erstellen");

                String benutzeroption = scanner.nextLine();

                Main.userSwitch(username, benutzeroption, scanner, alleUser, currentUser);
                break;
            case 2:
                String ordnername;
                System.out.println("Gib einen Namen für deinen Ordner ein: ");
                ordnername = scanner.nextLine();

                Ordner ordners = new Ordner(ordnername);
                ordnerVonUser.add(ordners);

                currentUser.get(currentUser.size() - 1).auswahlOption(username, scanner, alleUser, currentUser);
                break;
            case 3:
                if (ordnerVonUser.isEmpty()) {
                    System.out.println("Es gibt keine Ordner, bitte erstelle zuerst ein Ordner! ");
                    currentUser.get(currentUser.size() - 1).auswahlOption(username, scanner, alleUser, currentUser);
                } else {

                    int counter = 1;

                    for (Ordner ordner : ordnerVonUser) {
                        System.out.println(counter + ":" + " " + ordner.getName());
                        counter = counter + 1;
                    }

                    System.out.println("Welchen Ordner möchtest du auswählen?");

                    ordnerZahl = scanner.nextInt();

                    int ausgewaehlterOrdnerIndex = ordnerZahl - 1;

                    Ordner ausgewaehlterOrdner = ordnerVonUser.get(ausgewaehlterOrdnerIndex);
                    scanner.nextLine();

                    System.out.print("Titel: ");
                    String titel = scanner.nextLine();

                    System.out.print("Text: ");
                    String text = scanner.nextLine();

                    Notiz notiz = new Notiz(titel, text);

                    ausgewaehlterOrdner.addNotiz(notiz);

                    List<Notiz> alleNotizen;

                    alleNotizen = ausgewaehlterOrdner.getNotizen();

                    int counterForNotes = 1;

                    for (Notiz i : alleNotizen) {
                        System.out.println("Notiz Nr. " + counterForNotes);
                        counterForNotes++;

                        String fullNote = "Titel: " + i.getTitel() + "\n" +
                                "Text: " + i.getText();

                        System.out.println(fullNote);
                    }
                    currentUser.get(currentUser.size() - 1).auswahlOption(username, scanner, alleUser, currentUser);
                }

                break;
            case 4:
                System.out.println("Programm Beendet!");
                return;
            default:
                System.out.println("Ungügltige Zahl!");
        }
    }
}







