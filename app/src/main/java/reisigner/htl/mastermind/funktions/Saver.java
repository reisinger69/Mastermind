package reisigner.htl.mastermind.funktions;

import static android.media.MediaCodec.MetricsConstants.MODE;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import reisigner.htl.mastermind.objects.Status;

public class Saver {
    public Status load() {
        return null;
    }

    public void safe(Status s, Context context) {
        JSONObject jsnobj = new JSONObject();
        try {
            jsnobj.put("code", s.getCode());
            jsnobj.put("rounds", s.getGuessedRounds());
            JSONArray arr = new JSONArray();
            for (String line :
                    s.getList()) {
                arr.put(line);
            }
            System.out.println(arr);
            System.out.println(s.getList().toString());
            jsnobj.put("list", arr);

            FileOutputStream fos = context.openFileOutput("safe.json", Context.MODE_PRIVATE);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
            out.println(jsnobj);
            out.flush();
            out.close();

        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
