package com.alamat.besmellah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

import com.alamat.besmellah.databinding.ActivitySuraBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SuraActivity extends AppCompatActivity {

    private ActivitySuraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sura);
//        binding = ActivitySuraBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_sura);

        Bundle extras = getIntent().getExtras();
        String surasNames = "سورة " + extras.getString("surasNames");
        binding.tvSuraSuraTitil.setText(surasNames);
        int indexOfSurasNames = extras.getInt("indexOfSurasNames");

        for (int i = 0; i <  QuranFragment.listOfSewarNames.length; i++) {
            if (i == indexOfSurasNames){
                String fileName = i+1 + ".txt";
                String data = LoadData(fileName);
                binding.tvSuraSuraContent.setText(data);
            }
        }
        binding.tvSuraSuraContent.setMovementMethod(new ScrollingMovementMethod());

    }
    public String LoadData(String inFile) {
        String tContents = "";
        try {
            InputStream stream = getAssets().open(inFile);

            int size = stream.available();
            byte[] buffer = new byte[size];
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while(reader.ready()) {
                String line = reader.readLine();
//                System.out.println(line);
                tContents += line + "\n----------------------------------------------------------\n";
            }
//            stream.read(buffer);
            stream.close();
//            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }
        return tContents;
    }
}