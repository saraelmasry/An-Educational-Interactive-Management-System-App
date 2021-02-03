package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorAddsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mETAdds;
    Button mBtnSendAdds;
    private String CourcesNamee;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
    String getTxtAdds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_adds);
        intiateView();
        getCourceName();

    }

    private void getCourceName() {
        Bundle bundle = getIntent().getExtras();
        String courcesname = bundle.getString("courcename");
        CourcesNamee = courcesname;
    }

    private void intiateView() {
        mETAdds = findViewById(R.id.et_adds);
        mBtnSendAdds = findViewById(R.id.send_btn_adds_id);
        mBtnSendAdds.setOnClickListener(this);

    }
    private void sendAdds(){
        String courceId = myRef.child("cources").child(CourcesNamee).child("cource_id").push().getKey();
        myRef.child("cources").child(CourcesNamee).child("cource_id").setValue(courceId);
        myRef.child("cources").child(CourcesNamee).child("cource_name").setValue(CourcesNamee);
        myRef.child("cources").child(CourcesNamee).child("doctor_id").setValue(userId);
        myRef.child("cources").child(CourcesNamee).child("cources_adds").setValue(getTxtAdds);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn_adds_id:
                String Adds = mETAdds.getText().toString();
                getTxtAdds = Adds;
                sendAdds();
            break;
        }
    }
}
