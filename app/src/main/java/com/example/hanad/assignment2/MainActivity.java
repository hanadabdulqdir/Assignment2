package com.example.hanad.assignment2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hanad.assignment2.classicDetails.model.ClassicMusicFragment;
import com.example.hanad.assignment2.popDetails.model.PopMusicFragment;
import com.example.hanad.assignment2.rockDetails.model.Result;
import com.example.hanad.assignment2.rockDetails.model.RockMusicAdapter;
import com.example.hanad.assignment2.rockDetails.model.RockMusicFragment;
import com.example.hanad.assignment2.rockDetails.model.RockMusicList;
import com.example.hanad.assignment2.service.ServiceConnection;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //FragmentTransaction transaction = fragmentManager.beginTransaction();
            fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    Toast.makeText(getApplicationContext(), "Rock", Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new RockMusicFragment())
                            .disallowAddToBackStack()
                            .commit();

                    //call the method here to display main
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(getApplicationContext(), "Classic", Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new ClassicMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(getApplicationContext(), "Pop", Toast.LENGTH_LONG).show();
                    fragmentManager.beginTransaction()
                            .add(R.id.fragmentContainer, new PopMusicFragment())
                            .disallowAddToBackStack()
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            RockMusicFragment rockMusicFragment = new RockMusicFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, new RockMusicFragment())
                    .addToBackStack(null)
                    .commit();

        } else {

        }
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}