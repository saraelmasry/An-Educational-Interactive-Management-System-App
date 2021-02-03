package com.csc.ems.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc.ems.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class undergraduateSubjectFragment extends Fragment {

    public undergraduateSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_undergraduate_subject, container, false);
    }
}
