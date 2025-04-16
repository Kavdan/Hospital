package com.mycompany.hospital.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextUtils {
    public static String message = "";
    public static int createMenu(ArrayList<String> options) throws IOException, InterruptedException{
        enableRawMode();
        hideCursor();
        
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
                showCursor();
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
            System.out.print(options.get(i));
            if(i + 1 == options.size()) System.out.print("\r\n");
            else System.out.print("\n");
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

    public static ArrayList<String> objArrToStrArray(List<?> objects){
        ArrayList<String> arr = new ArrayList<>();

        for(Object obj : objects) {
            arr.add(obj.toString());
        }

        return arr;
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void enableRawMode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty -echo raw </dev/tty"}).waitFor();
    }

    public static void disableRawMode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty echo cooked </dev/tty"}).waitFor();
    }

    public static void hideCursor() {
        System.out.print("\033[?25l");
        System.out.flush();
    }

    public static void showCursor() {
        System.out.print("\033[?25h");
        System.out.flush();
    }
}
