package com.csc.ems.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.csc.ems.R;

public class adminProjectFragment extends Fragment {
    View view;
    Button mRequestIdeaBtn;

    public adminProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_project, container, false);
        mRequestIdeaBtn = view.findViewById(R.id.ad_requet_idea);
        mRequestIdeaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , AdminRequistListOfProjectActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
