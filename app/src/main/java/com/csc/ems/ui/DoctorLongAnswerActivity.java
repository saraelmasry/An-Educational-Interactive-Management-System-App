package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorLongAnswerActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EnterrQuestion,EnterrRightAnswer;
    Button add;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_long_answer);
        iniateView();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference(Constants.PROJECT_REFRANCE);
    }

    private void iniateView() {
        EnterrQuestion=findViewById(R.id.EnterQuestionPar);
        EnterrRightAnswer=findViewById(R.id.EnterRightAnswerPar);
        add=findViewById(R.id.btnAddPar);
        add.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        String id=databaseReference.push().getKey();
        if(v == add){
            String x3=EnterrQuestion.getText().toString();
            String x4=EnterrRightAnswer.getText().toString();
            if (!x3.isEmpty()&&!x4.isEmpty()) {
                databaseReference.child("Subject").child("Question Bank").child("Paragraph").child(id).child("question").setValue(x3);
                databaseReference.child("Subject").child("Question Bank").child("Paragraph").child(id).child("rightAnswer").setValue(x4);

            }
            else if (EnterrQuestion.getText().toString().isEmpty()||EnterrRightAnswer.getText().toString().isEmpty()){
                EnterrQuestion.setError("Enter the question");
                EnterrRightAnswer.setError("Enter the answer");
            }
        }
    }
}
