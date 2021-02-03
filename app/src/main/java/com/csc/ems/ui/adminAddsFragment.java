package com.csc.ems.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.csc.ems.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class adminAddsFragment extends Fragment implements View.OnClickListener {
    View view;
    RelativeLayout mLevelOne , mLevelTwo , mLevelThree , mLevelFour;

    public adminAddsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_admin_adds, container, false);
        intiateView();

        return view;
    }

    private void intiateView() {
        mLevelOne = view.findViewById(R.id.level_one_adds);
        mLevelTwo = view.findViewById(R.id.level_two_adds);
        mLevelThree = view.findViewById(R.id.level_three_adds);
        mLevelFour = view.findViewById(R.id.level_four_adds);
        mLevelOne.setOnClickListener(this);
        mLevelTwo.setOnClickListener(this);
        mLevelThree.setOnClickListener(this);
        mLevelFour.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.level_one_adds:
                nextPage( new AdminAddsActivity() , "level_one");
                break;
            case R.id.level_two_adds:
                nextPage( new AdminAddsActivity() , "level_two");
                break;
            case R.id.level_three_adds:
                nextPage( new AdminAddsActivity() , "level_three");
                break;
            case R.id.level_four_adds:
                nextPage( new AdminAddsActivity() , "level_four");
                break;
        }

    }
    private void nextPage(Activity activity , String courceName){
        Intent intent = new Intent(getActivity() , activity.getClass());
        intent.putExtra("courcename" , courceName );
        startActivity(intent);
    }
}
