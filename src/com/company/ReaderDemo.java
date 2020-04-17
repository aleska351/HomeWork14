package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(
                new FileInputStream("fileDemo.txt"))) {
            String s;
            while ((scanner.hasNextLine())) {
                s = scanner.nextLine();
                System.out.println(s);
            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
