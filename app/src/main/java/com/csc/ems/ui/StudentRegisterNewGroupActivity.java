package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
public class StudentRegisterNewGroupActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEtInputNewName;
    Button mBtnAddNewName , mBtnSubmit;
    ListView mShowTeamMember;
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> myList;
    ArrayList<String> myListId;
    ArrayAdapter adapter;
    String mStudentNameInDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register_new_group);
        intiateView();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ems");
        myList = new ArrayList<String>();
        myListId = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, myList);
    }
    private void createGroup(){
        String newName = mEtInputNewName.getText().toString();
        int arrayCount = myList.size();
        int numberOfStudent = 2;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.child("students").getChildren()){
                    String nameInDatabase = ds.child("student_name").getValue(String.class);
                    mStudentNameInDatabase = nameInDatabase;
                    if (newName.equalsIgnoreCase(nameInDatabase) && arrayCount<numberOfStudent){
                        String studentId = ds.child("student_id").getValue(String.class);
                        String groupKey = myRef.child("groups").push().getKey();
                        myList.add(newName);
                        myListId.add(studentId);
                        mShowTeamMember.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void submitGroup(){
        String groupKey = myRef.child("groups").push().getKey();
        for(int i=0; i<myListId.size(); i++){
            String id = myListId.get(i);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("students").child(id).child("student_name").getValue().toString();
                    myRef.child("groups").child(groupKey).child(id).child("student_name").setValue(name);
                    myRef.child("groups").child(groupKey).child(id).child("group_key").setValue(groupKey);
                    myRef.child("groups").child(groupKey).child(id).child("student_id").setValue(id);
                    myRef.child("students").child(id).child("group_key").setValue(groupKey);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
    private void intiateView() {
        mEtInputNewName = findViewById(R.id.input_new_name);
        mShowTeamMember = findViewById(R.id.show_team_member);
        mBtnAddNewName = findViewById(R.id.add_new_student);
        mBtnSubmit = findViewById(R.id.btn_submit_group);
        mBtnAddNewName.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_new_student:
                createGroup();
                break;
            case R.id.btn_submit_group:
                submitGroup();
                break;
        }
    }
}
