package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class StudentGroupDetailsActivity extends AppCompatActivity {
    ListView mStudentsGroup;
    FirebaseDatabase database;
    DatabaseReference myRef;
    final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    ArrayList<String> myList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_group_details);
        mStudentsGroup = findViewById(R.id.students_group);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ems");
        myList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, myList);
        getStudentsGroup();
    }
    private void getStudentsGroup() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String groupId = dataSnapshot.child("students").child(UserId).child("group_key").getValue(String.class);
                for (DataSnapshot ds : dataSnapshot.child("groups").child("-MEZCmj0BnHYXeqa2SNsm").getChildren()){
                    String confirmationState = ds.child("confirmation_state").getValue(String.class);
                    //if (confirmationState.equalsIgnoreCase("true")){
                        String names = ds.child("student_name").getValue(String.class);
                        myList.add(names);
                    //}
                }
                mStudentsGroup.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
