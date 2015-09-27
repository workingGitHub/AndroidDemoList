package com.example.working.demo1;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Administrator on 2015/9/25 0025.
 */
public class ListPerfenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.listactivity);
    }
}
