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
public class AdminRequestIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTitle , mDescribe , mTools , mMethodology , mObjective;
    Button mAccept , mReject ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String mDoctorId;
    String mStudentID;
    String mProjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_idea);
        intiateView();
        getDataFromDatabase();

    }
    private void getDataFromDatabase() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String projectStatus = data.child("doctor_project_status").getValue().toString();
                    if (projectStatus.equalsIgnoreCase("one")){
                        String projectId = data.child("project_id").getValue().toString();
                        String Title = data.child("project_title").getValue().toString();
                        String Describe = data.child("project_describe").getValue().toString();
                        String Tools = data.child("project_tools").getValue().toString();
                        String Methodology = data.child("project_methodology").getValue().toString();
                        String Objective = data.child("project_objective").getValue().toString();
                        String studentID = data.child("student_id").getValue().toString();
                        String doctorID = data.child("doctor_id").getValue().toString();
                        mDoctorId = doctorID;
                        mStudentID = studentID;
                        mTitle.setText(Title);
                        mDescribe.setText(Describe);
                        mTools.setText(Tools);
                        mMethodology.setText(Methodology);
                        mObjective.setText(Objective);
                        mProjectId = projectId;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void intiateView() {
        mTitle = findViewById(R.id.ad_title_describe_details);
        mDescribe = findViewById(R.id.ad_describe_describe_details);
        mTools = findViewById(R.id.ad_tools_describe_details);
        mMethodology = findViewById(R.id.ad_methodology_describe_details);
        mObjective = findViewById(R.id.ad_objective_describe_details);
        mAccept = findViewById(R.id.ad_accept_btn);
        mReject = findViewById(R.id.ad_reject_btn);
        mAccept.setOnClickListener(this);
        mReject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ad_accept_btn:
                myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("admin_project_status").setValue("one");
                break;
            case R.id.ad_reject_btn:
                myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("admin_project_status").setValue("zero");
                break;

        }

    }
}
