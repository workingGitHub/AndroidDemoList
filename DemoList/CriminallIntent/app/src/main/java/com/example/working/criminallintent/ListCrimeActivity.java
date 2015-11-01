package com.example.working.criminallintent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class ListCrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ListCrimFrament();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
