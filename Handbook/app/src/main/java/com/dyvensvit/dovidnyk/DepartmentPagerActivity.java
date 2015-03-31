package com.dyvensvit.dovidnyk;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.dyvensvit.dovidnyk.entities.Community;

import java.util.ArrayList;

public class DepartmentPagerActivity extends ActionBarActivity {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DepartmentCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    ArrayList<Community> mCommunities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_pager);
        setTitle("Спільноти");
        Bundle bundle;
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            bundle = savedInstanceState;
        } else {
            bundle = getIntent().getExtras();
        }
        mCommunities = bundle.getParcelableArrayList("communities");
        int currentPosition = bundle.getInt("pos", 0);
        Community[] communitiesArray = new Community[mCommunities.size()];
        mCommunities.toArray(communitiesArray);
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new DepartmentCollectionPagerAdapter(
                        getSupportFragmentManager(), communitiesArray);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        mViewPager.setCurrentItem(currentPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("pos", mViewPager.getCurrentItem());
        savedInstanceState.putParcelableArrayList("communities", mCommunities);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_department_pager, menu);
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
