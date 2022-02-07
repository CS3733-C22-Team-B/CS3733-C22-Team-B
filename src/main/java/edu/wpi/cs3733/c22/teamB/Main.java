package edu.wpi.cs3733.c22.teamB;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        args = getLogin(args);
        // bDB.main(args);
        Bapp.launch(Bapp.class, args);
    }

    private static String[] getLogin(String[] input) {
        if (input.length != 2) {
            input = new String[] {"", ""};
        }
        while (!input[0].equals("admin") || !input[1].equals("admin")) {
            System.out.println("Incorrect Login");
            Scanner userInput = new Scanner(System.in);
            System.out.println("Enter Username:");
            input[0] = userInput.nextLine();
            System.out.println("Enter Password:");
            input[1] = userInput.nextLine();
        }

        return input;
    }
}
