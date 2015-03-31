package com.dyvensvit.dovidnyk;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyvensvit.dovidnyk.entities.Community;


public class DepartmentObjectFragment extends Fragment {

    public static final String ARG_COMMUNITY = "community";

    private Community mCommunity;

    public static DepartmentObjectFragment newInstance(Community community) {
        DepartmentObjectFragment fragment = new DepartmentObjectFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_COMMUNITY, community);
        fragment.setArguments(args);
        return fragment;
    }

    public DepartmentObjectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCommunity = getArguments().getParcelable(ARG_COMMUNITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_department_object, container, false);

        ((TextView) rootView.findViewById(R.id.tvTitle)).setText(mCommunity.mTitle);
        ((TextView) rootView.findViewById(R.id.tvAddress)).setText(mCommunity.mAddress);
        ((TextView) rootView.findViewById(R.id.tvFatherValue)).setText(mCommunity.mFather);
        ((TextView) rootView.findViewById(R.id.tvChiefValue)).setText(mCommunity.mChief);
        ((TextView) rootView.findViewById(R.id.tvEmailValue)).setText(mCommunity.mEmail);
        ((TextView) rootView.findViewById(R.id.tvPhoneValue)).setText(mCommunity.mPhone);

        return rootView;
    }


}
