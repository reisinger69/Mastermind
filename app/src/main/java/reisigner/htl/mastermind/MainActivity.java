package reisigner.htl.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import reisigner.htl.mastermind.funktions.FileReader;
import reisigner.htl.mastermind.funktions.MasterMindLogic;
import reisigner.htl.mastermind.funktions.Saver;
import reisigner.htl.mastermind.objects.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FileReader config;
    ListView list;
    TextView guess;
    MasterMindLogic mml;
    Saver saver;

    int round;

    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.submit);
        b.setOnClickListener(this);

        config = new FileReader();
        try {
            config.readAsset(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mml = new MasterMindLogic(config);
        mml.newGame();

        list = findViewById(R.id.listview);
        guess = findViewById(R.id.guess);

        inizialize();

        bindAdapterToListView(list);

        saver = new Saver();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (items.get(i).equals("NEW GAME")) {
                    items.clear();
                    mAdapter.notifyDataSetChanged();
                    mml.newGame();
                }
            }
        });

    }


        private void bindAdapterToListView(ListView lv) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            list.setAdapter(mAdapter);
        }

    void inizialize() {
        int[] ids = {R.id.submit,R.id.load, R.id.save, R.id.score, R.id.settings};
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.submit) {
            System.out.println("clicked");
            if (!guess.getText().equals("") && isInAlphapth(guess.getText().toString()) && guess.getText().toString().length() == config.getCodeLength()) {
                round++;
                if (guess.getText().toString().equals(mml.getCode())) {
                    items.add("Solved");
                    items.add("NEW GAME");
                }else if (round == config.getGuessRounds()) {
                    items.add("Not Solved");
                    items.add("NEW GAME");
                } else {
                    items.add(String.valueOf(guess.getText() + " | " + mml.getSigns(guess.getText().toString())));
                }
                mAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "False Input", Toast.LENGTH_SHORT).show();
            }
        } else if(view.getId() == R.id.settings) {
            items.clear();
            mAdapter.notifyDataSetChanged();

            items.add("Code Length");
            items.add(config.getCodeLength() + "");
            items.add("Double Allowed");
            items.add(config.isDoubleAllowed() + "");
            items.add("Guess Rounds");
            items.add(config.getGuessRounds() + "");
            items.add("Correct Position Sign");
            items.add(config.getCorrectPositionSign() + "");
            items.add("Correct Code Element Sign");
            items.add(config.getCorrectCodeElementSign() + "");
            items.add("alphabet");
            items.add(config.getAlphabet().toString());
            items.add("NEW GAME");
            mAdapter.notifyDataSetChanged();
        } else if(view.getId() == R.id.save) {
            System.out.println("items");
            Status s = new Status(round, items, mml.getCode());
            saver.safe(s, getApplicationContext());

        }
    }

    boolean isInAlphapth(String input) {
        boolean right = false;
        char[] in = input.toCharArray();
        for (int i = 0; i < in.length; i++) {
            right = false;
            for (int j = 0; j < config.getAlphabet().size(); j++) {
                if (config.getAlphabet().get(j).toString().equals(Character.toString(in[i]))) {
                    right = true;
                    break;
                }
            }
            if (!right) {
                return false;
            }
        }
        System.out.println("is in alphapeth");
        return true;
    }
}