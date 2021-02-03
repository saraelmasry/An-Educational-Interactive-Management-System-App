package com.csc.ems.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorAcceptIdeaWithChangeActivity extends AppCompatActivity implements View.OnClickListener {
    Button mSubmit ;
    EditText mComment;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    String mProjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_accept_idea_with_change);
        intiateView();
        getProjectId();

    }

    private void sendData() {
        String comment = mComment.getText().toString();
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_comment").setValue(comment);
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_project_status").setValue("one");

    }

    private void getProjectId() {
        Intent intent = getIntent();
        String projectId = intent.getStringExtra("key");
        mProjectId = projectId;


    }
    private void intiateView() {
        mComment = findViewById(R.id.txt_comment);
        mSubmit = findViewById(R.id.accept_after_change);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.accept_after_change:
                sendData();
                break;
        }

    }
}
