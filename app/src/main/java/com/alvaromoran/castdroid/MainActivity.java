package com.alvaromoran.castdroid;

import android.os.Bundle;

import com.alvaromoran.castdroid.fragments.DiscoverFragment;
import com.alvaromoran.castdroid.fragments.HomeFragment;
import com.alvaromoran.castdroid.fragments.MyCastsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

/**
 * Main application class
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * Application creation
     *
     * @param savedInstanceState application saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets navigation item
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        // Loads default fragment on startup
        loadFragment(new HomeFragment());
    }

    /**
     * Loads the selected fragment on the bottom navigation view
     *
     * @param fragment fragment to be shown in the application
     * @return <code>true</code> if the navigation is properly executed. Otherwise, <code>false</code>
     */
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            // Replaces the fragment of the main application frame
            getSupportFragmentManager().beginTransaction().replace(R.id.application_frame, fragment).commit();
            return true;
        }
        return false;
    }

    /**
     * Method called when we click over a navigation item in the bottom navigation view
     *
     * @param menuItem selected menu item
     * @return <code>true</code> if the navigation is properly executed. Otherwise, <code>false</code>
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        // Possible items selected in the bottom navigation view
        switch (menuItem.getItemId()) {
            case R.id.navigate_to_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigate_to_discover:
                fragment = new DiscoverFragment();
                break;
            case R.id.navigate_to_my_casts:
                fragment = new MyCastsFragment();
                break;
        }
        // Load selected fragment
        return loadFragment(fragment);
    }
}
