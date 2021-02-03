package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.csc.ems.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorShortDialogActivity extends AppCompatActivity implements View.OnClickListener {
    TextView x1,x2;
    Button Insert;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    String y3,y4,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_short_dialog);
        x1=findViewById(R.id.EnterQuestionD);
        x2=findViewById(R.id.EnterRightAnswerD);
        Insert=findViewById(R.id.btnInsert);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("ems");

        y1=getIntent().getStringExtra("Question");
        y2=getIntent().getStringExtra("Answer");
        y4=getIntent().getStringExtra("id q");
        y3=getIntent().getStringExtra("id3");

        x1.setText(y1);
        x2.setText(y2);


        Insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        databaseReference.child("Subject").child("Question Bank").child("Short Answer").child(y4).child("Assignment Number").setValue(y3);

        Intent in = new Intent(DoctorShortDialogActivity.this, DoctorInsertOrFinishActivity.class);
        in.putExtra("id4", y3);
        startActivity(in);
        finish();
    }
}
