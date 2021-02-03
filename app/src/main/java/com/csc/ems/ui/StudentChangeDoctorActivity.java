package com.csc.ems.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class StudentChangeDoctorActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner mChangeDoctor;
    TextView mTitle;
    Button mSubmitBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    String mDoctorSelcted;
    String mProjectId;
    String mProjectTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strudent_change_doctor);
        initiateView();
        getData();
        chooseDoctor();
    }
    private void changeDoctor() {
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_id").setValue(mDoctorSelcted);
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_project_status").setValue("pending");
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("doctor_comment").setValue("");
    }
    private void getData() {
        Intent intent = getIntent();
        String projectId = intent.getStringExtra("key");
        String projectTitle = intent.getStringExtra("key1");
        mProjectId = projectId;
        mProjectTitle = projectTitle;
        mTitle.setText(projectTitle);
    }

    private void initiateView() {
        mChangeDoctor = findViewById(R.id.spinner_change_doctor);
        mSubmitBtn = findViewById(R.id.doctor_change_submit);
        mTitle = findViewById(R.id.doctor_change_title_id);
        mSubmitBtn.setOnClickListener(this);
    }
    private void chooseDoctor() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.change_doctorr, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChangeDoctor.setAdapter(adapter);
        mChangeDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String doctor_selected = parent.getItemAtPosition(position).toString();

                if (doctor_selected.equalsIgnoreCase("Dr/salah shaban")) {
                    String doctorID = "9SivH9nFEONzntuYkyYmXkR1jx23";
                    mDoctorSelcted = doctorID;
                } else if (doctor_selected.equalsIgnoreCase("Dr/mohamed torky")) {
                    String doctorID1 = "jq3UIqM6oqRW7NT35r5BiMq0lTT2";
                    mDoctorSelcted = doctorID1;
                } else if (doctor_selected.equalsIgnoreCase("Dr/mohamed hassan")) {
                    String doctorID2 = "FUjPG4O5ZvNohdGq7Bp24UJNWeC3";
                    mDoctorSelcted = doctorID2;
                }
                else if (doctor_selected.equalsIgnoreCase("Dr/kamal hamoda")) {
                    String doctorID3 = "T4F7PBtOg2f9HHr9zJZSqAIGxWs1";
                    mDoctorSelcted = doctorID3;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        changeDoctor();
    }
}
