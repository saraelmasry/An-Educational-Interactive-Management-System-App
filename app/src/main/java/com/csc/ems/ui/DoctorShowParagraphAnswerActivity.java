package com.csc.ems.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class DoctorShowParagraphAnswerActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listViewpara;
    java.util.List<String> List=new ArrayList<>();
    List<String> List2=new ArrayList<>();
    List<String> List3=new ArrayList<>();
    ArrayAdapter<String> Adapter ;
    String a;
    private static final String TAG = "ShowParagraphAnswer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_show_paragraph_answer);

        a=getIntent().getStringExtra("assignment id");


        listViewpara=findViewById(R.id.listofshowPara);

        databaseReference= FirebaseDatabase.getInstance().getReference(Constants.PROJECT_REFRANCE);

        listViewpara.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String v1=List.get(position);
                String v2=List2.get(position);
                String v3=List3.get(position);
                Intent intent1=new Intent(DoctorShowParagraphAnswerActivity.this,DoctorLongDialogActivity.class);
                intent1.putExtra("Question",v1);
                intent1.putExtra("Answer",v2);
                intent1.putExtra("id q",v3);
                intent1.putExtra("iid",a);
                startActivity(intent1);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Adapter= new ArrayAdapter<>(DoctorShowParagraphAnswerActivity.this,android.R.layout.simple_list_item_1,List);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List.clear();
                List2.clear();
                List3.clear();
                for(DataSnapshot ds : dataSnapshot.child("Subject").child("Question Bank").child("Paragraph").getChildren()){
                    //String input=ds.getValue(String.class);

                    Log.d(TAG, "test onDataChange: "+ds.getValue());

                    String mod=ds.child("question").getValue().toString();
                    List.add(mod);
                    String mod1=ds.child("rightAnswer").getValue().toString();
                    List2.add(mod1);
                    String mod2=ds.getKey();
                    List3.add(mod2);

                    listViewpara.setAdapter(Adapter);
                }


            }
            
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
