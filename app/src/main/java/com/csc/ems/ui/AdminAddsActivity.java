package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mETAdds;
    Button mBtnSendAdds;
    private String mLevelDegree;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("ems");
    FirebaseUser userId = FirebaseAuth.getInstance().getCurrentUser();
    String getTxtAdds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_adds);
        intiateView();
        getCourceName();
    }
    private void getCourceName() {
        Bundle bundle = getIntent().getExtras();
        String levelDegree = bundle.getString("courcename");
        mLevelDegree = levelDegree;
    }
    private void intiateView() {
        mETAdds = findViewById(R.id.ad_et_adds);
        mBtnSendAdds = findViewById(R.id.ad_send_btn_adds_id);
        mBtnSendAdds.setOnClickListener(this);

    }
    private void sendAdds(){
        myRef.child("adds").child(mLevelDegree).child("level_degree").setValue(mLevelDegree);
        myRef.child("adds").child(mLevelDegree).child("doctor_id").setValue(userId);
        myRef.child("adds").child(mLevelDegree).child("adds").setValue(getTxtAdds);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ad_send_btn_adds_id:
                String Adds = mETAdds.getText().toString();
                getTxtAdds = Adds;
                sendAdds();
                break;
        }

    }
}
