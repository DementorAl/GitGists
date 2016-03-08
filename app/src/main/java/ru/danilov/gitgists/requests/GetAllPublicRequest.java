package ru.danilov.gitgists.requests;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import ru.danilov.gitgists.model.Gist;

/**
 * Created by Danilov Alexey on 05.03.2016.
 */
public class GetAllPublicRequest  extends BaseApiRequest<Gist.SuperList> {

    private final Context context;

    public GetAllPublicRequest(Context context) {
        super(Gist.SuperList.class);
        this.context = context;
    }

    @Override
    public Gist.SuperList loadDataFromNetwork() throws Exception {

        Realm realm = Realm.getInstance(context);
        Log.d("first", String.valueOf(realm.allObjects(Gist.class).size()));
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(getService().getAllGists());
        realm.commitTransaction();
        Log.d("second", String.valueOf(realm.allObjects(Gist.class).size()));

        return getService().getAllGists();
    }
}
