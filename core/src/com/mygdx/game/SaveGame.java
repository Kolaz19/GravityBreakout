package com.mygdx.game;

import com.badlogic.gdx.Preferences;
import java.lang.reflect.Field;
import java.util.HashMap;

public class SaveGame {
    private static HashMap<Integer,Integer> levelCodes;

    static {
        levelCodes = new HashMap<>();
        levelCodes.put(1,92837);
        levelCodes.put(2,87324);
        levelCodes.put(3,90863);
        levelCodes.put(4,23778);
        levelCodes.put(5,11978);
        levelCodes.put(6,55693);
        levelCodes.put(7,20023);
        levelCodes.put(8,46871);
        levelCodes.put(9,90921);
        levelCodes.put(10,79109);
        levelCodes.put(11,22339);
        levelCodes.put(12,90123);
        levelCodes.put(13,53831);
        levelCodes.put(14,11095);
        levelCodes.put(15,20285);
        levelCodes.put(16,49324);
    }



    public static void saveHighscore(int level, int highScore) {


    }

    private static long getSavedCode(Preferences prefs, int level) throws NoDataFoundException {
        String code = prefs.getString(getKeyToLevel(level),"not saved yet");
        if (code.equals("not saved yet")) {
            throw new NoDataFoundException();
        }
        return Long.getLong(code);
    }

    private static String getKeyToLevel(int level) {
        return "level" + String.valueOf(level);
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


public static class NoDataFoundException extends Exception {

}

}
