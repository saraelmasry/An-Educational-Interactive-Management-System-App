package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorCheckDialogActivity extends AppCompatActivity implements View.OnClickListener {
    TextView x1,x2,x3,x4,x5,x6,x7,x8;
    Button Insert;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String y9,y10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_check_dialog);
        x1=findViewById(R.id.QuestionCh);
        x2=findViewById(R.id.AnswerCh1);
        x3=findViewById(R.id.AnswerCh2);
        x4=findViewById(R.id.AnswerCh3);
        x5=findViewById(R.id.AnswerCh4);
        x6=findViewById(R.id.AnswerCh5);
        x7=findViewById(R.id.AnswerCh6);
        x8=findViewById(R.id.AnswerCh);
        Insert=findViewById(R.id.btnInsertCh);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.PROJECT_REFRANCE);

        String y1=getIntent().getStringExtra("Question");
        String y2=getIntent().getStringExtra("Checkbox 1");
        String y3=getIntent().getStringExtra("Checkbox 2");
        String y4=getIntent().getStringExtra("Checkbox 3");
        String y5=getIntent().getStringExtra("Checkbox 4");
        String y6=getIntent().getStringExtra("Checkbox 5");
        String y7=getIntent().getStringExtra("Checkbox 6");
        String y8=getIntent().getStringExtra("Answer");
        y9=getIntent().getStringExtra("id q1");
        y10=getIntent().getStringExtra("iid1");
        x1.setText(y1);
        x2.setText(y2);
        x3.setText(y3);
        x4.setText(y4);
        x5.setText(y5);
        x6.setText(y6);
        x7.setText(y7);
        x8.setText(y8);

        Insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(y9).child("Assignment Number").setValue(y10);

        Intent ent=new Intent(DoctorCheckDialogActivity.this, DoctorInsertOrFinishActivity.class);
        startActivity(ent);
    }
}
