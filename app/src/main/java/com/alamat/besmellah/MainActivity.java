package com.alamat.besmellah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.alamat.besmellah.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity  {


    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        @NonNull
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.it_menu_quran:
                    fragment = new QuranFragment();
                    break;
                case R.id.it_menu_hadees:
                    fragment = new HadeesFragment();
                    break;
                case R.id.it_menu_tasbeeh:
                    fragment = new TasbeehFragment();
                    break;
                case R.id.it_menu_radio:
                    fragment = new RadioFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frg_main_bottomNavigationFragments,fragment).commit();
            return true;
        }

    };

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navMainBottomNavigator.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frg_main_bottomNavigationFragments, new QuranFragment()).commit();



    }
}