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

public class studentSubjectFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView Subject1,Subject2,Subject3,Subject4;

    public studentSubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_subject, container, false);
        intiateView();
        return view;
    }
    private void intiateView() {
        Subject1=view.findViewById(R.id.subject1);
        Subject2=view.findViewById(R.id.subject2);
        Subject3=view.findViewById(R.id.subject3);
        Subject4=view.findViewById(R.id.subject4);

        Subject1.setOnClickListener(this);
        Subject2.setOnClickListener(this);
        Subject3.setOnClickListener(this);
        Subject4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subject1:
                nextPage(new StudentSubjectToolsActivity() , "Cource_one");
                break;
            case R.id.subject2:
                nextPage(new StudentSubjectToolsActivity() , "Cource_two");
                break;
            case R.id.subject3:
                nextPage(new StudentSubjectToolsActivity() , "Cource_three");
                break;
            case R.id.subject4:
                nextPage(new StudentSubjectToolsActivity() , "Cource_four");
                break;
        }

    }
    private void nextPage(Activity activity , String courceName){
        Intent intent = new Intent(getActivity() , activity.getClass());
        startActivity(intent);
        intent.putExtra("courcename" , courceName );
        startActivity(intent);
    }
}
