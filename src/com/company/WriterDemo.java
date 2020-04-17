package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class WriterDemo {
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter(
                new FileOutputStream("fileDemo.txt"))) {
            writer.println("Hello, world!");
            writer.println("Hello, world!");
            writer.println("Hello ");
            writer.println("Hi");
        } catch (IOException e) {
            System.err.println("При записи позникла ошибка " + e.getMessage());
        }
    }
}
