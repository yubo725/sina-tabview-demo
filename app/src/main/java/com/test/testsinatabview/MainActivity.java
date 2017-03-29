package com.test.testsinatabview;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private CustomTabView mTabView;

    private int tabIndicatorInitWidth;

    private int[][] tabPostions = new int[4][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mTabView = (CustomTabView) findViewById(R.id.tab_view);

        tabIndicatorInitWidth = (int) (getScreenWidth() / 4 * 0.5);
        final int textWidth = getScreenWidth() / 4;
        for (int i = 0; i < 4; i++) {
            tabPostions[i][0] = (textWidth - tabIndicatorInitWidth) / 2 + textWidth * i;
            tabPostions[i][1] = tabPostions[i][0] + tabIndicatorInitWidth;
        }
        mTabView.setStartX(tabPostions[0][0]);
        mTabView.setEndX(tabPostions[0][1]);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new CustomPagerAdapter());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int distance = 0;
                if (positionOffset <= 0.5) {
                    distance = (int) (textWidth * (positionOffset / 0.5f));
                    mTabView.setEndX(tabPostions[position][1] + distance);
                    mTabView.setStartX(tabPostions[position][0]);
                } else {
                    distance = (int) (textWidth * ((positionOffset - 0.5) / 0.5f));
                    mTabView.setStartX(tabPostions[position][0] + distance);
                    mTabView.setEndX(tabPostions[position + 1][1]);
                }
                mTabView.postInvalidate();
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class CustomPagerAdapter extends PagerAdapter {

        private List<View> pages;

        public CustomPagerAdapter() {
            pages = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                TextView tv = new TextView(MainActivity.this);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(25);
                tv.setText("Tab" + (i + 1));
                pages.add(tv);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = pages.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pages.get(position));
        }
    }

    private int getScreenWidth() {
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

}
