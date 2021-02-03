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

import com.csc.ems.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorShowCheckboxAnswerActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listViewch;
    List<String> List=new ArrayList<>();
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

    private static final String TAG = "ShowCheckAnswerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_show_checkbox_answer);
        a=getIntent().getStringExtra("assignment id");

        listViewch=findViewById(R.id.listofshowCheck);

        databaseReference= FirebaseDatabase.getInstance().getReference("ems");

        listViewch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                Intent intent1 = new Intent(DoctorShowCheckboxAnswerActivity.this, DoctorCheckDialogActivity.class);
                intent1.putExtra("Question", v1);
                intent1.putExtra("Checkbox 1", v2);
                intent1.putExtra("Checkbox 2", v3);
                intent1.putExtra("Checkbox 3", v4);
                intent1.putExtra("Checkbox 4", v5);
                intent1.putExtra("Checkbox 5", v6);
                intent1.putExtra("Checkbox 6", v7);
                intent1.putExtra("Answer", v8);
                intent1.putExtra("id q1", v9);
                intent1.putExtra("iid1", a);
                startActivity(intent1);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Adapter= new ArrayAdapter<>(DoctorShowCheckboxAnswerActivity.this,android.R.layout.simple_list_item_1,List);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List.clear();
                for(DataSnapshot ds : dataSnapshot.child("Subject").child("Question Bank").child("Checkboxes").getChildren()){

                    Log.d(TAG, "test onDataChange: "+ds.getValue());

                    String mod=ds.child("question").getValue().toString();
                    List.add(mod);
                    String mod2=ds.child("Checkbox 1").getValue().toString();
                    List2.add(mod2);
                    String mod3=ds.child("Checkbox 2").getValue().toString();
                    List3.add(mod3);
                    String mod4=ds.child("Checkbox 3").getValue().toString();
                    List4.add(mod4);
                    String mod5=ds.child("Checkbox 4").getValue().toString();
                    List5.add(mod5);
                    String mod6=ds.child("Checkbox 5").getValue().toString();
                    List6.add(mod6);
                    String mod7=ds.child("Checkbox 6").getValue().toString();
                    List7.add(mod7);
                    String mod8=ds.child("rightAnswer").getValue().toString();
                    List8.add(mod8);
                    String mod9=ds.getKey();
                    List9.add(mod9);

                    listViewch.setAdapter(Adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
