package com.instance.working.lanrenjizhang.activity;

import android.app.Fragment;
import android.view.Menu;

import com.instance.working.lanrenjizhang.view.AccoutListFragment;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2015/12/5 0005.
 */
public class AccountListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment creatFragment() {
        return new AccoutListFragment();
    }

}
