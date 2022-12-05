package reisigner.htl.mastermind.objects;

import java.util.ArrayList;
import java.util.List;

public class Status {
    private int guessedRounds;
    private ArrayList<String> list;
    private String code;

    public Status(int guessedRounds, ArrayList<String> list, String code) {
        this.guessedRounds = guessedRounds;
        this.list = list;
        this.code = code;
    }

    public int getGuessedRounds() {
        return guessedRounds;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public String getCode() {
        return code;
    }
}
