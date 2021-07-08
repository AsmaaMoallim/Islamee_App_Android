package com.alamat.besmellah.Radio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alamat.besmellah.R;
import com.alamat.besmellah.databinding.FragmentRadioBinding;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.io.IOException;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RadioFragment extends Fragment {
    private FragmentRadioBinding binding;
    View view;

    //    String stream = "http://quraan.us:9882/;*.mp3";
//String stream = "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4";
    String stream = "";
    MediaPlayer mediaPlayer;
    boolean prepared = false;
    boolean started = false;
    List<DataModel> Radios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_radio, container, false);

        binding.btnRadioPlayOrStop.setEnabled(false);
        binding.btnRadioPlayOrStop.setText("جاري التحميل....");

        callRadios();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        binding.btnRadioPlayOrStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (started) {
                    started = false;
                    mediaPlayer.pause();
                    binding.btnRadioPlayOrStop.setText("تشغيل");
                } else {
                    started = true;
                    mediaPlayer.start();
                    binding.btnRadioPlayOrStop.setText("إيقاف");
                }
            }
        });

        binding.mainActivityMultiLineRadioGroup.setOnCheckedChangeListener(new MultiLineRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ViewGroup group, RadioButton button) {
                stream = getStreamUrl(button);
                Toast.makeText(getActivity(), button.getText() + " was clicked",
                        Toast.LENGTH_SHORT).show();

                if (started) {
                    started = false;
                    mediaPlayer.pause();
                }

                mediaPlayer.reset();

                new PlayerTask().execute(stream);


            }
        });


        view = binding.getRoot();
        return view;
    }


    public void callRadios() {
        APIManager.getAPIS().getAllSources().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    Radios = response.body().getRadios();

                    for ( DataModel i : Radios){
                        binding.mainActivityMultiLineRadioGroup.addButtons(i.name);
                    }
////                    adabter = new RecyclerViewAdabter(response.body().getRadios());
//                    binding.recyclarview.setAdapter(adabter);

                }
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());

            }
        });
    }

    private String getStreamUrl(RadioButton button) {

        for (DataModel i : Radios){
            if (button.getText() == i.name){
                stream = i.url;
        }


//        switch (button.getId()) {
//            case 1:
//                stream = "http://livestreaming5.onlinehorizons.net/hls-live/Qurankareem/_definst_/liveevent/livestream.m3u8";
//                break;
//            case 2:
//                stream = "http://quraan.us:9888/;*.mp3";
//                break;
//            case 3:
//                stream = "http://quraan.us:9930/;*.mp3";
//                break;
//            case 4:
//                stream = "http://quraan.us:9936/;*.mp3";
//                break;
//            case 5:
//                stream = "http://quraan.us:9960/;*.mp3";
//                break;
//            case 6:
//                stream = "http://server2.quraan.us:9314/;*.mp3";
//                break;
//            case 7:
//                stream = "http://quraan.us:9944/;*.mp3";
//                break;
//            case 8:
//                stream = "http://server2.quraan.us:9990/;*.mp3";
//                break;
//            case 9:
//                stream = "http://quraan.us:9850/;*.mp3";
//                break;
//            case 10:
//                stream = "http://server2.quraan.us:9856/;*.mp3";
//                break;
        }


        return stream;
    }

    class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            binding.btnRadioPlayOrStop.setEnabled(true);
            binding.btnRadioPlayOrStop.setText("تشغيل");

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (started) {
            mediaPlayer.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (started) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (prepared) {
            mediaPlayer.release();
        }
    }
}