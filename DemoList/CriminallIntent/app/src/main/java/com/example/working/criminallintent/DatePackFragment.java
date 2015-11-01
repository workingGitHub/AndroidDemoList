package com.example.working.criminallintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015/10/24 0024.
 */
public class DatePackFragment extends DialogFragment {
    public final static String EXTRA_DATE = "com.example.working.criminallintent.DatePackFragment.date";
    public final static String INTENT_EXTRA_DATE = "com.example.working.criminallintent.DatePackFragment.intentdate";
    public final static int FLAG_DATE = 0;

    public final static int SUCCESS_FLAG_DATEPACK = 0|FLAG_DATE;

    public static DatePackFragment newInstance(Date d)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, d);

        DatePackFragment _datepack = new DatePackFragment();
        _datepack.setArguments(args);
        return _datepack;
    }
    private Date _date;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        _date = (Date)getArguments().getSerializable(EXTRA_DATE);
        // 从Date中获取年月日
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(_date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        DatePicker datepicker = (DatePicker)v.findViewById(R.id.datePicker);
        datepicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                _date = new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                getArguments().putSerializable(EXTRA_DATE,_date);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dateofcrime)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(SUCCESS_FLAG_DATEPACK);
                    }
                })
                .create();
        //return super.onCreateDialog(savedInstanceState);
    }

    private void sendResult(int resultCode)
    {
        if(getTargetFragment() == null) {
            return;
        }
        Intent i = new Intent();
        i.putExtra(INTENT_EXTRA_DATE, _date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
