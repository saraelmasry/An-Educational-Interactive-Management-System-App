package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.csc.ems.pojo.Model;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
public class StudentIdeaCreatedActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEtInputNewName;
    Button mBtnAddNewName , mBtnSubmit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView mListView;
    ArrayList<String> myList;
    ArrayList<String> myListId;
    ArrayAdapter adapter;
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    String mGroupKey;
    String mProjectId;
    String mStudentNameInDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_idea_created);
        intiateView();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Constants.PROJECT_REFRANCE);
        getGroupId();
        myList = new ArrayList<String>();
        myListId = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, myList);
    }
    private void getGroupId(){
        Intent intent = getIntent();
        String projectId = intent.getStringExtra("key");
        mProjectId = projectId;
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
                        myList.add(newName);
                        myListId.add(studentId);
                        mListView.setAdapter(adapter);
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
       // String groupKey = myRef.child("groups").push().getKey();
        for(int i=0; i<myListId.size(); i++){
            String id = myListId.get(i);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("students").child(id).child("student_name").getValue().toString();

                    myRef.child(Constants.GROUPS).child(mProjectId).child(id).child("student_name").setValue(name);
                    myRef.child(Constants.GROUPS).child(mProjectId).child(id).child("group_key").setValue(mProjectId);
                    myRef.child(Constants.GROUPS).child(mProjectId).child(id).child("student_id").setValue(id);
                    myRef.child(Constants.GROUPS).child(mProjectId).child(id).child("student_status").setValue("pending");


                  //  myRef.child("groups").child(groupKey).child(id).child("student_name").setValue(name);
                  //  myRef.child("groups").child(groupKey).child(id).child("group_key").setValue(groupKey);
                  //  myRef.child("groups").child(groupKey).child(id).child("student_id").setValue(id);
                  //  myRef.child("students").child(id).child("group_key").setValue(groupKey);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
    private void intiateView() {
        mEtInputNewName = findViewById(R.id.new_name_id);
        mBtnAddNewName = findViewById(R.id.add_new_name_btn);
        mBtnSubmit = findViewById(R.id.btn_submit_group_id);
        mListView = findViewById(R.id.list_view_id);
        mBtnAddNewName.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
                case R.id.add_new_name_btn:
                    createGroup();
                    break;
                case R.id.btn_submit_group_id:
                    submitGroup();
                    break;
        }
    }
}