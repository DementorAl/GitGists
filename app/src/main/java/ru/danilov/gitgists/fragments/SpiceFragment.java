package ru.danilov.gitgists.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;

import ru.danilov.gitgists.SpiceService;

/**
 * Created by Danilov Alexey on 07.03.2016.
 */
public class SpiceFragment extends Fragment {

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

    private SpiceManager spiceManager = new SpiceManager(getSpiceServiceClass());

    protected Class<? extends SpiceService> getSpiceServiceClass() {
        return SpiceService.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        spiceManager.start(context);
    }

    @Override
    public void onDetach() {
        spiceManager.shouldStop();
        super.onDetach();
    }
}
