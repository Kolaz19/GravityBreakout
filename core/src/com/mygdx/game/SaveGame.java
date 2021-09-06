package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class SaveGame {
    private static final HashMap<Integer,Integer> levelCodes;
    private static final HashMap<Integer,Integer> levelEndCodes;
    private static final Preferences prefs;

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

        prefs = Gdx.app.getPreferences("Savegame");
    }

    public static int getSavedHighscore(int level) {
        long savedCode;

        try {
            savedCode = SaveGame.getSavedCode(level);
        } catch (NoDataFoundException | InvalidLevelCodeException e) {
            return 0;
        }

        return SaveGame.extractHighscore(savedCode);
    }

    public static void saveHighscore(int level, int highScore) {
        String targetSaveCode;
        long savedCode = 0;
        int savedHighScore;
        boolean invalidSave = false;

        try {
            savedCode = SaveGame.getSavedCode(level);
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

        if (level == 12 && highScore > 399 && !SaveGame.wasRickrolledBefore()) {
            rickroll();
        }
    }

    public static void rickroll() {
            try {
                Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=dQw4w9WgXcQ").toURI());
            } catch (URISyntaxException | IOException e) {
        }
    }

    private static boolean wasRickrolledBefore() {
       boolean wasRickrolled = prefs.getBoolean("rickrolled", false);
       if (!wasRickrolled) {
           prefs.putBoolean("rickrolled", true);
           prefs.flush();
       }
       return wasRickrolled;
    }

    public static boolean isLevelUnlocked(int level) {
        if (level == 1) {
            return true;
        }

        long code = 0;
        try {
            code = SaveGame.getSavedCode(level - 1);
        } catch (NoDataFoundException | InvalidLevelCodeException e) {
            return false;
        }

        if (SaveGame.extractHighscore(code) >= 400) {
            return true;
        } else {
            return false;
        }
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

    private static long getSavedCode(int level) throws NoDataFoundException, InvalidLevelCodeException {
        String code = prefs.getString(getKeyToLevel(level),"not saved yet");
        String levelCode;
        String levelEndCode;

        if (code.equals("not saved yet")) {
            throw new NoDataFoundException();
        }

        if (code.length() < 9) {
            throw new InvalidLevelCodeException("Code length in savegame under 9 characters");
        }

        levelCode = code.substring(0,5);
        levelEndCode = code.substring(code.length() - 3);


        if (!islevelCodeLegit(level, Integer.parseInt(levelCode)) || !isLevelEndCodeLegit(level, Integer.parseInt(levelEndCode))) {
            throw new InvalidLevelCodeException("Wrong Levelcode in savegame");
        }

        return Long.parseLong(code);
    }

    public static int getSavedMusicVolume() {
        String code = prefs.getString("musicVolume","50");
        return Integer.parseInt(code);
    }

    public static int getSavedEffectVolume() {
        String code = prefs.getString("effectVolume","50");
        return Integer.parseInt(code);
    }

    public static void saveMusicVolume(int volume) {
        prefs.putString("musicVolume", String.valueOf(volume));
        prefs.flush();
    }
    public static void saveEffectVolume(int volume) {
        prefs.putString("effectVolume", String.valueOf(volume));
        prefs.flush();
    }

    public static void saveBallColor(Ball.BallColor color) {
        prefs.putString("ballColor", color.toString());
        prefs.flush();
    }

    public static Ball.BallColor getSavedBallColor () {
        String code = prefs.getString("ballColor", "WHITE");
        return Ball.BallColor.valueOf(code);
    }

    public static void resetSaveGame() {
        prefs.clear();
        prefs.flush();
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

    public InvalidLevelCodeException(String message) {
        super(message);
    }
}



}
