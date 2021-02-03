package com.csc.ems.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.csc.ems.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class doctorProjectFragment extends Fragment implements View.OnClickListener {
    View view;
    Button createProject , requestProject;

    public doctorProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doctor_project, container, false);
        intiateView();
        return view;
    }
    private void intiateView() {
        createProject = view.findViewById(R.id.admin_create_new_idea);
        requestProject = view.findViewById(R.id.requet_idea);
        createProject.setOnClickListener(this);
        requestProject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.admin_create_new_idea:
                Intent intent1 = new Intent(getActivity() , DoctorCreateIdeaActivity.class);
                startActivity(intent1);
                break;
            case R.id.requet_idea:
                Intent intent2 = new Intent(getActivity() , DoctorRequestItemProjectIdeaActivity.class);
                startActivity(intent2);
                break;

        }

    }
}
