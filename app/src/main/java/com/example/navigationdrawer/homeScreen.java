package com.example.navigationdrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class homeScreen extends Fragment {

    public homeScreen() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_screen, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Display the welcome message only at the start of the app
        TextView welcomeTextView = view.findViewById(R.id.welcomeTextView);
        if (isFirstLaunch()) {
            welcomeTextView.setVisibility(View.VISIBLE);
        } else {
            welcomeTextView.setVisibility(View.GONE);
        }
    }

    private boolean isFirstLaunch() {
        // For simplicity, we'll use SharedPreferences
        SharedPreferences settings = requireActivity().getPreferences(Context.MODE_PRIVATE);
        return settings.getBoolean("firstLaunch", true);
    }
}
