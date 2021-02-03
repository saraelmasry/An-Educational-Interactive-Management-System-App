package com.csc.ems.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.csc.ems.R;

public class DoctorInsertOrFinishActivity extends AppCompatActivity implements View.OnClickListener {
    TextView a, b;
    String xz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_insert_or_finish);
        a = findViewById(R.id.InsertAnotherQuestion);
        b = findViewById(R.id.Finish);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        xz = getIntent().getStringExtra("id4");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.InsertAnotherQuestion:
                Intent aa = new Intent(DoctorInsertOrFinishActivity.this, DoctorNextTypeOfQuestionActivity.class);
                aa.putExtra("id5", xz);
                startActivity(aa);
                break;
            case R.id.Finish:
                Intent aq = new Intent(DoctorInsertOrFinishActivity.this, DoctorCollectQuestionOfAssignmentActivity.class);
                aq.putExtra("id5", xz);
                startActivity(aq);
                break;
        }
    }
}
