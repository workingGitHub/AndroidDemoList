package com.example.working.criminallintent;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2015/10/18 0018.
 */
public class CrimeActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID id = (UUID)getIntent().getSerializableExtra(CrimFrament.EXTRA_CRIME_ID);
        return CrimFrament.newInstance(id);
    }
}
