package com.example.working.criminallintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015/10/25 0025.
 */
public class TimePackFragment extends DialogFragment {
    private static String EXTRA_DATE = "com.example.working.criminallintent.TimePackFragment.InputDate";
    public static TimePackFragment newInstance(Date d)
    {
        Bundle b = new Bundle();
        b.putSerializable(EXTRA_DATE, d);

        TimePackFragment t = new TimePackFragment();
        t.setArguments(b);
        return t;
    }

    public static String INTENT_EXTRA_DATE = "com.example.working.criminallintent.TimePackFragment.INTENT_EXTRA_DATE";
    public final static int FLAG_DATE = 0x80;
    public final static int SUCCESS_FLAG_DATEPACK = 0|FLAG_DATE;
    Date _date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v("Test1","Begin");
        _date = (Date)getArguments().getSerializable(EXTRA_DATE);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(_date);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_time,null);

        TimePicker t = (TimePicker)v.findViewById(R.id.timePicker);
        t.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(0,0,0,hourOfDay,minute);
                _date = calendar.getTime();
                getArguments().putSerializable(EXTRA_DATE,_date);
            }
        });
        Log.v("Test1", "End");
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.timeofcrime)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(SUCCESS_FLAG_DATEPACK);
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
        i.putExtra(INTENT_EXTRA_DATE, _date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
    }
}
