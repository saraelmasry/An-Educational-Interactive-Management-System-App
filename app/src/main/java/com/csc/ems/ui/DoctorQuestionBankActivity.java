package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csc.ems.R;

public class DoctorQuestionBankActivity extends AppCompatActivity implements View.OnClickListener {
    TextView CreateQuestion,ShowQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_question_bank);
        itiateView();
    }

    private void itiateView() {
        CreateQuestion=findViewById(R.id.CreateQuestion);
        ShowQuestion=findViewById(R.id.ShowQuestion);
        CreateQuestion.setOnClickListener(this);
        ShowQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.CreateQuestion:
                Intent intent1 = new Intent(DoctorQuestionBankActivity.this , DoctorTypesOfQuestionActivity.class);
                startActivity(intent1);
                break;
            case R.id.ShowQuestion:
                Intent intent2 = new Intent(DoctorQuestionBankActivity.this , DoctorShowQuestionActivity.class);
                startActivity(intent2);
                break;



        }
    }
}
