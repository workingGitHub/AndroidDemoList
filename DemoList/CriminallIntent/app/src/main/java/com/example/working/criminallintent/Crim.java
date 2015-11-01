package com.example.working.criminallintent;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
import java.util.Date;
/**
 * Created by Administrator on 2015/10/18 0018.
 */
public class Crim {
    private static String STRING_ID = "ID";
    private static String STRING_NAME = "NAME";
    private static String STRING_DATE = "DATE";
    private static String STRING_ISSAVE = "IDSAVE";


    private UUID _id;
    private String _name;
    private Date _date;
    private boolean _isSave;
    Crim()
    {
        _id = UUID.randomUUID();
        _date = new Date();
    }
    Crim(JSONObject json) throws  JSONException
    {
        _id = UUID.fromString(json.getString(STRING_ID));
        if(json.has(STRING_NAME))
        {
            _name = json.getString(STRING_NAME);
        }
        _isSave = json.getBoolean(STRING_ISSAVE);
        _date = new Date(json.getLong(STRING_DATE));
    }
    public JSONObject toJSON() throws JSONException
    {
        JSONObject jObject = new JSONObject();
        jObject.put(STRING_ID,_id.toString());
        jObject.put(STRING_NAME,_name);
        jObject.put(STRING_DATE,_date.getTime());
        jObject.put(STRING_ISSAVE,_isSave);
        return jObject;
    }

    public Date get_date() {
        return _date;
    }

    public boolean is_isSave() {
        return _isSave;
    }

    public void set_date(Date _date) {
        this._date = _date;
    }

    public void set_isSave(boolean _isSave) {
        this._isSave = _isSave;
    }


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public UUID get_id() {
        return _id;
    }

    @Override
    public String toString() {
        return _name;
    }
}
