package com.alamat.besmellah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.alamat.besmellah.databinding.FragmentTasbeehBinding;

import java.util.ArrayList;

public class TasbeehFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentTasbeehBinding binding;
    View view;
    int counter = 0;
    //    int[] counterArr ;
    ArrayList<Integer> counterArr = new ArrayList<Integer>();
    int index;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tasbeeh, container, false);
        view = binding.getRoot();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinTasbeehZeker.setAdapter(adapter);
        binding.spinTasbeehZeker.setOnItemSelectedListener(this);


        ////////////////////////////////////////////////////////////////////////////////

        binding.btnTasbeehAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
//                    counter += 1;
                    counterArr.set(index, counter++);
                    binding.tvTasbeesCounterView.setText("العدد = " + counter);
                } catch (IndexOutOfBoundsException e) {
                    return;
                }

            }
        });

        binding.btnTasbeehStartOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                counterArr.add(index, 0);
                binding.tvTasbeesCounterView.setText("العدد = " + counterArr.get(index));
            }
        });

        binding.btnTasbeehRefreshAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                for (int i = 1; i < spinnerValues.length; i++) {
                    counterArr.add(i, 0);
                }
                binding.tvTasbeesCounterView.setText("العدد = " + counterArr.get(index));
            }
        });
        ////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < spinnerValues.length; i++) {
            counterArr.add(0);
        }
        return view;
    }

    String[] spinnerValues = {"سبحان الله", "الحمد لله", "استغفر الله", "لا إله إلا الله", "سبحان الله العظيم"};


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        counter = counterArr.get(index);
        binding.tvTasbeesCounterView.setText("العدد = " + counterArr.get(index));
//        Toast.makeText(getContext(), "Selected User: " + spinnerValues[position] + index, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        index = 0;
    }

}