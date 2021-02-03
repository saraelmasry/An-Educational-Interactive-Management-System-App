package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class DoctorCollectQuestionOfAssignmentActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView name,start,end;
    ListView showQ;
    List<String> List=new ArrayList<>();
    List<String> List2=new ArrayList<>();
    List<String> List3=new ArrayList<>();
    List<String> List4=new ArrayList<>();
    List<String> List5=new ArrayList<>();
    List<String> List6=new ArrayList<>();
    List<String> List7=new ArrayList<>();
    ArrayAdapter<String> Adapter ;
    String mT , b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_collect_question_of_assignment);
        String t=getIntent().getStringExtra("id5");
        mT = t;
        name=findViewById(R.id.NameOfAssignment);
        start=findViewById(R.id.DeadlineOfAssignmentstart);
        end=findViewById(R.id.DeadlineOfAssignmentend);
        showQ=findViewById(R.id.listshow);
        databaseReference= FirebaseDatabase.getInstance().getReference(Constants.PROJECT_REFRANCE);
        Adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, List);
    }
    private void function() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


               String dddd = dataSnapshot.child("Subject").child("Question Bank").child("Short Answer").child("-MEdyV4neAHwhvWK0PRN").child("question").getValue().toString();
               List.add(dddd);
               showQ.setAdapter(Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               /*for (DataSnapshot ds : dataSnapshot.child("Subject").child("Assignment").child("Create Assignment").getChildren())
               {
                   b=ds.child("Name").getValue().toString();
                   c=ds.child("Deadline Start").getValue().toString();
                   d=ds.child("Deadline End").getValue().toString();

                   name.setText(b);
                   start.setText(c);
                   end.setText(d);

               }*/
                //String AssignmentId=dataSnapshot.child("Subject").child("Question Bank").child("Short Answer").child("Assignment Number").getKey();

                for (DataSnapshot ds :dataSnapshot.child("Subject").child("Question Bank").child("Short Answer").getChildren()) {
                    String AssignmentId = ds.child("Assignment Number").getValue().toString();

                    if (AssignmentId.equalsIgnoreCase(mT)) {

                      String mood = ds.child("question").getValue().toString();
                        List.add(mood);
                      /* String mod1=ds.child("rightAnswer").getValue().toString();
                       List2.add(mod1);
                       String mod2=ds.getKey();
                       List3.add(mod2);*/
                        showQ.setAdapter(Adapter);
                    }
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
