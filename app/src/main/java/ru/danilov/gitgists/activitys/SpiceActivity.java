package ru.danilov.gitgists.activitys;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;

import ru.danilov.gitgists.SpiceService;

/**
 * Created by Danilov Alexey on 05.03.2016.
 */
public class SpiceActivity extends AppCompatActivity {
    private SpiceManager spiceManager = new SpiceManager(SpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
