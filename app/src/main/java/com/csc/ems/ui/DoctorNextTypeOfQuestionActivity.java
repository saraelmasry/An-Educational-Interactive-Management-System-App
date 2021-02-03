package com.csc.ems.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csc.ems.R;
public class DoctorNextTypeOfQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView shShort,shParagraph,shCheck,shMulti;
    String y1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_next_type_of_question);
        iniateVew();
    }

    private void iniateVew() {
        shShort=findViewById(R.id.shShortAnswer);
        shShort.setOnClickListener(this);

        shParagraph=findViewById(R.id.shParagraph);
        shParagraph.setOnClickListener(this);

        shCheck=findViewById(R.id.shCheckboxes);
        shCheck.setOnClickListener(this);

        shMulti=findViewById(R.id.shMultipleChoice);
        shMulti.setOnClickListener(this);

        y1=getIntent().getStringExtra("id");
    }

    @Override
    public void onClick(View v) {
        if(v==shShort){
            Intent intent1 = new Intent(DoctorNextTypeOfQuestionActivity.this, DoctorShowShortAnswerActivity.class);
            intent1.putExtra("id2",y1);
            startActivity(intent1);
            finish();

        }
        if(v==shParagraph){
            Intent intent2 = new Intent(DoctorNextTypeOfQuestionActivity.this, DoctorShowParagraphAnswerActivity.class);
            intent2.putExtra("id2",y1);
            startActivity(intent2);
            finish();

        }
        if(v==shMulti){
            Intent intent3 = new Intent(DoctorNextTypeOfQuestionActivity.this, DoctorShowMultipleAnswerActivity.class);
            intent3.putExtra("id2",y1);
            startActivity(intent3);
            finish();

        }
        if(v==shCheck){
            Intent intent4 = new Intent(DoctorNextTypeOfQuestionActivity.this, DoctorShowCheckboxAnswerActivity.class);
            intent4.putExtra("id2",y1);
            startActivity(intent4);
            finish();
        }
        finish();
    }
}
