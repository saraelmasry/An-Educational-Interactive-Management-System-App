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
public class AssistantRequestProjectIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTitle , mDescribe , mTools , mMethodology , mObjective;
    Button mAccept , mReject;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String StudentID;
    String mProjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_request_project_idea);
        intiateView();
        getDataFromDatabase();
    }
    private void intiateView() {
        mTitle = findViewById(R.id.as_title_describe_details);
        mDescribe = findViewById(R.id.as_describe_describe_details);
        mTools = findViewById(R.id.as_tools_describe_details);
        mMethodology = findViewById(R.id.as_methodology_describe_details);
        mObjective = findViewById(R.id.as_objective_describe_details);
        mAccept = findViewById(R.id.as_accept_btn);
        mReject = findViewById(R.id.as_reject_btn);
        mAccept.setOnClickListener(this);
        mReject.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.as_accept_btn:
                myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("assistant_project_status").setValue("one");
                break;
            case R.id.as_reject_btn:
                myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("assistant_project_status").setValue("zero");
                break;
        }
    }
    private void getDataFromDatabase() {
        String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String assistantID = ds.child("assistant_id").getValue().toString();
                    if (assistantID.equalsIgnoreCase(UserId)){
                        String projectId = ds.child("project_id").getValue().toString();
                        String Title = ds.child("project_title").getValue(String.class);
                        String Describe = ds.child("project_describe").getValue(String.class);
                        String Tools = ds.child("project_tools").getValue(String.class);
                        String Methodology = ds.child("project_methodology").getValue(String.class);
                        String Objective = ds.child("project_objective").getValue(String.class);
                        String studentId = ds.child("student_id").getValue(String.class);
                        StudentID = studentId;
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
}
