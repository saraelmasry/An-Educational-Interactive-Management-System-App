package com.csc.ems.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csc.ems.Constants;
import com.csc.ems.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentHomeFragment extends Fragment {
    View view;
    TextView mETHomePageAdds;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference(Constants.PROJECT_REFRANCE);
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public studentHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_student_home, container, false);
        intiateView();
        getLevelDegree();
        return view;
    }

    private void intiateView() {
        mETHomePageAdds = view.findViewById(R.id.home_page_et_adds);
    }
    private void getLevelDegree(){
        final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String levelDegree = dataSnapshot.child("users").child(UserId).child("level").getValue(String.class);
                String adds = dataSnapshot.child("adds").child(levelDegree).child("adds").getValue(String.class);
                mETHomePageAdds.setText(adds);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
