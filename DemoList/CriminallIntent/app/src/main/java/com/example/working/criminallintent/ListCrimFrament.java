package com.example.working.criminallintent;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    private ArrayList<Crim> mCrims;
    private static int CRIMRET = 1;
    private boolean isShowsubTitle = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("DebugTimeLoop","ListCrimFrament.onCreate");
        super.onCreate(savedInstanceState);
        //启用菜单栏
        setHasOptionsMenu(true);
        //保存临时信息
        setRetainInstance(true);
        getActivity().setTitle(R.string.crimes);
        mCrims = CrimLab.get(getActivity()).get_crimList();
        CrimAdapter adpter = new CrimAdapter(mCrims);
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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_fragmentlist:
                Crim c = new Crim();
                Intent i = new Intent(getActivity(),CrimPageActivity.class);
                mCrims.add(c);
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

            title.setText(crim.get_name().toString());
            DateFormat _dateformat = new DateFormat();
            date.setText(_dateformat.format("EE MMM dd hh:mm:ss  yyyy",crim.get_date()).toString());
            isSave.setChecked(crim.is_isSave());
            return convertView;
        }
    }
}
