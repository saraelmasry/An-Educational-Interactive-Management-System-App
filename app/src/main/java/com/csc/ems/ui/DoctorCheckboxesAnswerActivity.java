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

public class DoctorCheckboxesAnswerActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EnterrQuestion,check1,check2,check3,check4,check5,check6,EnterrRightAnswer;
    Button add;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_checkboxes_answer);
        intiateView();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference(Constants.PROJECT_REFRANCE);
    }

    private void intiateView() {
        EnterrQuestion=findViewById(R.id.EnterQuestionCheck);
        EnterrRightAnswer=findViewById(R.id.EnterRightAnswerCheck);
        check1=findViewById(R.id.Check1);
        check2=findViewById(R.id.Check2);
        check3=findViewById(R.id.Check3);
        check4=findViewById(R.id.Check4);
        check5=findViewById(R.id.Check5);
        check6=findViewById(R.id.Check6);
        add=findViewById(R.id.btnAddCheck);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id=databaseReference.push().getKey();
        if(v == add){
            String x3=EnterrQuestion.getText().toString();
            String x4=EnterrRightAnswer.getText().toString();
            String x5=check1.getText().toString();
            String x6=check2.getText().toString();
            String x7=check3.getText().toString();
            String x8=check4.getText().toString();
            String x9=check5.getText().toString();
            String x10=check6.getText().toString();

            if (!x3.isEmpty()&&!x4.isEmpty()&&!x5.isEmpty()&&!x6.isEmpty()&&!x7.isEmpty()&&!x8.isEmpty()) {
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("question").setValue(x3);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("rightAnswer").setValue(x4);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("Checkbox 1").setValue(x5);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("Checkbox 2").setValue(x6);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("Checkbox 3").setValue(x7);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("Checkbox 4").setValue(x8);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("Checkbox 5").setValue(x9);
                databaseReference.child("Subject").child("Question Bank").child("Checkboxes").child(id).child("Checkbox 6").setValue(x10);
            }
            else if (EnterrQuestion.getText().toString().isEmpty()||EnterrRightAnswer.getText().toString().isEmpty()||check1.getText().toString().isEmpty()||check2.getText().toString().isEmpty()
                    ||check3.getText().toString().isEmpty()||check4.getText().toString().isEmpty()){
                EnterrQuestion.setError("Enter the question");
                EnterrRightAnswer.setError("Enter the answer");
                check1.setError("The field cannot be empty");
                check2.setError("The field cannot be empty");
                check3.setError("The field cannot be empty");
                check4.setError("The field cannot be empty");
            }
        }
    }
}
