package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class DoctorShowQuestionActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    List<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter ;
    private static final String TAG = "ShowQuestionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_show_question);
        listView=findViewById(R.id.listofshow);
        databaseReference= FirebaseDatabase.getInstance().getReference(Constants.PROJECT_REFRANCE);
    }
    @Override
    protected void onStart() {
        super.onStart();
        arrayAdapter= new ArrayAdapter<>(DoctorShowQuestionActivity.this,android.R.layout.simple_list_item_1,arrayList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot ds : dataSnapshot.child("Subject").child("Question Bank").child("Checkboxes").getChildren()){
                    //String input=ds.getValue(String.class);

                    Log.d(TAG, "test onDataChange: "+ds.getValue());

                    String mod=ds.child("question").getValue().toString();
                    arrayList.add(mod);

                    listView.setAdapter(arrayAdapter);
                }
                for(DataSnapshot dl : dataSnapshot.child("Subject").child("Question Bank").child("Short Answer").getChildren()){

                    Log.d(TAG, "test onDataChange: "+dl.getValue());

                    String mod=dl.child("question").getValue().toString();
                    arrayList.add(mod);
                    listView.setAdapter(arrayAdapter);
                }
                for(DataSnapshot dm : dataSnapshot.child("Subject").child("Question Bank").child("Paragraph").getChildren()){

                    Log.d(TAG, "test onDataChange: "+dm.getValue());

                    String mod=dm.child("question").getValue().toString();
                    arrayList.add(mod);
                    listView.setAdapter(arrayAdapter);
                }
                for(DataSnapshot dn : dataSnapshot.child("Subject").child("Question Bank").child("Multiple Choice").getChildren()){

                    Log.d(TAG, "test onDataChange: "+dn.getValue());

                    String mod=dn.child("question").getValue().toString();
                    arrayList.add(mod);
                    listView.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}