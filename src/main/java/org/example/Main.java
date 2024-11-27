package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String username;
        List<Ordner> alleOrdner = new ArrayList<>();
        List<User> alleUser = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Gebe deinen Namen ein: ");
        username = scanner.nextLine();

        User user = new User(username);
        alleUser.add(user);

        user.checkinstanz(username, scanner, alleOrdner, alleUser);
    }
}