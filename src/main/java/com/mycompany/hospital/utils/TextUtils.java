package com.mycompany.hospital.utils;

import java.io.IOException;
import java.util.ArrayList;

public class TextUtils {
    public static String message = "";
    public static int createMenu(ArrayList<String> options) throws IOException, InterruptedException{
        enableRawMode();
        
        int idx = 0;
        int key;
        while(true){
            clearConsole();
            if(message.length() > 0){
                System.out.println("\r" + message);
                System.out.println("\r\n=======================================");
            }

            printMenu(options, idx);
            key = System.in.read();

            if(key == 27){
                System.in.read();
                int arrowkey = System.in.read();

                if(arrowkey == 'A'){
                    idx = (idx - 1 + options.size()) % options.size();
                }

                if(arrowkey == 'B'){
                    idx = (idx + 1) % options.size();
                }
            } else if(key == 10 || key == 13){
                disableRawMode();
                return idx + 1;
            }
        }
    }

    public static void printMenu(ArrayList<String> options, int selectedIndex){
        for(int i = 0; i < options.size(); i++){
            System.out.print("\r");
            if(i == selectedIndex){
                System.out.print("> ");
            }
            System.out.println(options.get(i));
        }
    }

    public static void printMenu(String[] options, int selectedIndex){
        for(int i = 0; i < options.length; i++){
            if(i == selectedIndex){
                System.out.print("> ");
            }
            System.out.print(options[i]);
            System.out.print("\r\n");
        }
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void enableRawMode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty -echo raw </dev/tty"}).waitFor();
    }

    private static void disableRawMode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty echo cooked </dev/tty"}).waitFor();
    }


}
