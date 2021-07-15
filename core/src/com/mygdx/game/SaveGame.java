package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.HashMap;

public class SaveGame {
    private static HashMap<Integer,Integer> levelCodes;
    private static HashMap<Integer,Integer> levelEndCodes;

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

        levelEndCodes = new HashMap<>();
        levelEndCodes.put(1,473);
        levelEndCodes.put(2,834);
        levelEndCodes.put(3,990);
        levelEndCodes.put(4,173);
        levelEndCodes.put(5,374);
        levelEndCodes.put(6,333);
        levelEndCodes.put(7,837);
        levelEndCodes.put(8,990);
        levelEndCodes.put(9,187);
        levelEndCodes.put(10,901);
        levelEndCodes.put(11,114);
        levelEndCodes.put(12,482);
        levelEndCodes.put(13,609);
        levelEndCodes.put(14,993);
        levelEndCodes.put(15,387);
        levelEndCodes.put(16,739);
        levelEndCodes.put(17,829);
    }



    public static void saveHighscore(int level, int highScore) {
        Preferences prefs = Gdx.app.getPreferences("Savegame");
        String targetSaveCode;
        long savedCode = 0;
        int savedHighScore;
        boolean invalidSave = false;

        try {
            savedCode = SaveGame.getSavedCode(prefs,level);
        } catch (NoDataFoundException | InvalidLevelCodeException e) {
            invalidSave = true;
        }
        
        if (!invalidSave && (SaveGame.extractHighscore(savedCode) >= highScore)) {
                return;
        }


        //Build levelcode
        targetSaveCode = String.valueOf(SaveGame.levelCodes.get(level));
        //Build highscore
        targetSaveCode = targetSaveCode + String.valueOf(SaveGame.translateToSave(highScore));
        //Build levelEndCode
        targetSaveCode = targetSaveCode + String.valueOf(SaveGame.levelEndCodes.get(level));

        prefs.putString(SaveGame.getKeyToLevel(level),targetSaveCode);
        prefs.flush();
    }

    private static boolean islevelCodeLegit (int level, int levelCode) {
        int correctLevelCode = SaveGame.levelCodes.get(level);
        return correctLevelCode == levelCode;
    }

    private static boolean isLevelEndCodeLegit(int level, int levelEndCode) {
        int correctLevelEndCode = SaveGame.levelEndCodes.get(level);
        return correctLevelEndCode == levelEndCode;
    }

    private static int extractHighscore(long code) {
        String highscore = String.valueOf(code);
        int indexEnd = highscore.length() - 3;

        highscore = highscore.substring(5, indexEnd);
        return SaveGame.translateFromSave(highscore);
    }

    private static long getSavedCode(Preferences prefs, int level) throws NoDataFoundException, InvalidLevelCodeException {
        String code = prefs.getString(getKeyToLevel(level),"not saved yet");
        String levelCode;
        String levelEndCode;

        if (code.equals("not saved yet")) {
            throw new NoDataFoundException();
        }

        if (code.length() < 9) {
            throw new InvalidLevelCodeException();
        }

        levelCode = code.substring(0,5);
        levelEndCode = code.substring(code.length() - 3);


        if (!islevelCodeLegit(level, Integer.parseInt(levelCode)) || !isLevelEndCodeLegit(level, Integer.parseInt(levelEndCode))) {
            throw new InvalidLevelCodeException();
        }

        return Long.parseLong(code);
    }

    private static String getKeyToLevel(int level) {
        return "level" + String.valueOf(level);
    }

    private static String translateToSave(int code) {
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
        return translatedCode;
    }

    private static int translateFromSave(String code) {
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

    public static class InvalidLevelCodeException extends Exception {

    }



}
