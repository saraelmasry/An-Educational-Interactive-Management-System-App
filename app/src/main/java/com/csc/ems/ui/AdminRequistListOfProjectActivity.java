package com.csc.ems.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminRequistListOfProjectActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mProjectTitle;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    String mProjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_requist_list_of_project);
        mProjectTitle = findViewById(R.id.ad_project_title);
        mProjectTitle.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(Constants.PROJECT_REFRANCE);
        getTitleName();


    }

    private void getTitleName() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String doctorStatus = ds.child("doctor_project_status").getValue().toString();
                    if (doctorStatus.equalsIgnoreCase("one")){
                        String titleName = ds.child("project_title").getValue().toString();
                        String projectId = ds.child("project_id").getValue().toString();
                        mProjectId = projectId;
                        mProjectTitle.setText(titleName);

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this , AdminRequestIdeaActivity.class);
        startActivity(intent);
        intent.putExtra("MESSAGE_KEY",mProjectId);

    }
}
