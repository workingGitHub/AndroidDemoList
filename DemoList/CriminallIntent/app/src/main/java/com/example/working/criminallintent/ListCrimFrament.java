package com.example.working.criminallintent;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class ListCrimFrament extends ListFragment {
    //private ArrayList<Crim> mCrims;
    public static boolean ISMUILTCHIOS = true;
    private static int CRIMRET = 1;
    private boolean isShowsubTitle = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("DebugTimeLoop", "ListCrimFrament.onCreate");
        super.onCreate(savedInstanceState);
        //启用菜单栏
        setHasOptionsMenu(true);
        //保存临时信息
        setRetainInstance(true);
        getActivity().setTitle(R.string.crimes);
        //mCrims = CrimLab.get(getActivity()).get_crimList();
        CrimAdapter adpter = new CrimAdapter(CrimLab.get(getActivity()).get_crimList());
        setListAdapter(adpter);
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimLab.get(getActivity()).onSave();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("DebugTimeLoop","ListCrimFrament.onCreateView");
        if(isShowsubTitle)
        {
            getActivity().getActionBar().setSubtitle(R.string.subtitle);
        }
        View v = super.onCreateView(inflater, container, savedInstanceState);
        ListView listview = (ListView)v.findViewById(android.R.id.list);
        listview.setBackgroundResource(R.drawable.backgroud_act);
        if(ISMUILTCHIOS)
        {
            //注册多选上下文菜单
            listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                    // 当选中或者撤销选中的时候会触发它
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    //实例化上下文菜单资源的地方
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.crim_list_item_context,menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    // 在onCreateActionMode之后调用
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    // 响应操作
                    switch(item.getItemId())
                    {
                        case R.id.menu_delete_item:
                            CrimAdapter adapter = (CrimAdapter)getListAdapter();
                            CrimLab crims = CrimLab.get(getActivity());
                            for(int i = adapter.getCount() - 1; i >= 0 ; i--)
                            {
                                if(getListView().isItemChecked(i))
                                {
                                    crims.DelCrim(adapter.getItem(i));
                                }
                            }
                            mode.finish();
                            adapter.notifyDataSetChanged();
                            return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    //ActionMode对象删除时调用
                }
            });
        }else {
            //注册浮动上下文控件
            registerForContextMenu(listview);
        }
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_fragmentlist:
                Crim c = new Crim();
                Intent i = new Intent(getActivity(),CrimPageActivity.class);
                CrimLab.get(getActivity()).get_crimList().add(c);
                i.putExtra(CrimFrament.EXTRA_CRIME_ID,c.get_id());
                startActivity(i);
                return true;
            case R.id.menu_subtitle:
                if(getActivity().getActionBar().getSubtitle() == null)
                {
                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
                    item.setTitle(R.string.hidesubtitle);
                    isShowsubTitle = true;
                }
                else
                {
                    getActivity().getActionBar().setSubtitle(null);
                    item.setTitle(R.string.showsubtitle);
                    isShowsubTitle = false;
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Crim c = (Crim)(getListAdapter().getItem(position));
        //Intent intent = new Intent(getActivity(),CrimeActivity.class);
        Intent intent = new Intent(getActivity(),CrimPageActivity.class);

        intent.putExtra(CrimFrament.EXTRA_CRIME_ID, c.get_id());
        //startActivity(intent);
        startActivityForResult(intent,CRIMRET);
        //Toast.makeText(getActivity(),c.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(CRIMRET == requestCode)
        {
            //TODO:
            Toast.makeText(getActivity(),String.format("Res:%d", requestCode),Toast.LENGTH_SHORT).show();
        }
        //super.onActivityResult(requestCode, resultCode, data);

    }


    private class CrimAdapter extends ArrayAdapter<Crim>
    {
        public CrimAdapter(ArrayList<Crim> crims)
        {
            super(getActivity(),0,crims);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.layout_item2,null);
            }
            TextView title = (TextView)convertView.findViewById(R.id.item_title);
            TextView date = (TextView)convertView.findViewById(R.id.item_date);
            CheckBox isSave = (CheckBox)convertView.findViewById(R.id.item_issave);
            Crim crim = getItem(position);

            title.setText(crim.get_name());
            DateFormat _dateformat = new DateFormat();
            date.setText(_dateformat.format("EE MMM dd hh:mm:ss  yyyy",crim.get_date()).toString());
            isSave.setChecked(crim.is_isSave());
            return convertView;
        }
    }

    //创建选项菜单
    /*第一步：创建选项菜单
    * 第二步：响应选项菜单 onOptionsItemSelected
    *
    * */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.v("DebugTimeLoop","ListCrimFrament.onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crim_list, menu);
        MenuItem item = menu.findItem(R.id.menu_subtitle);
        if((isShowsubTitle) && (item != null))
        {
            item.setTitle(R.string.hidesubtitle);
        }

    }

    //创建浮动上下文菜单
    /*第一步：onCreateContextMenu 创建上下文菜单
    * 第二步：注册响应上下文菜单的view控件
    * 第三步：响应处理 onContextItemSelected
    * 试试列表上下文的多选模式
    * 首先listview控件需要注册多选模式，setChoiceMode
    *
    *
    * */

     @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.crim_list_item_context,menu);
        //super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //因为ListView是AdpterView的子类，所以getmenuInfo返回的为AdpaterContextmenuInfo
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int postion = info.position;
        CrimAdapter cAdper = (CrimAdapter)getListAdapter();
        Crim c = cAdper.getItem(postion);
        switch (item.getItemId())
        {
            case R.id.menu_delete_item:
                CrimLab.get(getActivity()).DelCrim(c);
                cAdper.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }
}
