package com.example.working.hellomoon;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frameContainer);
        if(fragment == null)
        {
            fragment = new HelloMoonFragment();
            fm.beginTransaction()
                    .add(R.id.frameContainer,fragment)
                    .commit();
        }
    }

}
