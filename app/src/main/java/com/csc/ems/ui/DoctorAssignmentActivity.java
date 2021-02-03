package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csc.ems.R;

public class DoctorAssignmentActivity extends AppCompatActivity implements View.OnClickListener {
    TextView assignment,createassignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_assignment);
        assignment=findViewById(R.id.AssignmentsDoc);
        createassignment=findViewById(R.id.CreateAssignmentDoc);
        assignment.setOnClickListener(this);
        createassignment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AssignmentsDoc:
               // Intent intent=new Intent(DoctorAssignmentActivity.this,AssignmentByDocActivity.class);
               // startActivity(intent);
                break;

            case R.id.CreateAssignmentDoc:
                Intent intent1=new Intent(DoctorAssignmentActivity.this,DoctorCreateAssignmentActivity.class);
                startActivity(intent1);
                break;
        }

    }
}
