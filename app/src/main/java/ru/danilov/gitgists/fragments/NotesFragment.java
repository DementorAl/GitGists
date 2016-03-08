package ru.danilov.gitgists.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.danilov.gitgists.model.Gist;

/**
 * Created by Danilov Alexey on 08.03.2016.
 */
public class NotesFragment extends BaseGistsFragment {
    @Override
    public RealmResults<Gist> loadData() {
        Realm realm = Realm.getInstance(getActivity());
        return realm.where(Gist.class).isNotNull("localeDiscription").or().isNotNull("note").findAll();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeContainer.setEnabled(false);

    }


}
