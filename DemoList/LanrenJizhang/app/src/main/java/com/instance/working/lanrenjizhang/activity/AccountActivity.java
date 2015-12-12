package com.instance.working.lanrenjizhang.activity;

import android.app.Fragment;

import com.instance.working.lanrenjizhang.view.AccountFragment;

import java.util.UUID;

/**
 * Created by Administrator on 2015/12/5 0005.
 */
public class AccountActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        UUID id = (UUID)getIntent().getSerializableExtra(AccountFragment.EXTRA_ACCOUNT_ID);
        return AccountFragment.newInstance(id);
    }
}
