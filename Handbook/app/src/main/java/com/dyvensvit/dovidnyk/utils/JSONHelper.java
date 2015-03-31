package com.dyvensvit.dovidnyk.utils;

import android.content.res.Resources;

import com.dyvensvit.dovidnyk.R;
import com.dyvensvit.dovidnyk.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maksym on 3/29/2015.
 */
public class JSONHelper {


    public static ArrayList<Department> readDepartments(Resources r)
    {
        ArrayList<Department> result = null;
        try {
        //JSONObject obj = new JSONObject(loadStringFromAsset(r, R.raw.comissions));
        JSONArray m_jArry = new JSONArray(loadStringFromAsset(r, R.raw.comissions));
        result = new ArrayList<Department>();
        for (int i = 0; i < m_jArry.length(); i++)
        {
            JSONObject jo_inside = m_jArry.getJSONObject(i);

            Department d = new Department();
            d.mCode = jo_inside.getString("code");
            d.mTitle = jo_inside.getString("title");

            result.add(d);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Community> readCommunities(Resources r)
    {
        ArrayList<Community> result = null;
        try {
            //JSONObject obj = new JSONObject(loadStringFromAsset(r, R.raw.comissions));
            JSONArray m_jArry = new JSONArray(loadStringFromAsset(r, R.raw.community));
            result = new ArrayList<Community>();
            for (int i = 0; i < m_jArry.length(); i++)
            {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                Community c = new Community();
                c.mCode = jo_inside.getString("code");
                c.mDepCode = jo_inside.getString("dep_code");
                c.mTitle = jo_inside.getString("title");
                c.mAddress = jo_inside.getString("town");
                c.mFather = jo_inside.getString("father");
                c.mChief = jo_inside.getString("chief");
                c.mEmail = jo_inside.getString("email");
                c.mPhone = jo_inside.getString("phone");
                result.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static ArrayList<Community> readCommunitiesByDepCode(Resources r, String depCode)
    {
        ArrayList<Community> result = new ArrayList<Community>();

        ArrayList<Community> allCommunities = readCommunities(r);

        for(Community c : allCommunities)
        {
            if(c.mDepCode.startsWith(depCode))
            {
                result.add(c);
            }
        }

        return result;
    }

    private static String loadStringFromAsset(Resources r, int resourcesId)
    {

        InputStream is = r.openRawResource(resourcesId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
        e.printStackTrace();
        }
        }

        String result = writer.toString();

        return result;
    }
}
