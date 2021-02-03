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

public class DoctorShowMultipleAnswerActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listViewmu;
    java.util.List<String> List=new ArrayList<>();
    List<String> List2=new ArrayList<>();
    List<String> List3=new ArrayList<>();
    List<String> List4=new ArrayList<>();
    List<String> List5=new ArrayList<>();
    List<String> List6=new ArrayList<>();
    List<String> List7=new ArrayList<>();
    List<String> List8=new ArrayList<>();
    List<String> List9=new ArrayList<>();
    ArrayAdapter<String> Adapter ;
    String a;


    private static final String TAG = "ShowMultiAnswerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_show_multiple_answer);
        a=getIntent().getStringExtra("assignment id");

        listViewmu=findViewById(R.id.listofshowMulti);

        databaseReference= FirebaseDatabase.getInstance().getReference(Constants.PROJECT_REFRANCE);
        listViewmu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String v1 = List.get(position);
                String v2 = List2.get(position);
                String v3 = List3.get(position);
                String v4 = List4.get(position);
                String v5 = List5.get(position);
                String v6 = List6.get(position);
                String v7 = List7.get(position);
                String v8 = List8.get(position);
                String v9 = List9.get(position);
                Intent intent1 = new Intent(DoctorShowMultipleAnswerActivity.this, DoctorMultiDialogActivity.class);
                intent1.putExtra("question", v1);
                intent1.putExtra("choice 1", v2);
                intent1.putExtra("choice 2", v3);
                intent1.putExtra("choice 3", v4);
                intent1.putExtra("choice 4", v5);
                intent1.putExtra("choice 5", v6);
                intent1.putExtra("choice 6", v7);
                intent1.putExtra("rightAnswer", v8);
                intent1.putExtra("id q2", v9);
                intent1.putExtra("iid2", a);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Adapter= new ArrayAdapter<>(DoctorShowMultipleAnswerActivity.this,android.R.layout.simple_list_item_1,List);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List.clear();
                for(DataSnapshot ds : dataSnapshot.child("Subject").child("Question Bank").child("Multiple Choice").getChildren()){

                    Log.d(TAG, "test onDataChange: "+ds.getValue());

                    String mod=ds.child("question").getValue().toString();
                    List.add(mod);
                    String mod2=ds.child("choice 1").getValue().toString();
                    List2.add(mod2);
                    String mod3=ds.child("choice 2").getValue().toString();
                    List3.add(mod3);
                    String mod4=ds.child("choice 3").getValue().toString();
                    List4.add(mod4);
                    String mod5=ds.child("choice 4").getValue().toString();
                    List5.add(mod5);
                    String mod6=ds.child("choice 5").getValue().toString();
                    List6.add(mod6);
                    String mod7=ds.child("choice 6").getValue().toString();
                    List7.add(mod7);
                    String mod8=ds.child("rightAnswer").getValue().toString();
                    List8.add(mod8);
                    String mod9=ds.getKey();
                    List9.add(mod9);

                    listViewmu.setAdapter(Adapter);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
