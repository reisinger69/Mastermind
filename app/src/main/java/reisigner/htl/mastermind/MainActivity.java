package reisigner.htl.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

import reisigner.htl.mastermind.funktions.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FileReader config;

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

    }

    @Override
    public void onClick(View view) {

    }
}