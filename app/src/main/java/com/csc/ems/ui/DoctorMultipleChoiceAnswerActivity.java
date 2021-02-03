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

public class DoctorMultipleChoiceAnswerActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EnterrQuestion,choice1,choice2,choice3,choice4,choice5,choice6,EnterrRightAnswer;
    Button add;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_multiple_choice_answer);
        iniateView();
        database=FirebaseDatabase.getInstance();
        databaseReference= database.getReference(Constants.PROJECT_REFRANCE);
    }

    private void iniateView() {
        EnterrQuestion=findViewById(R.id.EnterQuestionCho);
        EnterrRightAnswer=findViewById(R.id.EnterRightAnswerCho);
        choice1=findViewById(R.id.Choice1);
        choice2=findViewById(R.id.Choice2);
        choice3=findViewById(R.id.Choice3);
        choice4=findViewById(R.id.Choice4);
        choice5=findViewById(R.id.Choice5);
        choice6=findViewById(R.id.Choice6);
        add=findViewById(R.id.btnAddCho);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id=databaseReference.push().getKey();
        if(v == add){
            String x3=EnterrQuestion.getText().toString();
            String x4=EnterrRightAnswer.getText().toString();
            String x5=choice1.getText().toString();
            String x6=choice2.getText().toString();
            String x7=choice3.getText().toString();
            String x8=choice4.getText().toString();
            String x9=choice5.getText().toString();
            String x10=choice6.getText().toString();
            if (!x3.isEmpty()&&!x4.isEmpty()&&!x5.isEmpty()&&!x6.isEmpty()&&!x7.isEmpty()&&!x8.isEmpty()) {
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("question").setValue(x3);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("rightAnswer").setValue(x4);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("choice 1").setValue(x5);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("choice 2").setValue(x6);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("choice 3").setValue(x7);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("choice 4").setValue(x8);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("choice 5").setValue(x9);
                databaseReference.child("Subject").child("Question Bank").child("Multiple Choice").child(id).child("choice 6").setValue(x10);
            }
            else if (EnterrQuestion.getText().toString().isEmpty()||EnterrRightAnswer.getText().toString().isEmpty()||choice1.getText().toString().isEmpty()||choice2.getText().toString().isEmpty()
                    ||choice3.getText().toString().isEmpty()||choice4.getText().toString().isEmpty()){
                EnterrQuestion.setError("Enter the question");
                EnterrRightAnswer.setError("Enter the answer");
                choice1.setError("The field cannot be empty");
                choice2.setError("The field cannot be empty");
                choice3.setError("The field cannot be empty");
                choice4.setError("The field cannot be empty");
            }

        }

    }
}
