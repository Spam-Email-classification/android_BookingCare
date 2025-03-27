package com.example.bookingcare263;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookingcare263.databinding.FragmentBlankBinding;

public class BlankFragment extends Fragment {
    TextView txttenbsdt;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {





        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


}