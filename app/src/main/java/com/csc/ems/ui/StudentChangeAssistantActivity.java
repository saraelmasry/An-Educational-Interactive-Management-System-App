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

public class StudentChangeAssistantActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner mChaneAssistant;
    TextView mTitle;
    Button mSubmitBtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    String mAssistantId;
    String mProjectId;
    String mProjectTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_change_assistant);
        initiateView();
        getData();
        chooseAAssistant();
    }
    private void initiateView() {
        mChaneAssistant = findViewById(R.id.spinner_change_assistant);
        mSubmitBtn = findViewById(R.id.assistant_change_submit);
        mTitle = findViewById(R.id.assistant_change_title_id);
        mSubmitBtn.setOnClickListener(this);
    }
    private void getData() {
        Intent intent = getIntent();
        String projectId = intent.getStringExtra("key");
        String projectTitle = intent.getStringExtra("key1");
        mProjectId = projectId;
        mProjectTitle = projectTitle;
        mTitle.setText(projectTitle);
    }
    private void changeAssistant() {
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("assistant_id").setValue(mAssistantId);
        myRef.child(Constants.GRADUATION_PROJECT).child(mProjectId).child("assistant_project_status").setValue("pending");
    }
    private void chooseAAssistant() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.change_assistant, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mChaneAssistant.setAdapter(adapter1);
        mChaneAssistant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String assistant_selected = parent.getItemAtPosition(position).toString();

                if (assistant_selected.equalsIgnoreCase("Eng/dalia")) {
                    String assistantId = "ZfT7dvaAnwZlmZAfDOXtc0wXApn2";
                    mAssistantId = assistantId;
                } else if (assistant_selected.equalsIgnoreCase("Eng/ahmed")) {
                    String assistantId1 = "tSC2a5eFbvO3ETmCOKTbSeKni3m1";
                    mAssistantId = assistantId1;
                } else if (assistant_selected.equalsIgnoreCase("Eng/andrue")) {
                    String assistantId2 = "8Iain1GN1kbJm1HHElocC6QOnOe2";
                    mAssistantId = assistantId2;
                }
                else if (assistant_selected.equalsIgnoreCase("Eng/marwa")) {
                    String assistantId3 = "ipwap43ECNSWhDgE7EPMARzbQhI2";
                    mAssistantId = assistantId3;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        changeAssistant();
    }
}
