package com.instance.working.lanrenjizhang.view.DateSelect;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.instance.working.lanrenjizhang.R;
import com.instance.working.lanrenjizhang.view.DateSelectFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/6 0006.
 */
public class TimePackFragment extends DialogFragment {
    private static String BUNDLE_DATE = "com.instance.working.lanrenjizhang.view.DateSelect.TimePackFragment.BUNDLE_DATE";
    private  Date mDate;
    public static TimePackFragment newInstance(Date _date)
    {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DATE,_date);
        TimePackFragment dsf = new TimePackFragment();
        dsf.setArguments(args);
        return dsf;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v("Test1", "Begin");
        mDate = (Date)getArguments().getSerializable(BUNDLE_DATE);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time,null);

        TimePicker t = (TimePicker)v.findViewById(R.id.timePicker);
        t.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day,hourOfDay,minute);
                mDate = calendar.getTime();
                getArguments().putSerializable(BUNDLE_DATE,mDate);
            }
        });
        Log.v("Test1", "End");
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.fragment_dateselect_settime)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(DateSelectFragment.SUCCESS_FLAG_DATEPACK);
                    }
                })
                .create();

    }
    private void sendResult(int resultCode)
    {
        if(getTargetFragment() == null) {
            return;
        }
        Intent i = new Intent();
        i.putExtra(DateSelectFragment.OUTPUT_EXTRA_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
