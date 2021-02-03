package com.csc.ems.pojo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Model {
    final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();//userID
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef =database.getReference("ems");
    String student_id;
    String student_name;


    public Model(){

    }

    public Model(String student_id, String student_name) {
        this.student_id = student_id;
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
