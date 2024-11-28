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
        int ordnerZahl = 0;

        String options = String.format("Was möchtest du machen %s? %s %s %s %s", username,
                "\n 1: Benutzer wechseln",
                "\n 2: Ordner erstellen",
                "\n 3: Notiz erstellen",
                "\n 4: Programm beenden");

        System.out.println(options);


        if (scanner.hasNextInt()) {
            auswahl = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Gib eine gültige Zahl ein.");
            scanner.nextLine();
            currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
            return;
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

                currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
                break;
            case 3:
                if (ordnerVonUser.isEmpty()) {
                    System.out.println("Es gibt keine Ordner, bitte erstelle zuerst ein Ordner! ");
                    currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
                } else {

                    int counter = 1;

                    for (Ordner ordner : ordnerVonUser) {
                        System.out.println(counter + ":" + " " + ordner.getName());
                        counter = counter + 1;
                    }

                    System.out.println("Welchen Ordner möchtest du auswählen?");

                    try {
                        ordnerZahl = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Gib eine gültige Zahl ein!");
                        currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
                    }


                    int ausgewaehlterOrdnerIndex = ordnerZahl - 1;

                    Ordner ausgewaehlterOrdner = ordnerVonUser.get(ausgewaehlterOrdnerIndex);

                    try {
                        ausgewaehlterOrdner = ordnerVonUser.get(ausgewaehlterOrdnerIndex);
                        scanner.nextLine();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Dieser Ordner existiert nicht, gib eine gültige Zahl ein!");
                        currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
                    }


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
                    currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
                }

                break;
            case 4:
                System.out.println("Programm Beendet!");
                return;
            default:
                System.out.println("Ungügltige Zahl!");
                currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
        }
    }
}







