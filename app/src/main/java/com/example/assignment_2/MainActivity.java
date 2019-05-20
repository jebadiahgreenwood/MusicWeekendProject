package com.example.assignment_2;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.assignment_2.View.RockFragment;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Home"));
//        tabLayout.addTab(tabLayout.newTab().setText("About"));
//        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.view_pager);
        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch(tab.getPosition())
                {
                    case 0:
                        tab.setIcon(R.drawable.ic_rock_selected_24dp);
//                        viewPager.getCurrentItem();
//                        RockFragment rockFragment = (RockFragment)viewPager
//                                .getAdapter()
//                                .instantiateItem(viewPager, viewPager.getCurrentItem());
//                        rockFragment.bindPresenter();
//                        rockFragment.populateMusicList();
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_classic_selected_24dp);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_pop_selected_24dp);
                        break;
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch(tab.getPosition())
                {
                    case 0:
                        tab.setIcon(R.drawable.ic_rock_unselected_24dp);
                        break;
                    case 1:
                        tab.setIcon(R.drawable.ic_classic_unselected_24dp);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_pop_unselected_24dp);
                        break;
                }

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        new Handler().postDelayed(
//                new Runnable(){
//                    @Override
//                    public void run() {
//                        tabLayout.getTabAt(1).select();
//                    }
//                }, 100);
    }


}
