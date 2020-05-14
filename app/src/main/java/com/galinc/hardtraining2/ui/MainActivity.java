package com.galinc.hardtraining2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.galinc.hardtraining2.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {//implements NavigationView.OnNavigationItemSelectedListener{

    private NavController navController;
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //navController.getCurrentDestination().
        if (navController.popBackStack(R.id.newTrainingEditExFragment,true)){
            navController.navigate(R.id.nav_document);
        }
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigation);
//        navigationView.setNavigationItemSelectedListener(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer =  findViewById(R.id.drawer_layout);
        //drawerToggle = setupDrawerToggle();
//        mDrawer.addDrawerListener(setupDrawerToggle());
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_document, R.id.nav_setting).setDrawerLayout(mDrawer).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(mDrawer).build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public NavController getNavController() {
        return navController;
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        int id = menuItem.getItemId();
////
////        if (id == R.id.nav_document) {
////            navController.navigate(R.id.trainingListFragment);
////        } else if (id == R.id.nav_setting) {
////            navController.navigate(R.id.downloadFragment);
////        }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // Примечание: Убедитесь, что вы передаёте допустимую ссылку
        // на toolbar
        // ActionBarDrawToggle() не предусматривает в ней
        // необходимости и не будет отображать иконку гамбургера без
        // неё
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }
}
