package com.instance.working.lanrenjizhang.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.instance.working.lanrenjizhang.R;
import com.instance.working.lanrenjizhang.view.DateSelect.DatePackFragment;
import com.instance.working.lanrenjizhang.view.DateSelect.TimePackFragment;

import java.util.Date;

/**
 * Created by Administrator on 2015/12/6 0006.
 */
public class DateSelectFragment extends DialogFragment {
    private static String BUNDLE_DATE = "com.instance.working.lanrenjizhang.view.DateSelectFragment.BUNDLE_DATE";
    private Date mDate;
    public static final String EXTRA_DATE = "date";
    public static final int SUCCESS_FLAG_DATEPACK = 0;

    public static final String OUTPUT_EXTRA_DATE = "com.instance.working.lanrenjizhang.view.DateSelectFragment.OUTPUT_EXTRA_DATE";


    public static DateSelectFragment newInstance(Date _date)
    {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DATE,_date);
        DateSelectFragment dsf = new DateSelectFragment();
        dsf.setArguments(args);
        return dsf;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(BUNDLE_DATE);

        View v = (View)getActivity().getLayoutInflater().inflate(R.layout.layout_fragment_dateselect,null);
        Button bn_dateselect = (Button)v.findViewById(R.id.fragment_bn_dateselect_dateSelect);
        Button bn_timeselect = (Button)v.findViewById(R.id.fragment_bn_dateselect_timeSelect);

        bn_dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePackFragment dpf = DatePackFragment.newInstance(mDate);
                dpf.setTargetFragment(getTargetFragment(),getTargetRequestCode());
                dpf.show(fm,EXTRA_DATE);
            }
        });

        bn_timeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                TimePackFragment dpf = TimePackFragment.newInstance(mDate);
                dpf.setTargetFragment(getTargetFragment(),getTargetRequestCode());
                dpf.show(fm,EXTRA_DATE);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.fragment_dateselect_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
        //return super.onCreateDialog(savedInstanceState);
    }
}
