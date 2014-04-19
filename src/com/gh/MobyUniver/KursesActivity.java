package com.gh.MobyUniver;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import static android.content.pm.ActivityInfo.*;

public class KursesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        if(savedInstanceState == null){
            FragmentTransaction mng = getFragmentManager().beginTransaction();
            KursesFragment kursesFragment = new KursesFragment();
            mng.add(R.id.kursesFrame, kursesFragment).commit();
        }
    }
}