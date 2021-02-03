package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorRequestItemProjectIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mProjectTitle;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_item_request_project_idea);
        intiateView();
        getTitleName();
    }
    private void getTitleName() {
        final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){

                    String doctorId = ds.child("doctor_id").getValue().toString();
                    if (UserId.equalsIgnoreCase(doctorId)){
                        String title = ds.child("project_title").getValue(String.class);
                        mProjectTitle.setText(title);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void intiateView() {
        mProjectTitle = findViewById(R.id.project_title);
        mProjectTitle.setOnClickListener(this);
    }
    public void onClick(View v) {

        Intent intent = new Intent(this , DoctorRequestProjectIdeaActivity.class);
        startActivity(intent);
    }
}
