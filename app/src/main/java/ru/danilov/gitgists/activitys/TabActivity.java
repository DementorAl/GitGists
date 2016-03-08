package ru.danilov.gitgists.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.danilov.gitgists.R;
import ru.danilov.gitgists.fragments.AllPublicFragment;
import ru.danilov.gitgists.fragments.NotesFragment;

/**
 * Created by Danilov Alexey on 08.03.2016.
 */
public class TabActivity extends AppCompatActivity {
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, TabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        setTitle("All in One");
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0){
                    return new AllPublicFragment();
                }
                return new NotesFragment();
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0){
                    return  "All Gists";
                }
                return "Notes";
            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
