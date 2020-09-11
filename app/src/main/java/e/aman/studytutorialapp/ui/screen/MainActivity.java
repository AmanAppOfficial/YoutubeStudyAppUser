package e.aman.studytutorialapp.ui.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import e.aman.studytutorialapp.R;
import e.aman.studytutorialapp.ui.fragments.ExploreFragment;
import e.aman.studytutorialapp.ui.fragments.MoreFragment;
import e.aman.studytutorialapp.ui.fragments.StudyFragment;
import e.aman.studytutorialapp.ui.fragments.TestFragment;


public class MainActivity extends AppCompatActivity{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loadFragment(new ExploreFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.explore:
                        fragment = new ExploreFragment();
                        break;

                    case R.id.study:
                        fragment = new StudyFragment();
                        break;

                    case R.id.test:
                        fragment = new TestFragment();
                        break;

                    case R.id.more:
                        fragment = new MoreFragment();
                        break;
                }

                return loadFragment(fragment);
            }

        });


    }




    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }




}
