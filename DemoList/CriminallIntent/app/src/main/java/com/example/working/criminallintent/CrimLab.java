package com.example.working.criminallintent;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class CrimLab {
    private static String FileName = "CrimLab";
    private CrimIntentJSONSerial _crimJsonSerial;

    private static CrimLab sInstance;
    private Context _context;
    private ArrayList<Crim> _crimList;

    public static CrimLab get(Context c){
        if(sInstance == null)
        {
            sInstance = new CrimLab(c);
        }
        return sInstance;
    }

    private CrimLab(Context context)
    {
        _context = context;

        _crimJsonSerial = new CrimIntentJSONSerial(context,FileName);
        try {
            _crimList = _crimJsonSerial.loadCrims();
        }catch (Exception e)
        {
            _crimList = new ArrayList<Crim>();
        }

    }
    public void AddCrim(Crim c)
    {
        _crimList.add(c);
    }
    public void DelCrim(Crim c)
    {
        _crimList.remove(c);
    }
    public ArrayList<Crim> get_crimList() {
        return _crimList;
    }
    public Crim get_crim(UUID id) {
        for(Crim c:_crimList)
        {
            if(c.get_id().equals(id))
            {
                return c;
            }
        }
        return null;
    }
    public boolean onSave()
    {
        try {
            _crimJsonSerial.onSave(_crimList);
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }
}
