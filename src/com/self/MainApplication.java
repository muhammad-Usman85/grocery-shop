package com.self;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        char option = '\0';

        System.out.println("\n\n Main Menu");
        System.out.println("1: ");
        System.out.println("2: ");

        Scanner scanner =  new Scanner(System.in);

        // getting input in option from user
        option = scanner.next().charAt(0);

        switch (option){
            case '1':

                break;
        }
    }
}
