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

public class AssistantRequestItemProjectIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mProjectTitle;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_request_item_project_idea);
        intiateView();
        getTitleName();
    }
    private void intiateView() {
        mProjectTitle = findViewById(R.id.as_project_title);
        mProjectTitle.setOnClickListener(this);
    }


    private void getTitleName() {
        String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child(Constants.GRADUATION_PROJECT).getChildren()){
                    String assistantID = ds.child("assistant_id").getValue().toString();
                    if (assistantID.equalsIgnoreCase(UserId)){
                        String title = ds.child("project_title").getValue().toString();
                        mProjectTitle.setText(title);

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
        Intent intent = new Intent(this , AssistantRequestProjectIdeaActivity.class);
        startActivity(intent);

    }
}
