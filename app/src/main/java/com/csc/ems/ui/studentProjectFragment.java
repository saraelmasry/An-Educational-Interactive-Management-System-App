package com.csc.ems.ui;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.csc.ems.R;

public class studentProjectFragment extends Fragment implements View.OnClickListener {
    View view;
    Button mRegisterProject, mRegisterGroup , mProjectStatus , mGroupDetails , mRequestJoinGroupProject , mProjectDetails;
    public studentProjectFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_project, container, false);
        intiateView();


        return view;
    }
    private void intiateView() {
        mRegisterProject = view.findViewById(R.id.create_new_idea);
        mRegisterGroup = view.findViewById(R.id.create_new_group);
        mProjectStatus = view.findViewById(R.id.project_status);
        mGroupDetails = view.findViewById(R.id.group_details);
        mRequestJoinGroupProject = view.findViewById(R.id.request_join_group_details);
        mProjectDetails = view.findViewById(R.id.project_details);
        mRegisterProject.setOnClickListener(this);
        mRegisterGroup.setOnClickListener(this);
        mProjectStatus.setOnClickListener(this);
        mGroupDetails.setOnClickListener(this);
        mRequestJoinGroupProject.setOnClickListener(this);
        mProjectDetails.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_new_idea:
                nextPage(new StudentCreateIdeaActivity());
                break;
            case R.id.create_new_group:
                nextPage(new StudentRegisterNewGroupActivity());
                break;
            case R.id.project_status:
                nextPage(new StudentProjectStatusActivity());
                break;
            case R.id.group_details:
                nextPage(new StudentGroupDetailsActivity());
                break;
            case R.id.request_join_group_details:
                nextPage(new StudentRequestJoinGroupActivity());
                break;
            case R.id.project_details:
                nextPage(new  StudentProjectDetailsActivity());
                break;
        }
    }
    private void nextPage(Activity activity){
        Intent intent = new Intent(getActivity() , activity.getClass());
        startActivity(intent);

    }
}
