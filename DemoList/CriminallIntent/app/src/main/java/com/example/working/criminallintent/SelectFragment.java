package com.example.working.criminallintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015/10/25 0025.
 */
public class SelectFragment extends DialogFragment {
    private static String BUNDLE_DATE = "com.example.working.criminallintent.SelectFragment.DateAndTime";
    private Date _date;
    public static SelectFragment newInstance(Date d)
    {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DATE, d);
        SelectFragment s = new SelectFragment();
        s.setArguments(args);
        return s;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        _date = (Date)getArguments().getSerializable(BUNDLE_DATE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.layout_select,null);
        Button time = (Button)v.findViewById(R.id.timeSelect);
        Button date = (Button)v.findViewById(R.id.dateSelect);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单击处理选择时间处理
                FragmentManager fm = getFragmentManager();
                TimePackFragment datepack = TimePackFragment.newInstance(_date);
                datepack.setTargetFragment(getTargetFragment(),getTargetRequestCode());
                datepack.show(fm, CrimFrament.EXTRA_DATE);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单击处理选择日期处理
                FragmentManager fm = getFragmentManager();
                DatePackFragment datepack = DatePackFragment.newInstance(_date);
                datepack.setTargetFragment(getTargetFragment(), getTargetRequestCode());
                datepack.show(fm, CrimFrament.EXTRA_DATE);

            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dateofcrime)
                .setPositiveButton(android.R.string.ok,null)
                .create();
        //return super.onCreateDialog(savedInstanceState);
    }
}
