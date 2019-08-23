package com.example.android.emailapp.navdrawer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android.emailapp.R;
import com.example.android.emailapp.baseui.BaseFragment;
import com.example.android.emailapp.databinding.FragmentOutlookDrawerBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

public class OutlookDrawerFragment extends BaseFragment
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentOutlookDrawerBinding mBinding;

    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            superOnBackPressed();
        }
    }

    private void superOnBackPressed() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.outlook_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_outlook_drawer;
    }

    @Override
    public void onViewStubInflated(View inflatedView, Bundle savedInstanceState) {
        mBinding = FragmentOutlookDrawerBinding.bind(inflatedView);
    }

    @Override
    public void initControllers() {

    }

    @Override
    public void handleViews() {
        if (getActivity() != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.includeAppBarOutlookDrawer.toolbar);
        }

        mBinding.includeAppBarOutlookDrawer.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), mBinding.drawerLayout, mBinding.includeAppBarOutlookDrawer.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void restoreValues(Bundle savedInstanceState) {

    }
}
