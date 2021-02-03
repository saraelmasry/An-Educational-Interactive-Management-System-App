package com.csc.ems.ui;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class DoctorCreateIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, discribtion, tools, objective, methodology;
    Button submit;
    Spinner project_filed;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String mProjectFieldSelcted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_create_idea);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ems");
        intiateView();
        chooseProjectField();
    }
    public void graduationProjectData() {
        final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//doctorID
        final String titleName = title.getText().toString();
        final String describeName = discribtion.getText().toString();
        final String toolsName = tools.getText().toString();
        final String methodologyName = methodology.getText().toString();
        final String objectiveName = objective.getText().toString();
        String projectNumber = myRef.child("graduation_project").push().getKey();

        myRef.child("graduation_project").child(projectNumber).child("project_number").setValue(projectNumber);
        myRef.child("graduation_project").child(projectNumber).child("doctor_id").setValue(UserId);
        myRef.child("graduation_project").child(projectNumber).child("project_title").setValue(titleName);
        myRef.child("graduation_project").child(projectNumber).child("project_describe").setValue(describeName);
        myRef.child("graduation_project").child(projectNumber).child("project_tools").setValue(toolsName);
        myRef.child("graduation_project").child(projectNumber).child("project_methodology").setValue(methodologyName);
        myRef.child("graduation_project").child(projectNumber).child("project_objective").setValue(objectiveName);
        myRef.child("graduation_project").child(projectNumber).child("project_status").setValue("pending");
        myRef.child("graduation_project").child(projectNumber).child("project_field").setValue(mProjectFieldSelcted);
    }
    private void chooseProjectField() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Project_Filed, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project_filed.setAdapter(adapter);
        project_filed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String project_field_selected = parent.getItemAtPosition(position).toString();
                mProjectFieldSelcted = project_field_selected;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void intiateView() {
        title = findViewById(R.id.dr_prject_title);
        tools = findViewById(R.id.dr_txt_tools_method);
        submit = findViewById(R.id.dr_create_idea_submitt);
        objective = findViewById(R.id.dr_txt_project_objectives);
        discribtion = findViewById(R.id.dr_txt_discribe_project);
        methodology = findViewById(R.id.dr_txt_project_methodology);
        project_filed = findViewById(R.id.dr_spinner_project_filed);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dr_create_idea_submitt:
                graduationProjectData();
                break;
        }
    }
}
