package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csc.ems.R;

public class DoctorTypesOfQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView ShortAnswer,Paragraph,MultipleChoice,Checkboxes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_types_of_question);
        iniateView();
    }

    private void iniateView() {
        ShortAnswer=findViewById(R.id.ShortAnswer);
        Paragraph=findViewById(R.id.Paragraph);
        MultipleChoice=findViewById(R.id.MultipleChoice);
        Checkboxes=findViewById(R.id.Checkboxes);
        ShortAnswer.setOnClickListener(this);
        Paragraph.setOnClickListener(this);
        MultipleChoice.setOnClickListener(this);
        Checkboxes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ShortAnswer:
                Intent intent=new Intent(DoctorTypesOfQuestionActivity.this,DoctorShortAnswerActivity.class);
                startActivity(intent);
                break;
            case R.id.Paragraph:
                Intent intent1=new Intent(DoctorTypesOfQuestionActivity.this,DoctorLongAnswerActivity.class);
                startActivity(intent1);
                break;
            case R.id.MultipleChoice:
                Intent intent2=new Intent(DoctorTypesOfQuestionActivity.this,DoctorMultipleChoiceAnswerActivity.class);
                startActivity(intent2);
                break;
            case R.id.Checkboxes:
                Intent intent3=new Intent(DoctorTypesOfQuestionActivity.this,DoctorCheckboxesAnswerActivity.class);
                startActivity(intent3);
                break;
        }

    }
}
