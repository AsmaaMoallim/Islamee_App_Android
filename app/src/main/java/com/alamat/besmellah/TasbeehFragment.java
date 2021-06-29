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
                    setTextsvalues(index);
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
                setTextsvalues(index);            }
        });

        binding.btnTasbeehRefreshAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = 0;
                for (int i = 0; i < spinnerValues.length; i++) {
                    counterArr.set(i, counter);
                    setTextsvalues(i);
                }
            }

        });
        ////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < spinnerValues.length; i++) {
            counterArr.add(0);
        }

        return view;
    }

    String[] spinnerValues = {"سبحان الله", "الحمد لله", "استغفر الله", "لا إله إلا الله", "سبحان الله العظيم"};


    public void setTextsvalues(int index){
            switch (index) {
                case 0:
                    binding.tvTasbeesCounterView1.setText("تعداد سبحان الله = " + counterArr.get(index));
                    break;
                case 1:
                    binding.tvTasbeesCounterView2.setText("تعداد الحمد لله = " + counterArr.get(index));
                    break;
                case 2:
                    binding.tvTasbeesCounterView3.setText("تعداد استغفر الله = " + counterArr.get(index));
                    break;
                case 3:
                    binding.tvTasbeesCounterView4.setText("تعداد لا إله إلا الله = " + counterArr.get(index));
                    break;
                case 4:
                    binding.tvTasbeesCounterView5.setText("تعداد سبحان الله العظيم = " + counterArr.get(index));
                    break;
            }
        int totalCount=0;
        for (int i = 0; i < spinnerValues.length; i++) {
            totalCount+=(counterArr.get(i));
        }
        binding.tvTasbeesTotalView.setText("المجموع = " +totalCount);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        counter = counterArr.get(index);
        setTextsvalues(index);
//        Toast.makeText(getContext(), "Selected User: " + spinnerValues[position] + index, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        index = 0;
    }

}