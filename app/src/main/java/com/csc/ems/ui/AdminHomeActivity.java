package com.csc.ems.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csc.ems.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminHomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImageMenue , mprfilePicture ;
    TextView mTxtTitle , mTxtMenueHome , mTxtMenueProject , mAddsMenueProject, mTxtViewName;
    DrawerLayout mDrawer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        intiateView();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.ad_fragment_container,new adminHomeFragment()).commit();
        BottomNavigationView bottomNavication =findViewById(R.id.ad_bottom_navigation);
        bottomNavication.setOnNavigationItemSelectedListener(navicationListener);
        getProfileId();
    }
    private void intiateView() {
        mImageMenue = findViewById(R.id.ad_img_menue);
        mDrawer = findViewById(R.id.ad_drawer_id);
        mTxtTitle = findViewById(R.id.ad_mPageTitle);
        mTxtMenueHome = findViewById(R.id.ad_TxtMenueProfile);
        mTxtMenueProject = findViewById(R.id.ad_TxtMenueProject);
        mAddsMenueProject = findViewById(R.id.ad_adds_id);
        mTxtViewName = findViewById(R.id.ad_txt_view_name);
        mprfilePicture = findViewById(R.id.ad_logo);
        mAddsMenueProject.setOnClickListener(this);
        mTxtMenueHome.setOnClickListener(this);
        mTxtMenueProject.setOnClickListener(this);
        mImageMenue.setOnClickListener(this);
    }
    private void getProfileId() {
        final String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String studentName = dataSnapshot.child("ems").child("users").child(UserId).child("name").getValue(String.class);
                mTxtViewName.setText(studentName);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ad_img_menue:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.ad_TxtMenueProfile:
                projectFragment(new adminProfileFragment(),"My Profile");
                break;
            case R.id.ad_TxtMenueProject:
                projectFragment(new adminProjectFragment(),"Projects");
                break;
            case R.id.ad_adds_id:
                projectFragment(new adminAddsFragment() , "Adds");
                break;
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navicationListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                    Fragment selectFragment=null;
                    switch (Item.getItemId()){
                        case  R.id.ad_ic_btn_home:
                            selectFragment(new adminHomeFragment() , "Home");
                            break;
                    }
                    return true;
                }
            };
    void projectFragment(Fragment fragment , String pageTitle){
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.ad_fragment_container,fragment).commit();
        mDrawer.closeDrawers();
        mTxtTitle.setText(pageTitle);
    }
    public void selectFragment(Fragment fragmentottom , String pageTitleBottom){

        getSupportFragmentManager().beginTransaction().replace(R.id.ad_fragment_container,fragmentottom).commit();
        mTxtTitle.setText(pageTitleBottom);
    }
}
