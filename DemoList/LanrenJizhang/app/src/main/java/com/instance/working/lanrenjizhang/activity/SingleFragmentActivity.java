package com.instance.working.lanrenjizhang.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.instance.working.lanrenjizhang.R;

/**
 * Created by Administrator on 2015/12/5 0005.
 */
public abstract class SingleFragmentActivity extends ActionBarActivity {
    protected abstract Fragment creatFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseactivity);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frameContainer);
        if(fragment == null)
        {
            fragment = creatFragment();
            fm.beginTransaction()
                    .add(R.id.frameContainer,fragment)
                    .commit();
        }
    }
}
