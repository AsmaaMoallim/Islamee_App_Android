package com.alamat.besmellah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alamat.besmellah.databinding.ActivityHadessContentBinding;

public class HadessContentActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ActivityHadessContentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hadess_content);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        binding.tvHadeesContentHadeescontent.setText(extras.getString("hadeescontent"));


        binding.btnHadeesContentDeletonehadees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getExtras().getInt("id");
                String res = new DbManager(getBaseContext()).deleteonerecord(++id);
                new DbManager(getBaseContext()).updateIDValues();

                HadeesFragment.hadeesModelList.remove(--id);
                HadeesFragment.hadeesAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        binding.tvHadeesContentHadeescontent.setMovementMethod(new ScrollingMovementMethod());

    }

}