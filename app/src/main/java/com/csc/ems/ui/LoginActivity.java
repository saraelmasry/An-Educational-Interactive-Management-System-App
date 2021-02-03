package com.csc.ems.ui;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.csc.ems.Constants;
import com.csc.ems.pojo.UsersModel;
import com.csc.ems.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mUserName, mUserPassword;
    Button mLoginBtn;
    TextView mForgetPasswordTxt;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
    }
    private void intView() {
        mUserName = findViewById(R.id.user_email);
        mUserPassword = findViewById(R.id.user_password);
        mForgetPasswordTxt = findViewById(R.id.forget_password);
        mForgetPasswordTxt.setOnClickListener(this);
        mLoginBtn = findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);
        mAuth  = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                if (validate()){
                    LoginValidate();
                }
                break;
        }
    }
    private void checkUsersDegree(){
        final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String degreeInDataBase = dataSnapshot.child("users").child(UserId).child("degree").getValue(String.class);
                String studentLevel = dataSnapshot.child("users").child(UserId).child("level").getValue(String.class);

                if (degreeInDataBase.equalsIgnoreCase("student")){
                    if (studentLevel.equalsIgnoreCase("level_four")) {

                        Intent studentActivity = new Intent(LoginActivity.this, StudentHomeActivity.class);
                        startActivity(studentActivity);
                    }
                    else {
                        Intent studentActivity = new Intent(LoginActivity.this, UnderGraduateLevelHomeActivity.class);
                        startActivity(studentActivity);

                    }
                }
                else if (degreeInDataBase.equalsIgnoreCase("doctor")){
                    Intent studentActivity = new Intent(LoginActivity.this , DoctorHomeActivity.class );
                    startActivity(studentActivity);
                }
                else if (degreeInDataBase.equalsIgnoreCase("assistant")){
                    Intent studentActivity = new Intent(LoginActivity.this , AssistantHomeActivity.class );
                    startActivity(studentActivity);
                }
                else if (degreeInDataBase.equalsIgnoreCase("admin")){
                    Intent studentActivity = new Intent(LoginActivity.this , AdminHomeActivity.class );
                    startActivity(studentActivity);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(LoginActivity.this, " faild ", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void LoginValidate() {
        String Email = mUserName.getText().toString();
        String Password = mUserPassword.getText().toString();
        if (validate()) {
            mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        checkUsersDegree();
                    } else {
                        Toast.makeText(LoginActivity.this, " user not found ", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private boolean validate () {
        boolean valid = true;
        String email = mUserName.getText().toString();
        String password = mUserPassword.getText().toString();
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mUserName.setError("enter a valid email address");
            valid = false;
        } else {
            mUserName.setError(null);
        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mUserPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mUserPassword.setError(null);
        }
        return valid;
    }
}
