package com.csc.ems.ui;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class studentProfileFragment extends Fragment {
    View view;

    TextView mTxtViewProfilename , mTxtViewProfileEmail , mTxtViewProfileId;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public studentProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        itiateView();
        getProfileData();
        return view;

    }

    private void getProfileData() {
            final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String studentName = dataSnapshot.child("ems").child("users").child(UserId).child("name").getValue(String.class);
                    String studentEmail = dataSnapshot.child("ems").child("users").child(UserId).child("email").getValue(String.class);
                  //  String studentId = dataSnapshot.child("ems").child("students").child(UserId).child("college id").getValue(String.class);
                    mTxtViewProfilename.setText(studentName);
                    mTxtViewProfileEmail.setText(studentEmail);
                 //   mTxtViewProfileId.setText(studentId);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

    private void itiateView() {

        mTxtViewProfilename = view.findViewById(R.id.profile_name_id);
        mTxtViewProfileEmail = view.findViewById(R.id.profile_email_id);
        mTxtViewProfileId = view.findViewById(R.id.profile_id_id);
    }
}
