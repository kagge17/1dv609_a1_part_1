package com.lab;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Password pw1 = null;
        Password pw2 = null;


        try {
            pw1 = new Password("Password123!");
            pw2 = new Password("Password123!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(pw1.isPasswordSame(pw2));
    }
}
