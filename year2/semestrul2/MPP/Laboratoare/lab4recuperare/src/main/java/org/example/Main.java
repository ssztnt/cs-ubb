package org.example;

import java.io.File;

public class Main{
    public static void main(String[] args) {
        File dbFile = new File("ateltism.sqlite");
        System.out.println("Absolute path to the database: " + dbFile.getAbsolutePath());
    }
}