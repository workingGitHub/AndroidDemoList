package com.instance.working.lanrenjizhang.view;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.instance.working.lanrenjizhang.R;
import com.instance.working.lanrenjizhang.date.Account;
import com.instance.working.lanrenjizhang.date.AccountLib;

import android.text.format.DateFormat;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/5 0005.
 */
public class AccoutListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("Debug", "AccoutListFragment.onCreate");
        super.onCreate(savedInstanceState);

        //启动菜单栏
        setHasOptionsMenu(true);
        AccoutAdapt adpter = new AccoutAdapt(AccountLib.get(getActivity()).getmAccountList());
        setListAdapter(adpter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // 某一个项被按下时触发
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_accout_list,menu);
    }

    // 内部类   账户列表数据类
    private class AccoutAdapt extends ArrayAdapter<Account>
    {
        public AccoutAdapt(ArrayList<Account> account)
        {
            super(getActivity(),0,account);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(null == convertView)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_item_accountlist,null);
            }
            TextView _title = (TextView)convertView.findViewById(R.id.accout_title);
            TextView _date = (TextView)convertView.findViewById(R.id.accout_date);
            TextView _cost = (TextView)convertView.findViewById(R.id.accout_cost);
            Account account = getItem(position);

            _title.setText(account.getmRemark());
            DateFormat _dateformat = new DateFormat();
            _date.setText(_dateformat.format("EE MMM dd hh:mm:ss  yyyy",account.getmDate()).toString());
            _cost.setText(String.format("%.2f", account.getmCost())




            );
            return convertView;
        }
    }
}
