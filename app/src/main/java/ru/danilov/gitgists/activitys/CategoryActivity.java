package ru.danilov.gitgists.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import ru.danilov.gitgists.R;
import ru.danilov.gitgists.fragments.AllPublicFragment;
import ru.danilov.gitgists.fragments.NotesFragment;

/**
 * Created by Danilov Alexey on 07.03.2016.
 */
public class CategoryActivity extends SpiceActivity {

    public static final String IS_ALL_TAG = "all";

    public static void start(Context context, boolean isAll) {
        Intent starter = new Intent(context, CategoryActivity.class);
        starter.putExtra(IS_ALL_TAG,isAll);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null){
            boolean isAll = getIntent().getBooleanExtra(IS_ALL_TAG, false);
            if (isAll){
                getSupportFragmentManager().beginTransaction().add(R.id.parent,new AllPublicFragment()).commit();
                setTitle("All Gists");
            }
            else {
                getSupportFragmentManager().beginTransaction().add(R.id.parent, new NotesFragment()).commit();
                setTitle("Notes");

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
