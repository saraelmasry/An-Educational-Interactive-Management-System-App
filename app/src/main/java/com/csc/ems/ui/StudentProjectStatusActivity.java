package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProjectStatusActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mTitle , mDoctorName , mAssistantNAme , mDoctorModification;
    LinearLayout mAssistantColorState , mDoctorColorState  , mFinalStateColor;
    Button mChangeDoctor , mChangeAssistant;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    String mProjectTitle;
    String mDoctorId;
    String mAssistantId;
    String mModifications;
    String mProjectStatus;
    String mProjectId;
    String mAssistantState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_project_status);
        intiateView();
        projectStatus();
        projectData();
    }
    private void projectData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String doctorName = dataSnapshot.child("users").child(mDoctorId).child("name").getValue().toString();
                String assistantName = dataSnapshot.child("users").child(mAssistantId).child("name").getValue().toString();
                mTitle.setText(mProjectTitle);
                mDoctorName.setText(doctorName);
                mAssistantNAme.setText(assistantName);
                mDoctorModification.setText(mModifications);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void projectStatus() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String studentId = ds.child("student_id").getValue().toString();
                    String adminState = ds.child("admin_project_status").getValue().toString();
                    String assistantState = ds.child("assistant_project_status").getValue().toString();
                    String doctorState = ds.child("doctor_project_status").getValue().toString();
                    String projectTitle = ds.child("project_title").getValue().toString();
                    String doctorId = ds.child("doctor_id").getValue().toString();
                    String assistantId = ds.child("assistant_id").getValue().toString();
                    String modifications = ds.child("doctor_comment").getValue().toString();
                    String projectId = ds.child("project_id").getValue().toString();

                    mProjectTitle = projectTitle;
                    mDoctorId  = doctorId;
                    mAssistantId = assistantId;
                    mModifications = modifications;
                    mProjectStatus = doctorState;
                    mProjectId = projectId;
                    mAssistantState = assistantState;

                    //get admin data
                    if (UserId.equalsIgnoreCase(studentId) && adminState.equalsIgnoreCase("one") ){
                        mFinalStateColor.setBackgroundColor(Color.GREEN);
                    }
                    else if (adminState.equalsIgnoreCase("zero")){
                        mFinalStateColor.setBackgroundColor(Color.RED);
                    }

                    //get assistant data
                    if (UserId.equalsIgnoreCase(studentId) && assistantState.equalsIgnoreCase("one") ){
                        mAssistantColorState.setBackgroundColor(Color.GREEN);
                    }
                    else if (assistantState.equalsIgnoreCase("zero")){
                        mAssistantColorState.setBackgroundColor(Color.RED);
                    }
                    //get doctor data
                    if (UserId.equalsIgnoreCase(studentId) && doctorState.equalsIgnoreCase("one") ){
                        mDoctorColorState.setBackgroundColor(Color.GREEN);
                    }
                    else if (doctorState.equalsIgnoreCase("zero")){
                        mDoctorColorState.setBackgroundColor(Color.RED);
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void assistantState() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String studentId = ds.child("student_id").getValue().toString();
                    String assistantState = ds.child("assistant_project_status").getValue().toString();
                    if (UserId.equalsIgnoreCase(studentId) && assistantState.equalsIgnoreCase("one") ){
                        mAssistantColorState.setBackgroundColor(Color.GREEN);
                    }
                    else if (assistantState.equalsIgnoreCase("zero")){
                        mAssistantColorState.setBackgroundColor(Color.RED);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void doctorState() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String studentId = ds.child("student_id").getValue().toString();
                    String doctorState = ds.child("doctor_project_status").getValue().toString();
                    if (UserId.equalsIgnoreCase(studentId) && doctorState.equalsIgnoreCase("one") ){
                        mDoctorColorState.setBackgroundColor(Color.GREEN);
                    }
                    else if (doctorState.equalsIgnoreCase("zero")){
                        mDoctorColorState.setBackgroundColor(Color.RED);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void intiateView() {
        mAssistantColorState = findViewById(R.id.assistant_color_state_id);
        mDoctorColorState = findViewById(R.id.doctor_color_state_id);
        mFinalStateColor = findViewById(R.id.final_color_state_id);
        mChangeDoctor = findViewById(R.id.change_doctor_btn);
        mChangeAssistant = findViewById(R.id.change_assistant_btn);
        mTitle = findViewById(R.id.st_project_title);
        mDoctorName = findViewById(R.id.st_doctor_name);
        mAssistantNAme = findViewById(R.id.st_asisstant_name);
        mDoctorModification = findViewById(R.id.doctor_modification);
        mChangeDoctor.setOnClickListener(this);
        mChangeAssistant.setOnClickListener(this);
    }
    private void changeDoctor(){
        if (mProjectStatus.equalsIgnoreCase("zero")){
            Intent intent = new Intent(StudentProjectStatusActivity.this , StudentChangeDoctorActivity.class);
            intent.putExtra("key" , mProjectId);
            intent.putExtra("key1" , mProjectTitle);
            startActivity(intent);
        }
    }
    private void changeAssistant(){
        if (mAssistantState.equalsIgnoreCase("zero")){
            Intent intent = new Intent(StudentProjectStatusActivity.this , StudentChangeAssistantActivity.class);
            intent.putExtra("key" , mProjectId);
            intent.putExtra("key1" , mProjectTitle);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_doctor_btn:
                changeDoctor();
                break;
            case R.id.change_assistant_btn:
                changeAssistant();
                break;


        }


    }
}
