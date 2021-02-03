package com.csc.ems.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csc.ems.R;

public class doctorSubjectFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView Subject11, Subject21, Subject31, Subject41;


    public doctorSubjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_doctor_subject, container, false);
        intiateView();

        return view;
    }
    private void intiateView() {
        Subject11 = view.findViewById(R.id.subjectDoc1);
        Subject21 = view.findViewById(R.id.subjectDoc2);
        Subject31 = view.findViewById(R.id.subjectDoc3);
        Subject41 = view.findViewById(R.id.subjectDoc4);

        Subject11.setOnClickListener(this);
        Subject21.setOnClickListener(this);
        Subject31.setOnClickListener(this);
        Subject41.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.subjectDoc1:
                nextPage( new DoctorSubjectToolsActivity() , "Cource_one");
                break;
            case R.id.subjectDoc2:
                nextPage( new DoctorSubjectToolsActivity() , "Cource_two");
                break;
            case R.id.subjectDoc3:
                nextPage( new DoctorSubjectToolsActivity() , "Cource_three");
                break;
            case R.id.subjectDoc4:
                nextPage( new DoctorSubjectToolsActivity() , "Cource_four");
                break;
                }
    }
    private void nextPage(Activity activity , String courceName){
        Intent intent = new Intent(getActivity() , activity.getClass());
        intent.putExtra("courcename" , courceName );
        startActivity(intent);
    }
}
