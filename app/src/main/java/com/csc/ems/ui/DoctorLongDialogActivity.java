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

public class DoctorLongDialogActivity extends AppCompatActivity implements View.OnClickListener {
    TextView x1,x2;
    Button Insert;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String y3,y4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_long_dialog);
        x1=findViewById(R.id.QuestionPar);
        x2=findViewById(R.id.AnswerPar);
        Insert=findViewById(R.id.btnInsertPar);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.PROJECT_REFRANCE);

        String y1=getIntent().getStringExtra("Question");
        String y2=getIntent().getStringExtra("Answer");
        y4=getIntent().getStringExtra("id q");
        y3=getIntent().getStringExtra("iid");
        x1.setText(y1);
        x2.setText(y2);

        Insert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        databaseReference.child("Subject").child("Question Bank").child("Paragraph").child(y4).child("Assignment Number").setValue(y3);

        Intent ent=new Intent(DoctorLongDialogActivity.this, DoctorInsertOrFinishActivity.class);
        startActivity(ent);

    }
}
