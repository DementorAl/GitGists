package ru.danilov.gitgists.api.requests;

import android.content.Context;

import io.realm.Realm;
import ru.danilov.gitgists.api.model.Gist;

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
        realm.beginTransaction();
        Gist.SuperList allGists = getService().getAllGists();
        realm.copyToRealmOrUpdate(allGists);
        realm.commitTransaction();

        return allGists;
    }
}
