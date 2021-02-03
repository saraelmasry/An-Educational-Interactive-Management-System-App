package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class StudentProjectDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTitle1, mDescribe1, mTools1, mMethodology1, mObjective1;
    Button mAcceptBtn , mRejectBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_project_details);
        intiateView();
        getDataFromDatabase();
    }



    private void getDataFromDatabase() {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){

                        String Titlee = ds.child("project_title").getValue(String.class);
                        String Describee = ds.child("project_describe").getValue(String.class);
                        String Toolss = ds.child("project_tools").getValue(String.class);
                        String Methodologyy = ds.child("project_methodology").getValue(String.class);
                        String Objectivee = ds.child("project_objective").getValue(String.class);
                        mTitle1.setText(Titlee);
                        mDescribe1.setText(Describee);
                        mTools1.setText(Toolss);
                        mMethodology1.setText(Methodologyy);
                        mObjective1.setText(Objectivee);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void intiateView() {
        mTitle1 = findViewById(R.id.title_describe_details);
        mDescribe1 = findViewById(R.id.describe_describe_details);
        mTools1 = findViewById(R.id.tools_describe_details);
        mMethodology1 = findViewById(R.id.methodology_describe_details);
        mObjective1 = findViewById(R.id.objective_describe_details);
        mAcceptBtn = findViewById(R.id.st_accept_btn);
        mRejectBtn = findViewById(R.id.st_reject_btn);
        mAcceptBtn.setOnClickListener(this);
        mRejectBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.st_accept_btn:

                break;
            case R.id.st_reject_btn:

                break;




        }

    }
}