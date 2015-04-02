package com.dyvensvit.handbook;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.widget.TextView;

import com.dyvensvit.handbook.R;
import com.dyvensvit.handbook.entities.Department;
import com.dyvensvit.handbook.utils.JSONHelper;

import java.util.ArrayList;

public class DepartmentsActivity extends ActionBarActivity {

    ArrayList<Department> mDepartments;
    String mDepartmentTitles[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // <- insert this
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
        TextView title = ((TextView)findViewById(R.id.caption_text));
        title.setText("Регіони");
        Bundle bundle;

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            bundle = savedInstanceState;
        } else {
            bundle = getIntent().getExtras();
        }
        if(bundle!=null && bundle.containsKey("departments"))
        {
            mDepartments = bundle.getParcelableArrayList("departments");
        }
        else {
            mDepartments = JSONHelper.readDepartments(getResources());
        }
        ArrayList<String> stringArrayList = new ArrayList<String>();
        for(Department d : mDepartments)
        {
            stringArrayList.add(d.mTitle);
        }
        mDepartmentTitles = stringArrayList.toArray(new String[stringArrayList.size()]);

        // 2. create array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, // standard row layout provided by android
                mDepartmentTitles);

        ListView lv = (ListView) findViewById(R.id.listViewDepartments);

        // 3. Call setListAdapter
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(DepartmentsActivity.this, CommunitiesActivity.class);
                intent.putExtra("department",mDepartments.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putParcelableArrayList("departments", mDepartments);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_departments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
