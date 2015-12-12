package com.instance.working.lanrenjizhang.view.DateSelect;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.instance.working.lanrenjizhang.R;
import com.instance.working.lanrenjizhang.view.DateSelectFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2015/12/6 0006.
 */
public class DatePackFragment extends DialogFragment {
    private static String BUNDLE_DATE = "com.instance.working.lanrenjizhang.view.DateSelect.DatePackFragment.BUNDLE_DATE";
    private  Date mDate;

    public static DatePackFragment newInstance(Date _date)
    {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DATE,_date);
        DatePackFragment dsf = new DatePackFragment();
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
        final int min = calendar.get(Calendar.MINUTE);

        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);

        DatePicker datepicker = (DatePicker)v.findViewById(R.id.datePicker);
        datepicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int _year, int _monthOfYear, int _dayOfMonth) {
                Calendar _calendar = Calendar.getInstance();
                _calendar.set(_year, _monthOfYear, _dayOfMonth, hour, min);
                mDate = _calendar.getTime();
                getArguments().putSerializable(BUNDLE_DATE, mDate);
            }
        });

        Log.v("Test1", "End");
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.fragment_dateselect_setdate)
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
