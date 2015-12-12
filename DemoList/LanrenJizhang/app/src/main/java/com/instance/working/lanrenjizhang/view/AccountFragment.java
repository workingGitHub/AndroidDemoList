package com.instance.working.lanrenjizhang.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.instance.working.lanrenjizhang.R;
import com.instance.working.lanrenjizhang.date.Account;
import com.instance.working.lanrenjizhang.date.AccountLib;
import com.instance.working.lanrenjizhang.view.DateSelect.DatePackFragment;

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
                try{
                    f = Float.parseFloat(s.toString());
                }catch (NumberFormatException  e)
                {
                    Toast.makeText(getActivity(),"输入金额有误，请检查。",Toast.LENGTH_SHORT).show();
                    return ;
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
        return v;
    }
}
