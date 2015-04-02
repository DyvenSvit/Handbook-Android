package com.dyvensvit.handbook;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dyvensvit.handbook.R;
import com.dyvensvit.handbook.entities.Community;


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

        if(mCommunity.mAddress.trim().length() == 0)
        {
            ((TextView) rootView.findViewById(R.id.tvAddress)).setVisibility(View.GONE);
        }

        ((TextView) rootView.findViewById(R.id.tvFatherValue)).setText(mCommunity.mFather);


        if(mCommunity.mFather.trim().length() == 0)
        {
            ((TextView) rootView.findViewById(R.id.tvFatherTitle)).setVisibility(View.GONE);
            ((TextView) rootView.findViewById(R.id.tvFatherValue)).setVisibility(View.GONE);
        }

        ((TextView) rootView.findViewById(R.id.tvChiefValue)).setText(mCommunity.mChief);

        if(mCommunity.mFather.trim().length() == 0)
        {
            ((TextView) rootView.findViewById(R.id.tvFatherTitle)).setVisibility(View.GONE);
            ((TextView) rootView.findViewById(R.id.tvFatherValue)).setVisibility(View.GONE);
        }

        ((Button) rootView.findViewById(R.id.btnEmailValue)).setText(mCommunity.mEmail);

        if(mCommunity.mEmail.trim().length() == 0)
        {
            ((TextView) rootView.findViewById(R.id.tvEmailTitle)).setVisibility(View.GONE);
            ((Button) rootView.findViewById(R.id.btnEmailValue)).setVisibility(View.GONE);
        }

        ((Button) rootView.findViewById(R.id.btnEmailValue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emails[] = mCommunity.mEmail.split(",");
                if(emails.length>0) {
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emails[0].trim()});
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
            }
        });

        ((Button) rootView.findViewById(R.id.btnPhoneValue)).setText(mCommunity.mPhone);

        ((Button) rootView.findViewById(R.id.btnPhoneValue)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phones[] = mCommunity.mPhone.split(",");
                if(phones.length>0) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + phones[0].trim().replace("-", "").replace("(","").replace(")","")));
                    startActivity(callIntent);
                }
            }
        });

        if(mCommunity.mPhone.trim().length() == 0)
        {
            ((TextView) rootView.findViewById(R.id.tvPhoneTitle)).setVisibility(View.GONE);
            ((Button) rootView.findViewById(R.id.btnPhoneValue)).setVisibility(View.GONE);
        }

        return rootView;
    }


}
