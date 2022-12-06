package reisigner.htl.mastermind.funktions;

import static android.media.MediaCodec.MetricsConstants.MODE;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import reisigner.htl.mastermind.objects.Status;

public class Saver {
    public Status load(Context context) {
        Gson gs = new Gson();
        try {
            FileInputStream fos = context.openFileInput("safe.json");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(fos, StandardCharsets.UTF_8));
            return gs.fromJson(br.readLine(), Status.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void safe(Status s, Context context) {
        try {
            Gson gs = new Gson();
            FileOutputStream fos = context.openFileOutput("safe.json", Context.MODE_PRIVATE);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
            out.println(gs.toJson(s));
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
