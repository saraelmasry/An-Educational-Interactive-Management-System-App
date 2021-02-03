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

public class DoctorMultiDialogActivity extends AppCompatActivity implements View.OnClickListener {
    TextView x1,x2,x3,x4,x5,x6,x7,x8;
    Button Insert;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String y9;
    String y10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_multi_dialog);
        x1=findViewById(R.id.QuestionMu);
        x2=findViewById(R.id.AnswerMu1);
        x3=findViewById(R.id.AnswerMu2);
        x4=findViewById(R.id.AnswerMu3);
        x5=findViewById(R.id.AnswerMu4);
        x6=findViewById(R.id.AnswerMu5);
        x7=findViewById(R.id.AnswerMu6);
        x8=findViewById(R.id.AnswerMu);
        Insert=findViewById(R.id.btnInsertMu);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.PROJECT_REFRANCE);

        String y1=getIntent().getStringExtra("question");
        String y2=getIntent().getStringExtra("choice 1");
        String y3=getIntent().getStringExtra("choice 2");
        String y4=getIntent().getStringExtra("choice 3");
        String y5=getIntent().getStringExtra("choice 4");
        String y6=getIntent().getStringExtra("choice 5");
        String y7=getIntent().getStringExtra("choice 6");
        String y8=getIntent().getStringExtra("rightAnswer");
        y9=getIntent().getStringExtra("id q2");
        y10=getIntent().getStringExtra("iid2");
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
        databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(y9).child("Assignment Number").setValue(y10);

        Intent ent=new Intent(DoctorMultiDialogActivity.this, DoctorInsertOrFinishActivity.class);
        startActivity(ent);

    }
}
