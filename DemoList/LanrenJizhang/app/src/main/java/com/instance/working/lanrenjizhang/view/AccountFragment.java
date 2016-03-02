package com.instance.working.lanrenjizhang.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.instance.working.lanrenjizhang.R;
import com.instance.working.lanrenjizhang.date.Account;
import com.instance.working.lanrenjizhang.date.AccountLib;
import com.instance.working.lanrenjizhang.view.DateSelect.DatePackFragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2015/12/5 0005.
 */
public class AccountFragment extends Fragment {
    public static final String EXTRA_ACCOUNT_ID = "com.instance.working.lanrenjizhang.view.AccountFragment.AccountID";
    private Account mAccount;
    private static final int DATE_REQUEST_CODE = 0;
    private Button mButtonDate;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    public final int MEDIA_TYPE_IMAGE = 1;
    public final int MEDIA_TYPE_VIDEO = 2;

    public static AccountFragment newInstance(UUID id)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ACCOUNT_ID, id);
        AccountFragment af = new AccountFragment();
        af.setArguments(args);
        return af;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)getArguments().getSerializable(EXTRA_ACCOUNT_ID);
        if(null == id)
        {
            mAccount = new Account();
        }
        else
        {
            mAccount = AccountLib.get(getActivity()).getAccount(id);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("workingTest",String.format("Image saved to [%d]:\n", resultCode) + data.getData());
        switch(requestCode)
        {
            case DATE_REQUEST_CODE:
                {
                    if(requestCode == DateSelectFragment.SUCCESS_FLAG_DATEPACK)
                    {
                        mAccount.setmDate((Date)data.getSerializableExtra(DateSelectFragment.OUTPUT_EXTRA_DATE));
                        if(null != mButtonDate)
                        {
                            mButtonDate.setText(mAccount.getmDateString("EE MMM dd hh:mm:ss  yyyy"));
                        }
                    }
                }
                break;
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                    Toast.makeText(getActivity(), String.format("Image saved to [%d]:\n", resultCode), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.layout_activity_accout,container,false);
        EditText _edittext_title = (EditText)v.findViewById(R.id.accountinfo_title);
        EditText _edittext_cost = (EditText)v.findViewById(R.id.accountinfo_cost);
        ImageButton _btn_startcamera = (ImageButton)v.findViewById(R.id.btn_startcamera);
        mButtonDate = (Button)v.findViewById(R.id.accountinfo_date);

        //显示刷新
        _edittext_title.setText(mAccount.getmRemark());
        _edittext_cost.setText(String.format("%.2f", mAccount.getmCost()));
        mButtonDate.setText(mAccount.getmDateString("EE MMM dd hh:mm:ss  yyyy"));

        //联动刷新
        //标题刷新
        _edittext_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAccount.setmRemark(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //金额刷新
        _edittext_cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float f = Account.INVALID_FLOAT;
                try {
                    f = Float.parseFloat(s.toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "输入金额有误，请检查。", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAccount.setmCost(f);
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // TODO: 时间刷新
        mButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DateSelectFragment dpf = DateSelectFragment.newInstance(mAccount.getmDate());
                dpf.setTargetFragment(AccountFragment.this,DATE_REQUEST_CODE);
                dpf.show(fm,DateSelectFragment.EXTRA_DATE);
            }
        });

        _btn_startcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /*
                File mediaStorageDir = getActivity().getExternalFilesDir(null);
                // This location works best if you want the created images to be shared
                // between applications and persist after your app has been uninstalled.

                // Create the storage directory if it does not exist
                if (! mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()){
                        Log.d("MyCameraApp", "failed to create directory");
                        return ;
                    }
                }

                // Create a media file name
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File mediaFile;

                mediaFile = new File(mediaStorageDir.getPath() +
                        "IMG_"+ timeStamp + ".jpg");
                */
                // 获取 SD 卡根目录
                String saveDir = Environment.getExternalStorageDirectory() + "/meitian_photos";
                // 新建目录
                File dir = new File(saveDir);
                if (! dir.exists()) dir.mkdir();
                // 生成文件名
                SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
                String filename = "MT" + (t.format(new Date())) + ".jpg";
                // 新建文件
                File mediaFile = new File(saveDir, filename);
                _intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mediaFile)); // set the image file name
                // start the image capture Intent
                Toast.makeText(getActivity(),"fileUri:"+Uri.fromFile(mediaFile),Toast.LENGTH_LONG).show();
                startActivityForResult(_intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

            }
        });
        return v;
    }



}
