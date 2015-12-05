package com.instance.working.lanrenjizhang.date;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/11/26 0026.
 * ���ڱ���������Ϣ
 */
public class AccountLib {
    private static String FileName = "AccountLib";

    // ����ģʽ
    private static AccountLib sInstance;
    public static AccountLib get(Context c){
        if(sInstance == null)
        {
            sInstance = new AccountLib(c);
        }
        return sInstance;
    }

    private Context mContext;
    private ArrayList<Account> mAccountList;
    private AccountLib(Context c)
    {
        mContext = c;
        mAccountList = CreateAccountList();
    }
    private ArrayList<Account> CreateAccountList()
    {
        float cost = 0;
        ArrayList<Account> _accountList = new ArrayList<Account>();
        for (int i = 0 ; i < 40 ; i++)
        {
            Random random = new Random();
            Account accout = new Account();
            cost = random.nextFloat();
            accout.setmCost(cost);
            accout.setmRemark("���Ѳ���");
            _accountList.add(accout);
        }
        return _accountList;

    }

    public ArrayList<Account> getmAccountList() {
        return mAccountList;
    }
}
