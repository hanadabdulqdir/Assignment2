package com.example.hanad.assignment2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanad.assignment2.classicDetails.model.ClassicMusicFragment;
import com.example.hanad.assignment2.popDetails.model.PopMusicFragment;
import com.example.hanad.assignment2.rockDetails.model.RockMusicFragment;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        RockMusicFragment rockMusicFragment = new RockMusicFragment();

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Rock:
                    //mTextMessage.setText(R.string.title_home);
                    Toast.makeText(getApplicationContext(), "Rock", Toast.LENGTH_LONG).show();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new RockMusicFragment())
                            .disallowAddToBackStack()
                            .commit();

                    return true; //end of Rock
                case R.id.navigation_Classic:
                    fragmentManager = getSupportFragmentManager();
                    Toast.makeText(getApplicationContext(), "Classic", Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new ClassicMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
                case R.id.navigation_Pop:
                    fragmentManager = getSupportFragmentManager();
                    Toast.makeText(getApplicationContext(), "Pop", Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new PopMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
            }
        return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        //if (savedInstanceState == null) {
            RockMusicFragment rockMusicFragment = new RockMusicFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, new RockMusicFragment())
                    .addToBackStack(null)
                    .commit();
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getItemBackgroundResource();

    }
}