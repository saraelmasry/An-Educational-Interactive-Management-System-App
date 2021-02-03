package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csc.ems.R;

public class DoctorSubjectToolsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView assignment1,adversment,questionbank;
    private String CourcesName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_subject_tools);
        intiateView();
        Bundle bundle = getIntent().getExtras();
        String courcesname = bundle.getString("courcename");
        CourcesName = courcesname;
    }
    private void intiateView() {
        assignment1=findViewById(R.id.CourseAssignmentDoc);
        adversment=findViewById(R.id.dr_adds_id);
        questionbank=findViewById(R.id.CourseQuestionBankDoc);
        adversment.setOnClickListener(this);
        assignment1.setOnClickListener(this);
        questionbank.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dr_adds_id:
                nextPage(new DoctorAddsActivity() , CourcesName);
                break;
            case R.id.CourseAssignmentDoc:
                Intent intent = new Intent(DoctorSubjectToolsActivity.this , DoctorAssignmentActivity.class);
                startActivity(intent);
                break;
            case R.id.CourseQuestionBankDoc:
                Intent intent1 = new Intent(DoctorSubjectToolsActivity.this , DoctorQuestionBankActivity.class);
                startActivity(intent1);
                break;


        }

    }
    private void nextPage(Activity activity , String courcesName){
        Intent intent = new Intent(this , activity.getClass());
        startActivity(intent);
        intent.putExtra("courcename" , courcesName );
        startActivity(intent);
    }
}
