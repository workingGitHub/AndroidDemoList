package com.example.working.criminallintent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2015/10/18 0018.
 */
public class CrimFrament extends Fragment {
    private Crim _crim;
    public static final String EXTRA_CRIME_ID = "com.example.working.criminallintent.CrimFrament.CrimeId";
    public static final String EXTRA_DATE = "date";
    public static final int REQUEST_CODE = 0;
    private Button _button;
    private ImageButton _btn_camera;
    public static CrimFrament newInstance(UUID id)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, id);
        CrimFrament c = new CrimFrament();
        c.setArguments(args);
        return c;
    }
    private void UpdateDate()
    {
        if(null != _button)
        {
            DateFormat _dateformat = new DateFormat();
            _button.setText(_dateformat.format("EE MMM dd hh:mm:ss  yyyy", _crim.get_date()).toString());
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime);
        //_crim = new Crim();
        UUID id = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        setHasOptionsMenu(true);
        if(id != null)
        {
            _crim = CrimLab.get(getActivity()).get_crim(id);
        }
        else
        {
            _crim = new Crim();
        }
    }
    public void returnResult()
    {
        getActivity().setResult(Activity.RESULT_OK,null);
    }
    @Override
    public void setReturnTransition(Transition transition) {
        super.setReturnTransition(transition);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Date d;
        Calendar Oldcalendar;
        Calendar Newcalendar;
        Oldcalendar = Calendar.getInstance();
        Oldcalendar.setTime(_crim.get_date());
        int year = Oldcalendar.get(Calendar.YEAR);
        int month = Oldcalendar.get(Calendar.MONTH);
        int day = Oldcalendar.get(Calendar.DAY_OF_MONTH);
        int hour = Oldcalendar.get(Calendar.HOUR_OF_DAY);
        int min = Oldcalendar.get(Calendar.MINUTE);
        int sec = Oldcalendar.get(Calendar.SECOND);
        if(requestCode == REQUEST_CODE)
        {
            switch(resultCode)
            {
                case DatePackFragment.SUCCESS_FLAG_DATEPACK:
                    d = (Date)data.getSerializableExtra(DatePackFragment.INTENT_EXTRA_DATE);
                    Newcalendar = Calendar.getInstance();
                    Newcalendar.setTime(d);
                    year = Newcalendar.get(Calendar.YEAR);
                    month = Newcalendar.get(Calendar.MONTH);
                    day = Newcalendar.get(Calendar.DAY_OF_MONTH);
                    Oldcalendar.set(year,month,day,hour,min,sec);
                    _crim.set_date(Oldcalendar.getTime());
                    UpdateDate();
                    break;
                case TimePackFragment.SUCCESS_FLAG_DATEPACK:
                    d = (Date)data.getSerializableExtra(TimePackFragment.INTENT_EXTRA_DATE);
                    Newcalendar = Calendar.getInstance();
                    Newcalendar.setTime(d);
                    hour = Newcalendar.get(Calendar.HOUR);
                    min = Newcalendar.get(Calendar.MINUTE);
                    Oldcalendar.set(year,month,day,hour,min,sec);
                    _crim.set_date(Oldcalendar.getTime());
                    UpdateDate();
                    break;
                default:
                    break;

            }
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                Log.v("Test1","home is clock!");
                if(NavUtils.getParentActivityIntent(getActivity()) != null)
                {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View _v = inflater.inflate(R.layout.crimeframent,container,false);
        EditText _edittext = (EditText)_v.findViewById(R.id.crime_title);
        _button = (Button)_v.findViewById(R.id.datebtn);

        //向上的导航条
        if(NavUtils.getParentActivityIntent(getActivity()) != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        CheckBox _checkbox = (CheckBox)_v.findViewById(R.id.issave);
        _checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                _crim.set_isSave(isChecked);
                return;
            }
        });
        UpdateDate();
        //_button.setEnabled(false);
        _edittext.setText(_crim.get_name());
        _checkbox.setChecked(_crim.is_isSave());
        _edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                _crim.set_name(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                SelectFragment selectpack = SelectFragment.newInstance(_crim.get_date());
                selectpack.setTargetFragment(CrimFrament.this, REQUEST_CODE);
                selectpack.show(fm, EXTRA_DATE);
            }
        });

        _btn_camera = (ImageButton) _v.findViewById(R.id.btn_startcamera);
        _btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),CrimPageActivity.class);
                startActivity(i);
                return;
            }
        });

        //判断设备是否支持摄像头
        PackageManager pm = getActivity().getPackageManager();
        boolean isSupportCamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) ||
                            pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) ||
                            Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD ||
                            Camera.getNumberOfCameras() > 0;

        if(!isSupportCamera)
        {
            _btn_camera.setEnabled(false);
        }
        return _v;
    }
}
