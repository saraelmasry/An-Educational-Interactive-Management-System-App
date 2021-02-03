package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentRequestJoinGroupActivity extends AppCompatActivity implements View.OnClickListener {
    ListView mViewGroupMember;
    Button mConfirmGroup , mRejectGroup;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<String> myList;
    ArrayAdapter adapter;
    String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    String mGroupKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_request_join_group);
        intiateView();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("ems");
        myList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, myList);
        getGroupMemberInDatabase();
    }
    private void getGroupMemberInDatabase(){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String groupKey = dataSnapshot.child("students").child(UserId).child("group_key").getValue().toString();
                mGroupKey = groupKey;
                for (DataSnapshot ds : dataSnapshot.child("groups").child(groupKey).getChildren()){
                    String names = ds.child("student_name").getValue().toString();
                    myList.add(names);

                    mViewGroupMember.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void confirmationState(){
        myRef.child("groups").child(mGroupKey).child(UserId).child("confirmation_state").setValue("true");
    }
    private void rejectionState(){

        myRef.child("groups").child(mGroupKey).child(UserId).child("confirmation_state").setValue("false");
    }
    private void intiateView() {
        mViewGroupMember = findViewById(R.id.show_student_group_id);
        mConfirmGroup = findViewById(R.id.confirm_group_btn_id);
        mRejectGroup = findViewById(R.id.reject_group_btn_id);
        mConfirmGroup.setOnClickListener(this);
        mRejectGroup.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_group_btn_id:
                confirmationState();
                break;
            case R.id.reject_group_btn_id:
                rejectionState();
                break;


        }
    }
}
