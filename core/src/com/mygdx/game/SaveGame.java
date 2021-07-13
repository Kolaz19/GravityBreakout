package com.mygdx.game;

public class SaveGame {
    private static final int level01Code = 92837;
    private static final int level02Code = 23013;
    private static final int level03Code = 71037;
    private static final int level04Code = 94025;
    private static final int level05Code = 12839;
    private static final int level06Code = 82348;
    private static final int level07Code = 84902;
    private static final int level08Code = 90293;
    private static final int level09Code = 29598;
    private static final int level10Code = 10184;
    private static final int level11ode = 83659;
    private static final int level12Code = 66783;
    private static final int level13Code = 98231;
    private static final int level14Code = 90982;
    private static final int level15Code = 57891;
    private static final int level16Code = 88234;



    public static void translate() {
        
    }


    private static int translateToSave(int code) {
        String translatedCode = "";
        String currentDigit = "";
        String number = String.valueOf(code);
        int size;


        size = number.length();
        while (translatedCode.length() != size) {
            switch(number.charAt(translatedCode.length())) {
                case '0': currentDigit = "7";
                    break;
                case '1': currentDigit = "8";
                    break;
                case '2': currentDigit = "2";
                    break;
                case '3': currentDigit = "9";
                    break;
                case '4': currentDigit = "6";
                    break;
                case '5': currentDigit = "3";
                    break;
                case '6': currentDigit = "4";
                    break;
                case '7': currentDigit = "5";
                    break;
                case '8': currentDigit = "0";
                    break;
                case '9': currentDigit = "1";
                    break;
            }
            translatedCode = translatedCode + currentDigit;
        }
        return Integer.parseInt(translatedCode);

    }

    private static int translateFromSave(int code) {
        String translatedCode = "";
        String currentDigit = "";
        String number = String.valueOf(code);
        int size;


        size = number.length();
        while (translatedCode.length() != size) {
            switch(number.charAt(translatedCode.length())) {
                case '0': currentDigit = "8";
                    break;
                case '1': currentDigit = "9";
                    break;
                case '2': currentDigit = "2";
                    break;
                case '3': currentDigit = "5";
                    break;
                case '4': currentDigit = "6";
                    break;
                case '5': currentDigit = "7";
                    break;
                case '6': currentDigit = "4";
                    break;
                case '7': currentDigit = "0";
                    break;
                case '8': currentDigit = "1";
                    break;
                case '9': currentDigit = "3";
                    break;
            }
            translatedCode = translatedCode + currentDigit;
        }
        return Integer.parseInt(translatedCode);
    }




}
