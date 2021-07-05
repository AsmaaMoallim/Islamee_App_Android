package com.alamat.besmellah.Hadees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alamat.besmellah.DbManager;
import com.alamat.besmellah.R;
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
                AlertDialog diaBox = ConfirmDeletion();
                diaBox.show();
//                customDialog();
            }
        });
        binding.tvHadeesContentHadeescontent.setMovementMethod(new ScrollingMovementMethod());


    }


    private AlertDialog ConfirmDeletion(){
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_view, null);

        AlertDialog alert = new AlertDialog.Builder( new ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .setTitle("حذف الحديث")
                .setMessage("هل تريد حذف هذا الحديث؟")
//                .setIcon(R.drawable.delete)

//                .setView(R.layout.dialog_view)
//                .setView(view)
                .setPositiveButton("حذف", new DatePickerDialog.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        int id = getIntent().getExtras().getInt("id");
                        String res = new DbManager(getBaseContext()).deleteonerecord(++id);
                        new DbManager(getBaseContext()).updateIDValues();

                        HadeesFragment.hadeesModelList.remove(--id);
                        HadeesFragment.hadeesAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
                        finish();
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();




        return alert;


    }
//
//    private void customDialog() {
//        final Dialog dialog = new Dialog(this, R.style.MaterialDialogSheet);
//        dialog.setContentView(R.layout.dialog_view); // your custom view.
//        dialog.setCancelable(false);
//        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//
//        dialog.show();
//    }
}