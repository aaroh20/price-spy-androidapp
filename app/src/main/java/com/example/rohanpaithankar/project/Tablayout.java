package com.example.rohanpaithankar.project;

/**
 * Created by Aaroh on 05-Apr-17.
 */
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

//import info.androidhive.materialtabs.R;
import com.example.rohanpaithankar.project.OneFragment;

import com.example.rohanpaithankar.project.SecondFragment;

public class Tablayout extends AppCompatActivity {

    private Toolbar toolbar, toolbar1;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private EditText query;
    String q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        //getSupportActionBar().setDisplayShowTitleEnabled(false);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Best Price");
        adapter.addFragment(new SecondFragment(), "Starred");
        //OneFragment o=new OneFragment();

        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
        adapter.getItem(0);

        /*if (adapter.getItem(0) != null) {
            OneFragment o = new OneFragment();
            Bundle bundle = new Bundle();
            bundle.putString("query", q);
            o.setArguments(bundle);
        }*/


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            /*Fragment fragment=null;

            Bundle bundle = new Bundle();
            bundle.putString("edttext", "data From Activity");

            switch (position){
                case 0:
                    fragment=new OneFragment();
                    fragment.setArguments(bundle);
                    break;
                case 1:
                    fragment=new SecondFragment();
                    break;
                default:
                    fragment=null;
                    break;
            }*/


            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
