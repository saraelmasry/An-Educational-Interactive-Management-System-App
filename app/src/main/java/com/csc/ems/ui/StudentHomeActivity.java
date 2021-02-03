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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class StudentHomeActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mImageMenue , mprfilePicture ;
    TextView mTxtTitle , mTxtMenueHome , mTxtMenueProject , mCoursesMenueProject, mTxtViewName;
    DrawerLayout mDrawer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        intiateView();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,new studentHomeFragment()).commit();
        BottomNavigationView bottomNavication =findViewById(R.id.bottom_navigationn);
        bottomNavication.setOnNavigationItemSelectedListener(navicationListener);
        getProfileId();
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
    private void intiateView() {
        mImageMenue = findViewById(R.id.img_menue);
        mDrawer = findViewById(R.id.drawer_id);
        mTxtTitle = findViewById(R.id.mPageTitle);
        mTxtMenueHome = findViewById(R.id.TxtMenueProfile);
        mTxtMenueProject = findViewById(R.id.TxtMenueProject);
        mCoursesMenueProject = findViewById(R.id.Subjects_id);
        mTxtViewName = findViewById(R.id.txt_view_name);
        mprfilePicture = findViewById(R.id.logo);
        mCoursesMenueProject.setOnClickListener(this);
        mTxtMenueHome.setOnClickListener(this);
        mTxtMenueProject.setOnClickListener(this);
        mImageMenue.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_menue:
                mDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.TxtMenueProfile:
                projectFragment(new studentProfileFragment(),"My Profile");
                break;
            case R.id.TxtMenueProject:
                projectFragment(new studentProjectFragment(),"Projects");
                break;
            case R.id.Subjects_id:
                projectFragment(new studentSubjectFragment() , "Subjects");
                break;
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navicationListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
                    Fragment selectFragment=null;
                    switch (Item.getItemId()){
                        case  R.id.ic_btn_home:
                            selectFragment(new studentHomeFragment() , "Home");
                            break;
                    }
                    return true;
                }
            };
    void projectFragment(Fragment fragment , String pageTitle){
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.fragment_container,fragment).commit();
        mDrawer.closeDrawers();
        mTxtTitle.setText(pageTitle);
    }
    public void selectFragment(Fragment fragmentottom , String pageTitleBottom){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentottom).commit();
        mTxtTitle.setText(pageTitleBottom);
    }
}
