package com.example.forev.vetapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.forev.vetapp.Fragments.HomeFragment;
import com.example.forev.vetapp.R;
import com.example.forev.vetapp.Utils.ChangeFragments;
import com.example.forev.vetapp.Utils.GetSharedPreferences;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    private ChangeFragments changeFragments;
    private BottomNavigationView bottomNavigationView;
    private ImageView button_home,button_call,button_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        define();
        controlUser();
        //action();
    }


    private void getFragment() {
        changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }

    public void define()
    {
        preferences = new GetSharedPreferences(MainActivity.this);
        getSharedPreferences = preferences.getSession();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            changeFragments.change(new HomeFragment());
                            break;
                        case R.id.nav_call:
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("tel:05436193656"));
                            startActivity(intent);
                            break;
                        case R.id.nav_exit:
                            GetSharedPreferences getSharedPreferences = new GetSharedPreferences(MainActivity.this);
                            getSharedPreferences.deleteToSession();
                            Intent intentbir = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intentbir);
                    }
                     return  true;
                }
            };

    public void action()
    {
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragments.change(new HomeFragment());
            }
        });
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:05436193656"));
                startActivity(intent);
            }
        });
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetSharedPreferences getSharedPreferences = new GetSharedPreferences(MainActivity.this);
                getSharedPreferences.deleteToSession();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void controlUser()
    {
        if(getSharedPreferences.getString("id",null) == null && getSharedPreferences.getString("email",null) == null &&
                getSharedPreferences.getString("username",null) == null)
        {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
