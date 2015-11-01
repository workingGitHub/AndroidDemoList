package com.example.working.criminallintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/30 0030.
 */
public class CrimIntentJSONSerial {
    private Context _context;
    private String _fileName;
    CrimIntentJSONSerial(Context c,String name)
    {
        _context = c;
        _fileName = name;
    }
    public void onSave(ArrayList<Crim> ListCrim) throws IOException,JSONException
    {
        JSONArray jArray = new JSONArray();
        for(Crim c:ListCrim)
        {
           // JSONObject jObject = c.toJSON();
            jArray.put(c.toJSON());
        }

        //讲内容写到硬盘上
        Writer writer = null;
        try{
            OutputStream out = _context.openFileOutput(_fileName,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jArray.toString());
        }finally {
            if(writer != null)
            {
                writer.close();
            }
        }
    }

    public ArrayList<Crim> loadCrims() throws IOException,JSONException
    {
        ArrayList<Crim> crims = new ArrayList<Crim>();
        BufferedReader reader = null;
        try{
            //打开要读取的文件
            InputStream in = _context.openFileInput(_fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line=reader.readLine())!= null)
            {
                jsonString.append(line);
            }
            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0 ;i < jArray.length(); i++)
            {
                crims.add(new Crim(jArray.getJSONObject(i)));
            }
        }catch (Exception e)
        {

        }finally {
            if(reader != null)
            {
                reader.close();
            }
        }

        return crims;
    }
}
