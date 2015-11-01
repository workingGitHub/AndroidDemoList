package com.example.working.criminallintent;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;


import android.app.Fragment;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2015/10/23 0023.
 */
public class CrimPageActivity extends Activity {
    private ViewPager _viewpager;
    private ArrayList<Crim> _crims;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _viewpager = new ViewPager(this);
        _viewpager.setId(R.id.viewPager);
        setContentView(_viewpager);

        _crims = CrimLab.get(this).get_crimList();

        FragmentManager fm = getFragmentManager();
        _viewpager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crim crim = _crims.get(position);
                return CrimFrament.newInstance(crim.get_id());
            }

            @Override
            public int getCount() {
                return _crims.size();
            }
        });

        UUID id = (UUID)getIntent().getSerializableExtra(CrimFrament.EXTRA_CRIME_ID);
        for(int i = 0;i < _crims.size();i++)
        {
            Crim c = _crims.get(i);
            if(c.get_id().equals(id))
            {
                _viewpager.setCurrentItem(i);
                break;
            }
        }
    }
}
