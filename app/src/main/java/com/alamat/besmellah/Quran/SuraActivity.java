package com.alamat.besmellah.Quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;

import com.alamat.besmellah.R;
import com.alamat.besmellah.databinding.ActivitySuraBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SuraActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ActivitySuraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        String text = "";
        try {
            InputStream stream = getAssets().open(inFile);

//            int size = stream.available();
//            byte[] buffer = new byte[size];
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            int num = 0;

            while(reader.ready()) {
                String line = reader.readLine();
//                System.out.println(line);
                text += line + "\n------------------------ ( " + ++num + " ) ------------------------\n";
            }
//            stream.read(buffer);
            stream.close();
//            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }
        return text;
    }
}