package ru.danilov.gitgists.fragments;

import android.os.Bundle;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.danilov.gitgists.api.model.Gist;
import ru.danilov.gitgists.api.requests.GetAllPublicRequest;

/**
 * Created by Danilov Alexey on 08.03.2016.
 */
public class AllPublicFragment extends BaseGistsFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onRefresh();
    }

    @Override
    public RealmResults<Gist> loadData() {
        Realm realm = Realm.getInstance(getActivity());
        return realm.where(Gist.class).findAllSorted("created_at");
    }

    @Override
    public void onRefresh() {
        getSpiceManager().execute(new GetAllPublicRequest(getActivity()), new RequestListener<Gist.SuperList>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                if (swipeContainer != null)
                    swipeContainer.setRefreshing(false);
            }

            @Override
            public void onRequestSuccess(Gist.SuperList gists) {
                if (swipeContainer != null)
                    swipeContainer.setRefreshing(false);
            }
        });
    }
}
