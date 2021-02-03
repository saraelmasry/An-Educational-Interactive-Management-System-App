package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class DoctorCreateAssignmentActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameofassignment, deadlinestart, deadlineend;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Button nextt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_create_assignment);
        iniateView();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(Constants.PROJECT_REFRANCE);
    }
    private void iniateView() {
        nameofassignment = findViewById(R.id.NameOfAssignment);
        deadlinestart = findViewById(R.id.DeadlineOfAssignmentstart);
        deadlineend = findViewById(R.id.DeadlineOfAssignmentend);
        nextt = findViewById(R.id.btnnext);
        nextt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == nextt) {
            String id = databaseReference.push().getKey();
            String name = nameofassignment.getText().toString();
            databaseReference.child("Subject").child("Assignment").child("Create Assignment").child(id).child("Name").setValue(name);

            String start = deadlinestart.getText().toString();
            databaseReference.child("Subject").child("Assignment").child("Create Assignment").child(id).child("Deadline Start").setValue(start);

            String end = deadlineend.getText().toString();
            databaseReference.child("Subject").child("Assignment").child("Create Assignment").child(id).child("Deadline End").setValue(end);

        }

        databaseReference.child("Subject").child("Assignment").child("Create Assignment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    String mod1=ds.getKey();

                    Intent xcv=new Intent(DoctorCreateAssignmentActivity.this,DoctorNextTypeOfQuestionActivity.class);
                    xcv.putExtra("id",mod1);
                    startActivity(xcv);
                    finish();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
