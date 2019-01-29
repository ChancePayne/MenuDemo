package com.lambdaschool.menudemo;

import android.app.UiModeManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar      toolbar;
    DrawerLayout drawerLayout;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        drawerLayout = findViewById(R.id.drawer_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(context, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                menuItem.setChecked(!menuItem.isChecked());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (menuItem.getItemId() == R.id.nav_first_in_submenu) {
                        final UiModeManager uiModeManager = context.getSystemService(UiModeManager.class);
                        uiModeManager.setNightMode(menuItem.isChecked() ? UiModeManager.MODE_NIGHT_YES : UiModeManager.MODE_NIGHT_NO);
                    }
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     *
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_options_menu, menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String messageText = item.getTitle().toString();
        /*if(item.getGroupId() != 0) {
            R.id.my_group;
        }*/
        switch (item.getItemId()) {
            case R.id.first_item:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
            case R.id.second_item:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
            case R.id.first_in_group:
                if (item.isChecked()) {
                    item.setChecked(false);
                    messageText += " unchecked";
                } else {
                    item.setChecked(true);
                    messageText += " checked";
                }

                Toast.makeText(this, messageText, Toast.LENGTH_LONG).show();
                break;
            case R.id.second_in_group:
                if (item.isChecked()) {
                    item.setChecked(false);
                    messageText += " unchecked";
                } else {
                    item.setChecked(true);
                    messageText += " checked";
                }

                Toast.makeText(this, messageText, Toast.LENGTH_LONG).show();
                break;
            case R.id.first_in_submenu:
                if (item.isChecked()) {
                    item.setChecked(false);
                    messageText += " unchecked";
                } else {
                    item.setChecked(true);
                    messageText += " checked";
                }

                Toast.makeText(this, messageText, Toast.LENGTH_LONG).show();

                break;
            case R.id.second_in_submenu:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
