package com.csc.ems.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.csc.ems.R;

public class assistantProjectFragment extends Fragment {
    View view;
    Button mRequestIdeaBtn;

    public assistantProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_assistant_project, container, false);
        intateView();
        return view;
    }

    private void intateView() {
        mRequestIdeaBtn = view.findViewById(R.id.requet_idea);
        mRequestIdeaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , AssistantRequestItemProjectIdeaActivity.class);
                startActivity(intent);
            }
        });
    }
}
