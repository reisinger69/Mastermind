package reisigner.htl.mastermind.funktions;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import reisigner.htl.mastermind.MainActivity;

public class FileReader {
    private ArrayList<Character> alphabet = new ArrayList();
    private int guessRounds;
    private int codeLength;
    private boolean doubleAllowed;
    private char correctPositionSign;
    private char correctCodeElementSign;

    public void readAsset(Context context) throws IOException {
        InputStream input = null;
        AssetManager assetManager = context.getAssets();
        try {
             input = assetManager.open("config.conf");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(input));

        for (int i = 0; i < 6; i++) {
            String line = bf.readLine();
            String identifyer = line.split("=")[0];
            String secoundPart = line.split("=")[1];
            switch (identifyer.trim()) {
                case "alphabet":
                    String[] alpha = secoundPart.split(",");
                    for (String s : alpha) {
                        alphabet.add(s.trim().charAt(0));
                    }
                    break;
                case "codeLength":
                    codeLength = Integer.parseInt(secoundPart.trim());
                     break;
                case "doubleAllowed":
                    doubleAllowed = Boolean.parseBoolean(secoundPart.trim());
                    break;
                case "correctPositionSign":
                    correctPositionSign = secoundPart.charAt(0);
                    break;
                case "correctCodeElementSign":
                    correctCodeElementSign = secoundPart.charAt(0);
                    break;
                case "guessRounds":
                    guessRounds = Integer.parseInt(secoundPart.trim());
            }
        }

    }

    public int getGuessRounds() {
        return guessRounds;
    }

    public ArrayList<Character> getAlphabet() {
        return alphabet;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public boolean isDoubleAllowed() {
        return doubleAllowed;
    }

    public char getCorrectPositionSign() {
        return correctPositionSign;
    }

    public char getCorrectCodeElementSign() {
        return correctCodeElementSign;
    }
}
