package com.newassignment.test.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newassignment.retrofitexample.R;
import com.newassignment.test.model.WeatherDaily;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import io.armcha.playtablayout.core.PlayTabLayout;

public class Main2Activity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;

    @BindView(R.id.appbar)
    AppBarLayout appbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.container)
    ViewPager mViewPager;


    @BindView(R.id.fab)
    FloatingActionButton fab;
    private static ArrayList<WeatherDaily> list;
    private HorizontalCalendar horizontalCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main2);
        ButterKnife.bind(Main2Activity.this);


        //setSupportActionBar(toolbar);

        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView).build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {

            }

            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Date date, int position) {
                return true;
            }
        });

        list = (ArrayList<WeatherDaily>) getIntent().getSerializableExtra("object");

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";


        @BindView(R.id.section_label)
        TextView city;
        @BindView(R.id.constraintLayout)
        LinearLayout constraintLayout;

        TextView sectionLabel;

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.row)
        LinearLayout row;

        @BindView(R.id.temprature)
        TextView temprature;

        @BindView(R.id.weather)
        TextView weather;

        @BindView(R.id.country)
        TextView country;

        @BindView(R.id.day)
        TextView day;
        private WeatherDaily obj;

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            ButterKnife.bind(this, rootView);

            obj = list.get(getArguments().getInt(ARG_SECTION_NUMBER) - 1);
            temprature.setText(String.valueOf(obj.getTemp().getMax()));
            weather.setText(String.valueOf(obj.getWeatherlist().get(0).getMain()));
            city.setText(obj.getName() + ",India");
            country.setText(obj.getName() + ",India");
            day.setText(String.valueOf(obj.getTemp().getHumidity()));
            if (obj.getWeatherlist().get(0).getMain().equalsIgnoreCase("mist")) {
                image.setImageResource(R.drawable.sunny);
            } else if (obj.getWeatherlist().get(0).getMain().equalsIgnoreCase("cloudy")) {
                image.setImageResource(R.drawable.clear);

            } else if (obj.getWeatherlist().get(0).getMain().equalsIgnoreCase("rainy")) {
                image.setImageResource(R.drawable.rainy);

            } else {
                image.setImageResource(R.drawable.clear);

            }

            return rootView;
        }

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
