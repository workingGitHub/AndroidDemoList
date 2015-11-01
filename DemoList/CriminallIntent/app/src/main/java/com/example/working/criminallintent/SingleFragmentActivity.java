package com.example.working.criminallintent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public abstract class SingleFragmentActivity extends Activity {
    protected abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime);

        FragmentManager framentmanager = getFragmentManager();
        Fragment fragment = framentmanager.findFragmentById(R.id.frameContainer);
        if(fragment == null)
        {
            fragment = createFragment();
            framentmanager.beginTransaction()
                    .add(R.id.frameContainer,fragment)
                    .commit();
        }
    }
}
