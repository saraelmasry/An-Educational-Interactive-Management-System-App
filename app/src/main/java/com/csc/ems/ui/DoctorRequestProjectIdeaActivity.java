package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
public class DoctorRequestProjectIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTitle , mDescribe , mTools , mMethodology , mObjective;
    Button mAccept , mReject , mAcceptWithChangeBtn ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String StudentID;
    String mProjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_request_project_idea);
        intiateView();
        getProjectData();
        getDataFromDatabase();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.accept_btn:
                myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_project_status").setValue("one");
                break;
            case R.id.reject_btn:
                myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_project_status").setValue("zero");
                break;
            case R.id.accept_with_change_btn:
                Intent intent = new Intent(DoctorRequestProjectIdeaActivity.this , DoctorAcceptIdeaWithChangeActivity.class);
                intent.putExtra("key", mProjectId);
                startActivity(intent);
                break;
        }
    }
    private void getProjectData(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String doctorID = ds.child("doctor_id").getValue().toString();
                    if (UserId.equalsIgnoreCase(doctorID)){
                        String projectId = ds.child("project_id").getValue().toString();
                        mProjectId = projectId;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    // OnChildUpdate
    // firebase weekend -> Udacity

    private void getDataFromDatabase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Title = dataSnapshot.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("project_title").getValue(String.class);
                String Describe = dataSnapshot.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("project_describe").getValue(String.class);
                String Tools = dataSnapshot.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("project_tools").getValue(String.class);
                String Methodology = dataSnapshot.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("project_methodology").getValue(String.class);
                String Objective = dataSnapshot.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("project_objective").getValue(String.class);
                String studentID = dataSnapshot.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("student_id").getValue(String.class);
                StudentID = studentID;
                mTitle.setText(Title);
                mDescribe.setText(Describe);
                mTools.setText(Tools);
                mMethodology.setText(Methodology);
                mObjective.setText(Objective);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void intiateView() {
        mTitle = findViewById(R.id.title_describe_details);
        mDescribe = findViewById(R.id.describe_describe_details);
        mTools = findViewById(R.id.tools_describe_details);
        mMethodology = findViewById(R.id.methodology_describe_details);
        mObjective = findViewById(R.id.objective_describe_details);
        mAcceptWithChangeBtn = findViewById(R.id.accept_with_change_btn);
        mAccept = findViewById(R.id.accept_btn);
        mReject = findViewById(R.id.reject_btn);
        mAccept.setOnClickListener(this);
        mReject.setOnClickListener(this);
        mAcceptWithChangeBtn.setOnClickListener(this);
    }
}