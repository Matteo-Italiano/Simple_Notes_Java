package org.example;

import java.util.*;

public class User {
    private String name;
    private List<Ordner> ordnerVonUser;

    public User(String name) {
        ordnerVonUser = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", ordnerVonUser=" + ordnerVonUser + "]";
    }

    public void auswahlOption(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int auswahl = 0;

        String options = String.format("\n Was möchtest du machen %s? %s %s %s %s %s %s %s %s %s %s %s", username,
                "\n 1: Benutzer wechseln",
                "\n 2: Benutzer bearbeiten",
                "\n 3: Ordner erstellen",
                "\n 4: Ordner bearbeiten",
                "\n 5: Ordner Löschen",
                "\n 6: Ordner Anzeigen",
                "\n 7: Notiz erstellen",
                "\n 8: Notiz bearbeiten",
                "\n 9: Notiz Löschen",
                "\n 10 Notizen Anzeigen",
                "\n 11: Programm beenden");

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
                currentUser.getLast().benutzerWechseln(username, scanner, alleUser, currentUser);
                break;

            case 2:
                currentUser.getLast().benutzerBearbeiten(username, alleUser, currentUser, scanner);
                break;

            case 3:
                currentUser.getLast().createOrdner(username, scanner, alleUser, currentUser);
                break;

            case 4:
                currentUser.getLast().ordnerBearbeiten(username, scanner, currentUser, alleUser);
                break;

            case 5:
                currentUser.getLast().deleteFolder(username, scanner, alleUser, currentUser);
                break;

            case 6:
                currentUser.getLast().showFolder(username, scanner, alleUser, currentUser);
                break;

            case 7:
                currentUser.getLast().createNote(username, scanner, alleUser, currentUser);
                break;

            case 8:
                currentUser.getLast().noteBearbeiten(username, scanner, alleUser, currentUser);

            case 9:
                currentUser.getLast().deleteNote(username, scanner, alleUser, currentUser);
                break;

            case 10:
                currentUser.getLast().showNotes(username, scanner, alleUser, currentUser);
                break;

            case 11:
                System.out.println("Programm Beendet!");
                break;

            default:
                System.out.println("Ungügltige Zahl!");
                currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
        }
    }

    public void benutzerWechseln(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int countCase1 = 1;
        System.out.println("Auf welchen Benutzer möchtest du wechseln?");

        for (User i : alleUser) {
            System.out.println(countCase1 + ": " + i.getName());
            countCase1++;
        }

        System.out.println(" \n N: Neuen Benutzer erstellen");

        String benutzeroption = scanner.nextLine();

        Main.userSwitch(username, benutzeroption, scanner, alleUser, currentUser);
    }

    public void benutzerBearbeiten(String username, List<User> alleUser, List<User> currentUser, Scanner scanner) {
        int countCase2 = 1;
        int auswahlCase2 = 1;

        for (User i : alleUser) {
            System.out.println(countCase2 + ": " + i.getName());
            countCase2++;
        }

        System.out.println("Welchen Benutzer möchtest du bearbeiten?");

        try {
            auswahlCase2 = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Gib eine gültige Zahl ein.");
        }

        User ausgewaehlterUser = alleUser.get(auswahlCase2 - 1);

        String anfrageUmbenennen = String.format("Wie möchtest du %s umbenennen?", ausgewaehlterUser.getName());
        System.out.println(anfrageUmbenennen);
        username = scanner.nextLine();

        username = scanner.nextLine();
        ausgewaehlterUser.setName(username);
        currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
    }

    public void createOrdner(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        String ordnername;
        System.out.println("Gib einen Namen für deinen Ordner ein: ");
        ordnername = scanner.nextLine();

        Ordner ordners = new Ordner(ordnername);
        ordnerVonUser.add(ordners);

        currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);

    }

    public void ordnerBearbeiten(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int ordnerZahl = 0;

        if (ordnerVonUser.isEmpty()) {
            System.out.println("Es gibt keine Ordner, bitte erstelle zuerst ein Ordner! ");
            currentUser.getLast().createOrdner(username, scanner, alleUser, currentUser);
        } else {

            int counter = 1;

            for (Ordner ordner : ordnerVonUser) {
                System.out.println(counter + ":" + " " + ordner.getName());
                counter = counter + 1;
            }

            System.out.println("Welchen Ordner möchtest du bearbeiten?");

            try {
                ordnerZahl = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Gib eine gültige Zahl ein!");
                currentUser.getLast().ordnerBearbeiten(username, scanner, alleUser, currentUser);
            }

            ordnerVonUser.get(ordnerZahl - 1).setName(username);

            String newOrdnerNameRequest = String.format("Gib einen neuen Namen für den Ordner %s ein", ordnerVonUser.get(ordnerZahl - 1).getName());
            System.out.println(newOrdnerNameRequest);
            scanner.nextLine();

            String newOrdnerName = scanner.nextLine();
            ordnerVonUser.get(ordnerZahl - 1).setName(newOrdnerName);

            currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);

        }
    }

    public void createNote(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int ordnerZahl = 0;

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
                currentUser.getLast().createNote(username, scanner, alleUser, currentUser);
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
    }

    public void noteBearbeiten(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int ordnerZahl = 0;

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

            List<Notiz> alleNotizen = ausgewaehlterOrdner.getNotizen();
            int ausgewaehlteNotiz = 0;

            int counterForNotes = 1;

            for (Notiz i : alleNotizen) {
                System.out.println("Notiz Nr. " + counterForNotes + "\n"
                        + i.getTitel() + "\n"
                        + i.getText());
                counterForNotes++;
            }

            System.out.println("Welche Notiz möchtest du bearbeiten?");
            try {
                ausgewaehlteNotiz = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Gib eine gültige Zahl ein!");
                currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
            }

            int ausgewaehlteNotizindex = ausgewaehlteNotiz - 1;

            Notiz notizZumbearbeiten = alleNotizen.getFirst();

            try {
                notizZumbearbeiten = alleNotizen.get(ausgewaehlteNotizindex);
            } catch (Exception e) {
                System.out.println("Diese Notiz existiert nicht, gib eine gültige Zahl ein!");
                currentUser.getLast().noteBearbeiten(username, scanner, alleUser, currentUser);
            }

            System.out.println("Gib einen neuen Titel für die Notiz ein: ");
            String newTitel = scanner.nextLine();
            newTitel = scanner.nextLine();


            System.out.println("Gib einen neuen Text für die Notiz ein: ");
            String newText = scanner.nextLine();

            notizZumbearbeiten.setTitel(newTitel);
            notizZumbearbeiten.setText(newText);

            currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
        }

    }

    public void deleteNote(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int ordnerZahl = 0;

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

            List<Notiz> alleNotizen = ausgewaehlterOrdner.getNotizen();
            int ausgewaehlteNotiz = 0;

            int counterForNotes = 1;

            for (Notiz i : alleNotizen) {
                System.out.println("Notiz Nr. " + counterForNotes + "\n"
                        + i.getTitel() + "\n"
                        + i.getText());
                counterForNotes++;
            }

            System.out.println("Welche Notiz möchtest du Löschen?");
            try {
                ausgewaehlteNotiz = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Gib eine gültige Zahl ein!");
                currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
            }

            int ausgewaehlteNotizindex = ausgewaehlteNotiz - 1;

            Notiz notizZumbearbeiten = alleNotizen.getFirst();

            try {
                notizZumbearbeiten = alleNotizen.get(ausgewaehlteNotizindex);
            } catch (Exception e) {
                System.out.println("Diese Notiz existiert nicht, gib eine gültige Zahl ein!");
                currentUser.getLast().deleteNote(username, scanner, alleUser, currentUser);
            }

            alleNotizen.remove(ausgewaehlteNotizindex);
        }

        currentUser.getLast().deleteNote(username, scanner, alleUser, currentUser);
    }

    public void showNotes(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int ordnerZahl = 0;

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
                currentUser.getLast().showNotes(username, scanner, alleUser, currentUser);
            }


            int ausgewaehlterOrdnerIndex = ordnerZahl - 1;

            Ordner ausgewaehlterOrdner = ordnerVonUser.get(ausgewaehlterOrdnerIndex);

            List<Notiz> alleNotizen = ausgewaehlterOrdner.getNotizen();
            int ausgewaehlteNotiz = 0;

            int counterForNotes = 1;

            for (Notiz i : alleNotizen) {
                System.out.println("Notiz Nr. " + counterForNotes + "\n"
                        + i.getTitel() + "\n"
                        + i.getText());
                counterForNotes++;
            }

            currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
        }
    }

    public void deleteFolder(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        int ordnerZahl = 0;

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
                currentUser.getLast().deleteFolder(username, scanner, alleUser, currentUser);
            }


            int ausgewaehlterOrdnerIndex = ordnerZahl - 1;

            Ordner ausgewaehlterOrdner = ordnerVonUser.get(ausgewaehlterOrdnerIndex);
            ordnerVonUser.remove(ausgewaehlterOrdnerIndex);

            currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
        }
    }

    public void showFolder(String username, Scanner scanner, List<User> alleUser, List<User> currentUser) {
        if (ordnerVonUser.isEmpty()) {
            System.out.println("Es gibt keine Ordner, bitte erstelle zuerst ein Ordner! ");
            currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
        } else {

            int counter = 1;

            for (Ordner ordner : ordnerVonUser) {
                System.out.println(counter + ":" + " " + ordner.getName());
                counter = counter + 1;
            }
        }

        currentUser.getLast().auswahlOption(username, scanner, alleUser, currentUser);
    }
}








