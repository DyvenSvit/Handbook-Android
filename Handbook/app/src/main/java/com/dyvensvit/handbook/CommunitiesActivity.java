package com.dyvensvit.handbook;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.dyvensvit.handbook.R;
import com.dyvensvit.handbook.entities.Community;
import com.dyvensvit.handbook.entities.Department;
import com.dyvensvit.handbook.utils.JSONHelper;

import java.util.ArrayList;

public class CommunitiesActivity extends ActionBarActivity {
    Department mDepartment;
    ArrayList<Community> mCommunities;
    String mCommunityTitles[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // <- insert this
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_communities);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);

        /*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        }
*/
        Bundle bundle;

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            bundle = savedInstanceState;
        } else {
            bundle = getIntent().getExtras();
        }

        mDepartment = bundle.getParcelable("department");
        TextView title = ((TextView)findViewById(R.id.caption_text));
        title.setText(mDepartment.mTitle);
        if(bundle.containsKey("communities"))
        {
            mCommunities = bundle.getParcelableArrayList("communities");
        }
        else {
            mCommunities = JSONHelper.readCommunitiesByDepCode(getResources(), mDepartment.mCode);
        }

        ArrayList<String> stringArrayList = new ArrayList<String>();
        for(Community c : mCommunities)
        {
            stringArrayList.add(c.mTitle);
        }
        mCommunityTitles = stringArrayList.toArray(new String[stringArrayList.size()]);

        // 2. create array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, // standard row layout provided by android
                mCommunityTitles);

        ListView lv = (ListView) findViewById(R.id.listViewCommunities);

        // 3. Call setListAdapter
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(CommunitiesActivity.this, DepartmentPagerActivity.class);
                intent.putExtra("communities", mCommunities);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putParcelable("department", mDepartment);

        savedInstanceState.putParcelableArrayList("communities", mCommunities);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_communities, menu);
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
