package com.example.navigationdrawer;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        // Setup of toolbar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Load the home screen fragment initially
        loadFragment(new homeScreen());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.optQuadra) {
                    loadFragment(new QuardaFrag());
                } else if (id == R.id.optCubic) {
                    loadFragment(new CubicFrag());
                } else if (id == R.id.optlinear1) {
                    loadFragment(new linearFrag1());
                } else if (id == R.id.optlinear2) {
                    loadFragment(new linearFrag2());
                } else if (id == R.id.optlinear3) {
                    loadFragment(new linearFrag3());
                }

                // Close the navigation drawer after selecting an item
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // Set isFirstLaunch to true during the first launch
        if (isFirstLaunch()) {
            setFirstLaunch(false);
        }
    }

    // Check if the app is launched for the first time
    public boolean isFirstLaunch() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        return settings.getBoolean("firstLaunch", true);
    }


    // Set isFirstLaunch value
    private void setFirstLaunch(boolean value) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstLaunch", value);
        editor.apply();
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Replace the existing fragment with the new one
        ft.replace(R.id.container, fragment);

        // Add the transaction to the back stack
        ft.addToBackStack(null);

        // Commit the transaction
        ft.commit();

    }

}
