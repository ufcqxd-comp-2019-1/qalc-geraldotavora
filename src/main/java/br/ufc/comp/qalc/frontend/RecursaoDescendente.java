package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecursaoDescendente {

    public static Scanner scan;
    public static int value;
    public static String app;

    public static void main(String[] args) {
        System.out.println( "E -> TX\n" +
                            "X -> 'o'TX | null\n" +
                            "T -> FY\n" +
                            "Y -> 'e'FY | null\n" +
                            "F -> '!'F | '('E')'|'True'|'False'\n"
        );

        String file;

        RecursaoDescendente doc = new RecursaoDescendente();
        file = "/home/geraldo/Área de Trabalho/teste.txt";

        if (doc.open(file) == 0) {
            while (scan.hasNextLine()) {
                value = 0;
                doc.reader();
            }
            scan.close();
        }
    }

        public int open(String file){
            try{
                scan = new Scanner(new File(file));
                return 0;
            }catch(FileNotFoundException x){
                System.out.println("Arquivo nao encontrado");
                return 1;
            }
        }

        public void reader(){

        app = scan.nextLine();
        System.out.println("String lida do arquivo" );


            if(E() && value == app.length()){
                System.out.println("A string: "+ app +" esta na linguagem.");
            } else{
                System.out.println("A string: "+ app +" nao esta na linguagem.");
            }
            for(int i = 0; i < app.length();i++){
                System.out.println("\t"+app.charAt(i) +"\t\t\t"+ i);
            }

            }

        public static boolean E(){
            if(T()){
                if(value <= app.length()){
                    if(X()){
                        return true;
                    }
                }
            }
            return false;

        }


        public static boolean T(){
            if(F()){
                if(value <= app.length()){
                    if(Y()){
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean F(){

            if(value < app.length() && app.charAt(value)=='('){
                value++;
                if(F()){
                    if(value < app.length()) {
                        value++;
                        if (value <= app.length() && app.charAt(value) == ')') {
                            return true;
                        }
                    }
                }
            }


            else if(value < app.length() && app.charAt(value) == '"') {
                value++;
                if (value < app.length() && app.charAt(value) == 't') {
                    value++;
                    if (value < app.length() && app.charAt(value) == 'r') {
                        value++;
                        if (value < app.length() && app.charAt(value) == 'u') {
                            value++;
                            if (value < app.length() && app.charAt(value) == 'e') {
                                value++;
                                if (value < app.length() && app.charAt(value) == '"') {

                                    return true;
                                }
                            }
                        }
                    }
                }

                else if(value < app.length() && app.charAt(value) == 'f'){
                    value++;
                    if(value < app.length() && app.charAt(value) == 'a'){
                        value++;
                        if(value < app.length() && app.charAt(value) == 'l'){
                            value++;
                            if(value < app.length() && app.charAt(value) == 's'){
                                value++;
                                if(value < app.length() && app.charAt(value) == 'e'){
                                    value++;
                                    if(value < app.length() && app.charAt(value) == '"'){
                                        return true;
                                    }

                                }
                            }
                        }
                    }
                }
                else if(value < app.length() && app.charAt(value) == '!') {
                    value++;
                    if(value < app.length() && app.charAt(value) == '"') {
                        value++;
                        if (F()) {
                            if (value < app.length()) {
                                value++;
                                return true;
                            }

                        }
                    }
                }
            }
            return false;
        }

        public static boolean Y(){
            if(value < app.length() && app.charAt(value) == '"'){
                value++;
                if(value < app.length() && app.charAt(value) == 'e'){
                    value++;
                    if(value < app.length() && app.charAt(value) == '"'){
                        if(F()){
                            if(Y()){
                                value++;
                                return true;
                            }
                        }
                    }
                }
            }
            if(value <= app.length()){
                return true;
            }
        return false;

        }

        public static boolean X(){
            if(value < app.length() && app.charAt(value) == '"'){
                value++;
                if(value < app.length() && app.charAt(value) == 'o'){
                    value++;
                    if(value < app.length() && app.charAt(value) == '"'){
                        if(T()){
                            if(X()){
                                value++;
                                return true;
                            }
                        }
                    }
                }
            }
            if(value == app.length()){
                return true;
            }
            return false;

        }



}
