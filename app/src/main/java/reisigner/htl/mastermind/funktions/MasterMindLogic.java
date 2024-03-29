package reisigner.htl.mastermind.funktions;

import android.provider.CalendarContract;

import java.util.ArrayList;

public class MasterMindLogic {
    private ArrayList<Character> alphabet = new ArrayList();
    private int guessRounds;
    private int codeLength;
    private boolean doubleAllowed;
    private char correctPositionSign;
    private char correctCodeElementSign;

    private String code;

    public MasterMindLogic(FileReader fr) {
        this.alphabet = fr.getAlphabet();
        this.guessRounds = fr.getGuessRounds();
        this.codeLength = fr.getCodeLength();
        this.doubleAllowed = fr.isDoubleAllowed();
        this.correctPositionSign = fr.getCorrectPositionSign();
        this.correctCodeElementSign = fr.getCorrectCodeElementSign();
        this.code = "";
    }

    public String getCode() {
        return code;
    }
    public void newGame() {
        StringBuilder sb = new StringBuilder();
        System.out.println("new Game");
        if (doubleAllowed) {
            for (int i = 0; i < codeLength; i++) {
                int random = (int) (Math.random() * alphabet.size() - 1);
                sb.append(alphabet.get(random));
            }
        } else {
            ArrayList<Character> usedChars = new ArrayList<>();
            for (int i = 0; i < codeLength; i++) {
                int random = (int) (Math.random() * alphabet.size() - 1);
                if (!usedChars.contains(alphabet.get(random))) {
                    usedChars.add(alphabet.get(random));
                    sb.append(alphabet.get(random));
                } else {
                    i--;
                }

            }
        }
        this.code = sb.toString();
        System.out.println("code is " + code);
    }

    public String getSigns(String input) {
        StringBuilder checkCode = new StringBuilder(code);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == checkCode.charAt(i)) {
                checkCode.setCharAt(i, ' ');
                sb.append(correctPositionSign).append(" ");
            } else {
                for (int j = 0; j < code.length(); j++) {
                    if (input.charAt(i) == checkCode.charAt(j)) {
                        checkCode.setCharAt(j, ' ');
                        sb.append(correctCodeElementSign).append(" ");
                    }
                }
            }
        }
        return sb.toString();
    }

    public void setCode(String code) {
        this.code = code;
    }
}
