package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.csc.ems.data.CreateNewProject;
import com.csc.ems.models.User;
import com.csc.ems.models.UserData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
public class StudentCreateIdeaActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, discribtion, tools, objective, methodology;
    Button submit;
    Spinner project_filed;
    Spinner choose_assistant;
    Spinner choose_docter;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    private String doctor_id;
    private String assistant_id;
    private String project_field;
    String mDoctorSelect;
    String mProjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create_idea);
          database = FirebaseDatabase.getInstance();
          mDatabase = database.getReference(Constants.PROJECT_REFRANCE);
          intiateView();
          chooseADoctor();
          chooseAAssistant();
          chooseProjectField();
    }
    private void intiateView() {
        title = findViewById(R.id.prject_title);
        tools = findViewById(R.id.txt_tools_method);
        submit = findViewById(R.id.create_idea_submitt);
        objective = findViewById(R.id.txt_project_objectives);
        discribtion = findViewById(R.id.txt_discribe_project);
        methodology = findViewById(R.id.txt_project_methodology);
        project_filed = findViewById(R.id.spinner_project_filed);
        choose_assistant = findViewById(R.id.spinner_choose_assistant);
        choose_docter = findViewById(R.id.spinner_choose_doctor);
        submit.setOnClickListener(this);
    }
    private void chooseADoctor() {
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.choose_doctor, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choose_docter.setAdapter(adapter2);
        choose_docter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String doctor_selected = parent.getItemAtPosition(position).toString();
                if (doctor_selected.equalsIgnoreCase("Dr/salah shaban")) {
                    String doctorID = "9SivH9nFEONzntuYkyYmXkR1jx23";
                    doctor_id = doctorID;
                } else if (doctor_selected.equalsIgnoreCase("Dr/mohamed torky")) {
                    String doctorID1 = "jq3UIqM6oqRW7NT35r5BiMq0lTT2";
                    doctor_id = doctorID1;
                } else if (doctor_selected.equalsIgnoreCase("Dr/mohamed hassan")) {
                    String doctorID2 = "FUjPG4O5ZvNohdGq7Bp24UJNWeC3";
                    doctor_id = doctorID2;
                }
                else if (doctor_selected.equalsIgnoreCase("Dr/kamal hamoda")) {
                    String doctorID3 = "T4F7PBtOg2f9HHr9zJZSqAIGxWs1";
                    doctor_id = doctorID3;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void chooseAAssistant() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.choose_assistant, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choose_assistant.setAdapter(adapter1);
        choose_assistant.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String assistant_selected = parent.getItemAtPosition(position).toString();

                if (assistant_selected.equalsIgnoreCase("Eng/dalia")) {
                    String assistantId = "ZfT7dvaAnwZlmZAfDOXtc0wXApn2";
                    assistant_id = assistantId;
                } else if (assistant_selected.equalsIgnoreCase("Eng/ahmed")) {
                    String assistantId1 = "tSC2a5eFbvO3ETmCOKTbSeKni3m1";
                    assistant_id = assistantId1;
                } else if (assistant_selected.equalsIgnoreCase("Eng/andrue")) {
                    String assistantId2 = "8Iain1GN1kbJm1HHElocC6QOnOe2";
                    assistant_id = assistantId2;
                }
                else if (assistant_selected.equalsIgnoreCase("Eng/marwa")) {
                    String assistantId3 = "ipwap43ECNSWhDgE7EPMARzbQhI2";
                    assistant_id = assistantId3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void chooseProjectField() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Project_Filed, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        project_filed.setAdapter(adapter);
        project_filed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String project_field_selected = parent.getItemAtPosition(position).toString();
                project_field = project_field_selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void getDoctorId(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String doctorName = dataSnapshot.child("doctors").child("doctor_name").getValue().toString();
                    if (mDoctorSelect.equalsIgnoreCase(doctorName)) {
                        String doctorId = dataSnapshot.child("doctors").child("doctor_id").getValue().toString();
                        doctorId = doctor_id;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void graduationProjectData() {
        final String student_id = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
        final String titleName = title.getText().toString();
        final String discribeeName = discribtion.getText().toString();
        final String toolsName = tools.getText().toString();
        final String methodologyName = methodology.getText().toString();
        final String objectiveName = objective.getText().toString();
        String project_id = mDatabase.child("graduation_projects").push().getKey();
        mProjectId = project_id;

        CreateNewProject createNewProject = new CreateNewProject(project_id ,student_id ,doctor_id ,assistant_id ,titleName
            ,  discribeeName ,  toolsName ,  methodologyName , objectiveName ,  project_field ,
                "pending" ,"pending" ,"pending" );
        Map<String,Object> userValues = createNewProject.toMap();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/" +Constants.GRADUATION_PROJECT +"/" +project_id,userValues);
        mDatabase.updateChildren(childUpdates);
      }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_idea_submitt:
                if(validate()) {
                    graduationProjectData();
                    Intent intent = new Intent(StudentCreateIdeaActivity.this , StudentIdeaCreatedActivity.class);
                    intent.putExtra("key" , mProjectId);
                    startActivity(intent);
                   // startActivity(new Intent(StudentCreateIdeaActivity.this, StudentIdeaCreatedActivity.class));
                }
                return;
        }
    }
    private boolean validate() {
        boolean valid = true;
        String project_title = title.getText().toString();
        String describe_project = discribtion.getText().toString();
        String project_tools = tools.getText().toString();
        String project_objective = objective.getText().toString();
        String project_methodology = methodology.getText().toString();
        if (project_title.isEmpty() && describe_project.isEmpty()
                && project_tools.isEmpty() && project_objective.isEmpty() && project_methodology.isEmpty()) {
            title.setError("field required");
            discribtion.setError("field required");
            tools.setError("field required");
            objective.setError("field required");
            methodology.setError("field required");
        }
        else if(project_title.isEmpty()){
            title.setError("field required");
        }
       else  if (describe_project.isEmpty()) {
            discribtion.setError("field required");
        }
        else if (project_tools.isEmpty()) {
            tools.setError("field required");

        }
        else if (project_objective.isEmpty()) {
            objective.setError("field required");

        }
        else if (project_methodology.isEmpty()) {
            methodology.setError("field required");
        }
        else {
            return valid;
        }
        return false;
    }
}