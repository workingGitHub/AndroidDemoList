package com.instance.working.lanrenjizhang.activity;

import android.app.Fragment;

import com.instance.working.lanrenjizhang.view.AccountFragment;

/**
 * Created by Administrator on 2015/12/5 0005.
 */
public class AccountActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new AccountFragment();
    }
}
