package com.csc.ems.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentSubjectToolsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView assignment,material;
    TextView mAdds;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
    private String CourcesName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject_tools);
        intiateView();
        Bundle bundle = getIntent().getExtras();
        String courcesname = bundle.getString("courcename");
        CourcesName = courcesname;
        getAdds();
    }

    private void getAdds() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String courcesAdds = dataSnapshot.child("cources").child(CourcesName).child("cources_adds").getValue(String.class);
                mAdds.setText(courcesAdds);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void intiateView() {
        mAdds  = findViewById(R.id.st_adds_id);
        assignment=findViewById(R.id.CourseAssignment);
        material=findViewById(R.id.CourseMaterial);
        assignment.setOnClickListener(this);
        material.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
    private void nextPage(Activity activity ){
        Intent intent = new Intent(this , activity.getClass());
        startActivity(intent);

    }
}
