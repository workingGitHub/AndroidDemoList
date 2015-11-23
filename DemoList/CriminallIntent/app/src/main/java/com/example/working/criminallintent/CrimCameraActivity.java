package com.example.working.criminallintent;

import android.app.Fragment;

/**
 * Created by Administrator on 2015/11/4 0004.
 */
public class CrimCameraActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimCameraFragment();
    }
}
